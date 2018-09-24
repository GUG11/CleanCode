package com.objectmentor.utilities.args;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.text.ParseException;

/**
 * Unit test for simple App.
 */
public class ArgsTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ArgsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ArgsTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testBooleanArgs() throws Exception
    {
      String schema = "l,t,p";
      String[] arguments = new String[]{"-lp"};
      Args arg = new Args(schema, arguments);
      assertTrue(arg.getBoolean('l'));
      assertTrue(arg.getBoolean('p'));
      assertFalse(arg.getBoolean('t'));
      assertEquals(arg.cardinality(), 2);
    }

    public void testUnexpected() throws Exception
    {
      String schema = "";
      String[] arguments = new String[]{"-x"};
      try {
        Args arg = new Args(schema, arguments);
        fail();
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.UNEXPECTED_ARGUMENT);
        assertEquals(e.getErrorArgumentId(), 'x');
      }
    }

    public void testStringArgs() throws Exception
    {
      String schema = "d*,f*";
      String[] arguments = new String[]{"-d", "/usr/local/bin", "-f", "/usr/bin"};
      Args arg = new Args(schema, arguments);
      assertEquals(arg.getString('d'), "/usr/local/bin");
      assertEquals(arg.getString('f'), "/usr/bin");
      assertEquals(arg.cardinality(), 2);
    }

    public void testMissingString() throws Exception
    {
      try {
        String schema = "d*";
        String[] arguments = new String[]{"-d"};
        Args arg = new Args(schema, arguments);
        fail();
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.MISSING_STRING);
        assertEquals(e.getErrorArgumentId(), 'd');
      }
    }

    public void testIntegerArgs() throws Exception
    {
      String schema = "n#,p#";
      String[] arguments = new String[]{"-n", "145", "-p", "123"};
      Args arg = new Args(schema, arguments);
      assertEquals(arg.getInt('n'), 145);
      assertEquals(arg.getInt('p'), 123);
      assertEquals(arg.cardinality(), 2);
    }

    public void testMissingInteger() throws Exception {
      try {
        String schema = "p#";
        String[] arguments = new String[]{"-p"};
        Args arg = new Args(schema, arguments);
        fail();
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.MISSING_INTEGER);
        assertEquals(e.getErrorArgumentId(), 'p');
      }
    }

    public void testInvalidInteger() throws Exception {
      try {
        String schema = "p#";
        String[] arguments = new String[]{"-p", "Test"};
        Args arg = new Args(schema, arguments);
        fail();
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.INVALID_INTEGER);
        assertEquals(e.getErrorArgumentId(), 'p');
        assertEquals(e.getErrorParameter(), "Test");
      }
    }

    public void testDoubleArgs() throws Exception
    {
      String schema = "f##,r##";
      String[] arguments = new String[]{"-f", "12.4", "-r", "0.0024"};
      Args arg = new Args(schema, arguments);
      assertEquals(arg.getDouble('f'), 12.4);
      assertEquals(arg.getDouble('r'), 0.0024);
      assertEquals(arg.cardinality(), 2);
    }

    public void testMissingDouble() throws Exception {
      try {
        String schema = "f##";
        String[] arguments = new String[]{"-f"};
        Args arg = new Args(schema, arguments);
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.MISSING_DOUBLE);
        assertEquals(e.getErrorArgumentId(), 'f');
      }
    }

    public void testInvalidDouble() throws Exception {
      try {
        String schema = "f##";
        String[] arguments = new String[]{"-f", "Test"};
        Args arg = new Args(schema, arguments);
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.INVALID_DOUBLE);
        assertEquals(e.getErrorArgumentId(), 'f');
        assertEquals(e.getErrorParameter(), "Test");
      }
    }

    public void testMixedArgs() throws Exception
    {
      String schema = "l,d*,p#";
      String[] arguments = new String[]{"-l", "-d", "/usr/lib", "-p", "8080"};
      Args arg = new Args(schema, arguments);
      assertEquals(arg.getBoolean('l'), true);
      assertEquals(arg.getInt('p'), 8080);
      assertEquals(arg.getString('d'), "/usr/lib");
      assertEquals(arg.cardinality(), 3);
    }
}
