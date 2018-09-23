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
    public void testBooleanArgs()
    {
      String schema = "l,t,p";
      String[] arguments = new String[]{"-lpx"};
      try {
        Args arg = new Args(schema, arguments);
        assertTrue(arg.getBoolean('l'));
        assertTrue(arg.getBoolean('p'));
        assertFalse(arg.getBoolean('t'));
        assertEquals(arg.errorMessage(), "Argument(s) -x unexpected.");
      } catch (ParseException e) {
        fail(e.toString());
      } catch (Exception e) {
        fail(e.toString());
      }
    }

    public void testStringArgs()
    {
      String schema = "d*,f*";
      String[] arguments = new String[]{"-d", "/usr/local/bin", "-x", "/usr/bin"};
      try {
        Args arg = new Args(schema, arguments);
        assertEquals(arg.getString('d'), "/usr/local/bin");
        assertEquals(arg.errorMessage(), "Argument(s) -x unexpected.");
      } catch (ParseException e) {
        fail(e.toString());
      } catch (Exception e) {
        fail(e.toString());
      }
    }

    public void testIntegerArgs()
    {
      String schema = "n#,p#";
      String[] arguments = new String[]{"-n", "145", "-x", "123"};
      try {
        Args arg = new Args(schema, arguments);
        assertEquals(arg.getInt('n'), 145);
        assertEquals(arg.errorMessage(), "Argument(s) -x unexpected.");
      } catch (ParseException e) {
        fail(e.toString());
      } catch (Exception e) {
        fail(e.toString());
      }
    }
}
