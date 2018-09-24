package com.objectmentor.utilities.args;

import com.objectmentor.utilities.args.ArgsException;
import com.objectmentor.utilities.args.ArgumentMarshaler;
import java.util.Iterator;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
  private boolean booleanValue = false;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    booleanValue = true;
  }

  public Object get() {
    return booleanValue;
  }
}

