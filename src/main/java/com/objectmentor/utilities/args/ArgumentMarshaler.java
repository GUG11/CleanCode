package com.objectmentor.utilities.args;

import com.objectmentor.utilities.args.ArgsException;
import java.util.Iterator;

public interface ArgumentMarshaler {
  void set(Iterator<String> currentArgument) throws ArgsException;
  Object get();
}
