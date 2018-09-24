package com.objectmentor.utilities.args;

import com.objectmentor.utilities.args.ArgsException;
import com.objectmentor.utilities.args.ArgumentMarshaler;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NumberFormatException;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
  private double doubleValue = 0;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      doubleValue = Double.parseDouble(parameter);
    } catch (NoSuchElementException e) {
      throw new ArgsException(ArgsException.ErrorCode.MISSING_DOUBLE);
    } catch (NumberFormatException e) {
      throw new ArgsException(ArgsException.ErrorCode.INVALID_DOUBLE, parameter);
    }
  }

  public Object get() {
    return doubleValue;
  }
}
