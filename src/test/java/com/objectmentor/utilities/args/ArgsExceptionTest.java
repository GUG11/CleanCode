package com.objectmentor.utilities.args;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ArgsExceptionTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ArgsExceptionTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ArgsExceptionTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testUnexpectedArgument() throws Exception
    {
      ArgsException e = new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, 'x');
      assertEquals(e.errorMessage(), "Argument(s) -x unexpected.");
    }

    public void testMissingString() throws Exception
    {
      ArgsException e = new ArgsException(ArgsException.ErrorCode.MISSING_STRING, 'x');
      assertEquals(e.errorMessage(), "Could not find string parameter for -x.");
    }

    public void testInvalidInteger() throws Exception
    {
      ArgsException e = new ArgsException(ArgsException.ErrorCode.INVALID_INTEGER, 'x', "Int");
      assertEquals(e.errorMessage(), "Argument -x expects an integer but was 'Int'.");
    }

    public void testMissingInteger() throws Exception
    {
      ArgsException e = new ArgsException(ArgsException.ErrorCode.MISSING_INTEGER, 'x');
      assertEquals(e.errorMessage(), "Could not find integer parameter for -x.");
    }

    public void testInvalidDouble() throws Exception
    {
      ArgsException e = new ArgsException(ArgsException.ErrorCode.INVALID_DOUBLE, 'x', "Double");
      assertEquals(e.errorMessage(), "Argument -x expects a double but was 'Double'.");
    }

    public void testMissingDouble() throws Exception
    {
      ArgsException e = new ArgsException(ArgsException.ErrorCode.MISSING_DOUBLE, 'x');
      assertEquals(e.errorMessage(), "Could not find double parameter for -x.");
    }
}
