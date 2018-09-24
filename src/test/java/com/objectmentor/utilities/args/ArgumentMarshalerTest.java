package com.objectmentor.utilities.args;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Unit test for simple App.
 */
public class ArgumentMarshalerTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ArgumentMarshalerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ArgumentMarshalerTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testBoolean() throws Exception
    {
      BooleanArgumentMarshaler am = new BooleanArgumentMarshaler();
      assertFalse((Boolean) am.get());
      am.set(null);
      assertTrue((Boolean) am.get());
    }

    public void testString() throws Exception
    {
      List<String> argsList = Arrays.asList(new String[]{"Test"});
      Iterator<String> it = argsList.iterator();
      StringArgumentMarshaler am = new StringArgumentMarshaler();
      assertEquals((String) am.get(), "");
      am.set(it);
      assertEquals((String) am.get(), "Test");
    }

    public void testMissingString() throws Exception
    {
      List<String> argsList = Arrays.asList(new String[]{});
      Iterator<String> it = argsList.iterator();
      StringArgumentMarshaler am = new StringArgumentMarshaler();
      try {
        am.set(it);
        fail();
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.MISSING_STRING);
      }
    }

    public void testInteger() throws Exception
    {
      List<String> argsList = Arrays.asList(new String[]{"100"});
      Iterator<String> it = argsList.iterator();
      IntegerArgumentMarshaler am = new IntegerArgumentMarshaler();
      assertEquals(((Integer) am.get()).intValue(), 0);
      am.set(it);
      assertEquals(((Integer) am.get()).intValue(), 100);
    }

    public void testMissingInteger() throws Exception
    {
      List<String> argsList = Arrays.asList(new String[]{});
      Iterator<String> it = argsList.iterator();
      IntegerArgumentMarshaler am = new IntegerArgumentMarshaler();
      try {
        am.set(it);
        fail();
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.MISSING_INTEGER);
      }
    }

    public void testInvalidInteger() throws Exception
    {
      List<String> argsList = Arrays.asList(new String[]{"Int"});
      Iterator<String> it = argsList.iterator();
      IntegerArgumentMarshaler am = new IntegerArgumentMarshaler();
      try {
        am.set(it);
        fail();
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.INVALID_INTEGER);
        assertEquals(e.getErrorParameter(), "Int");
      }
    }

    public void testDouble() throws Exception
    {
      List<String> argsList = Arrays.asList(new String[]{"100.0"});
      Iterator<String> it = argsList.iterator();
      DoubleArgumentMarshaler am = new DoubleArgumentMarshaler();
      assertEquals(((Double) am.get()).doubleValue(), 0.0);
      am.set(it);
      assertEquals(((Double) am.get()).doubleValue(), 100.0);
    }

    public void testMissingDouble() throws Exception
    {
      List<String> argsList = Arrays.asList(new String[]{});
      Iterator<String> it = argsList.iterator();
      DoubleArgumentMarshaler am = new DoubleArgumentMarshaler();
      try {
        am.set(it);
        fail();
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.MISSING_DOUBLE);
      }
    }

    public void testInvalidDouble() throws Exception
    {
      List<String> argsList = Arrays.asList(new String[]{"Double"});
      Iterator<String> it = argsList.iterator();
      DoubleArgumentMarshaler am = new DoubleArgumentMarshaler();
      try {
        am.set(it);
        fail();
      } catch (ArgsException e) {
        assertEquals(e.getErrorCode(), ArgsException.ErrorCode.INVALID_DOUBLE);
        assertEquals(e.getErrorParameter(), "Double");
      }
    }
}
