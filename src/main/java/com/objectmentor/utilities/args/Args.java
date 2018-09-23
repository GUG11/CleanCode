/* schema: "l,p#,d*"
 * args: -l -p 8080 -d thrift */
package com.objectmentor.utilities.args;

import java.text.ParseException;
import java.util.*;

public class Args {
  private String schema;
  private String[] args;
  private boolean valid = true;
  private Set<Character> unexpectedArguments = new TreeSet<Character>();
  private Map<Character, Boolean> booleanArgs = new HashMap<Character, Boolean>();
  private int numberOfArguments = 0;

  public static void main(String[] args) {
    try {
      Args arg = new Args("l", args);
      boolean logging = arg.getBoolean('l');
      executeApplication(logging);
      System.out.printf(arg.errorMessage());
    } catch (ParseException e) {
      System.out.printf("Argument error \n");
    }
  }

  public static void executeApplication(boolean logging) {
    System.out.printf("logging: %b\n", logging);
  }

  public Args(String schema, String[] args) throws ParseException {
    this.schema = schema;
    this.args = args;
    valid = parse();
  }

  private boolean parse() throws ParseException {
    if (schema.length() == 0 && args.length == 0) {
      return true;
    }
    parseSchema();
    parseArguments();
    return unexpectedArguments.size() == 0;
  }

  private boolean parseSchema() {
    for (String element : schema.split(",")) {
      if (element.length() > 0) {
        parseSchemaElement(element);
      }
    }
    return true;
  }

  private void parseSchemaElement(String element) {
    if (element.length() == 1) {
      parseBooleanSchemaElement(element);
    }
  }

  private void parseBooleanSchemaElement(String element) {
    char c = element.charAt(0);
    if (Character.isLetter(c)) {
      booleanArgs.put(c, false);
    }
  }

  private boolean parseArguments() {
    for (String arg : args) {
      parseArgument(arg);
    }
    return true;
  }

  private void parseArgument(String arg) {
    if(arg.startsWith("-")) {
      parseElements(arg);
    }
  }

  private void parseElements(String arg) {
    for (int i = 1; i < arg.length(); i++) {
      parseElement(arg.charAt(i));
    }
  }

  private void parseElement(char argChar) {
    if (isBoolean(argChar)) {
      numberOfArguments++;
      setBooleanArg(argChar, true);
    } else {
      unexpectedArguments.add(argChar);
    }
  }

  private void setBooleanArg(char argChar, boolean value) {
    booleanArgs.put(argChar, value);
  }

  private boolean isBoolean(char argChar) {
    return booleanArgs.containsKey(argChar);
  }

  public int cardinality() {
    return numberOfArguments;
  }

  public String usage() {
    if (schema.length() > 0) {
      return "-[" + schema + "]";
    } else {
      return "";
    }
  }

  public String errorMessage() {
    if (unexpectedArguments.size() > 0) {
      return unexpectedArgumentMessage();
    } else {
      return "";
    }
  }

  private String unexpectedArgumentMessage() {
    StringBuffer message = new StringBuffer("Argument(s) -");
    for (char c: unexpectedArguments) {
      message.append(c);
    }
    message.append(" unexpected.");
    return message.toString();
  }

  public boolean getBoolean(char arg) {
    return booleanArgs.get(arg);
  }
}
