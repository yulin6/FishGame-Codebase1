package xyes;

//Main class of program xyes, which will execute commands repeatedly.
//For more details, please reference the README file.
public class Main {


  protected static boolean isLimit = false;

  public static void main(String[] args) {

    String cmd = argsToString(args);
    twentyOrInfinitePrints(cmd);

  }

  /**
   * When the boolean isLimit is true, repeatedly print the input String for 20 times. Otherwise
   * infinitely printing the String.
   *
   * @param cmd the input String that going to be printed.
   */
  protected static void twentyOrInfinitePrints(String cmd) {
    if (isLimit) {
      for (int i = 0; i < 20; ++i) {
        System.out.println(cmd);
      }
    } else {
      while (true) {
        System.out.println(cmd);
      }
    }
  }

  /**
   * Turns input list of strings into one String. If the input list is empty, return "hello world".
   * If the first String is -limit, skip the first String. Otherwise, concatenate all Strings with a
   * space between each Strings. If the concatenated String is empty, return hello world instead.
   *
   * @param args a list of String that came from commands.
   * @return a single concatenated String.
   */
  protected static String argsToString(String[] args) {
    if (args.length == 0) {
      return "hello world";
    }
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < args.length; ++i) {
      if (i == 0 && args[i].equals("-limit")) {
        isLimit = true;
        continue;
      } else {
        builder.append(args[i]);
        builder.append(" ");
      }
    }

    String cmd = builder.toString().trim().equals("") ? "hello world" : builder.toString().trim();
    return cmd;
  }
}
