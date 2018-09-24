package com.objectmentor.utilities.args;

import com.objectmentor.utilities.args.ArgsException;
import com.objectmentor.utilities.args.ArgumentMarshaler;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringArgumentMarshaler implements ArgumentMarshaler {
  private String stringValue = "";

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      stringValue = currentArgument.next();
    } catch (NoSuchElementException e) {
      throw new ArgsException(ArgsException.ErrorCode.MISSING_STRING);
    }
  }

  public Object get() {
    return stringValue;
  }
}
