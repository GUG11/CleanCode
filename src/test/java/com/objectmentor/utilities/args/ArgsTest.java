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
      String[] arguments = new String[]{"-lpx"};
      Args arg = new Args(schema, arguments);
      assertTrue(arg.getBoolean('l'));
      assertTrue(arg.getBoolean('p'));
      assertFalse(arg.getBoolean('t'));
      assertFalse(arg.isValid());
      assertEquals(arg.cardinality(), 2);
      assertEquals(arg.errorMessage(), "Argument(s) -x unexpected.");
    }

    public void testStringArgs() throws Exception
    {
      String schema = "d*,f*";
      String[] arguments = new String[]{"-d", "/usr/local/bin", "-x", "/usr/bin"};
      Args arg = new Args(schema, arguments);
      assertEquals(arg.getString('d'), "/usr/local/bin");
      assertFalse(arg.isValid());
      assertEquals(arg.cardinality(), 1);
      assertEquals(arg.errorMessage(), "Argument(s) -x unexpected.");
    }

    public void testMissingString() throws Exception
    {
      String schema = "d*";
      String[] arguments = new String[]{"-d"};
      Args arg = new Args(schema, arguments);
      assertFalse(arg.isValid());
      assertEquals(arg.cardinality(), 0);
      assertEquals(arg.errorMessage(), "Could not find string parameter for -d.");
    }

    public void testIntegerArgs() throws Exception
    {
      String schema = "n#,p#";
      String[] arguments = new String[]{"-n", "145", "-x", "123"};
      Args arg = new Args(schema, arguments);
      assertEquals(arg.getInt('n'), 145);
      assertFalse(arg.isValid());
      assertEquals(arg.cardinality(), 1);
      assertEquals(arg.errorMessage(), "Argument(s) -x unexpected.");
    }

    public void testMissingInteger() throws Exception {
      String schema = "p#";
      String[] arguments = new String[]{"-p"};
      Args arg = new Args(schema, arguments);
      assertFalse(arg.isValid());
      assertEquals(arg.cardinality(), 0);
      assertEquals(arg.errorMessage(), "Could not find integer parameter for -p.");
    }

    public void testInvalidInteger() throws Exception {
      String schema = "p#";
      String[] arguments = new String[]{"-p", "Test"};
      Args arg = new Args(schema, arguments);
      assertFalse(arg.isValid());
      assertEquals(arg.cardinality(), 0);
      assertEquals(arg.errorMessage(), "Argument -p expects an integer but was 'Test'.");
    }

    public void testDoubleArgs() throws Exception
    {
      String schema = "f##,r##";
      String[] arguments = new String[]{"-f", "12.4", "-r", "0.0024", "-t", "0.2"};
      Args arg = new Args(schema, arguments);
      assertEquals(arg.getDouble('f'), 12.4);
      assertEquals(arg.getDouble('r'), 0.0024);
      assertFalse(arg.isValid());
      assertEquals(arg.cardinality(), 2);
      assertEquals(arg.errorMessage(), "Argument(s) -t unexpected.");
    }

    public void testMissingDouble() throws Exception {
      String schema = "f##";
      String[] arguments = new String[]{"-f"};
      Args arg = new Args(schema, arguments);
      assertFalse(arg.isValid());
      assertEquals(arg.cardinality(), 0);
      assertEquals(arg.errorMessage(), "Could not find double parameter for -f.");
    }

    public void testInvalidDouble() throws Exception {
      String schema = "f##";
      String[] arguments = new String[]{"-f", "Test"};
      Args arg = new Args(schema, arguments);
      assertFalse(arg.isValid());
      assertEquals(arg.cardinality(), 0);
      assertEquals(arg.errorMessage(), "Argument -f expects a double but was 'Test'.");
    }

    public void testMixedArgs() throws Exception
    {
      String schema = "l,d*,p#";
      String[] arguments = new String[]{"-l", "-d", "/usr/lib", "-p", "8080"};
      Args arg = new Args(schema, arguments);
      assertEquals(arg.getBoolean('l'), true);
      assertEquals(arg.getInt('p'), 8080);
      assertEquals(arg.getString('d'), "/usr/lib");
      assertTrue(arg.isValid());
      assertEquals(arg.cardinality(), 3);
    }
}
