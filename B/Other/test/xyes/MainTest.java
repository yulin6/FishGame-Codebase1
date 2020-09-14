package xyes;

import static org.junit.jupiter.api.Assertions.*;
import static xyes.Main.main;
import static xyes.Main.twentyOrInfinitePrints;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * A class with test cases for the main class of xyes program.
 */
class MainTest {

  @Test
  void noCommand() {
    assertEquals("hello world", Main.argsToString(new String[]{}));
  }

  @Test
  void emptyStringCommand() {
    assertEquals("", Main.argsToString(new String[]{""}));
  }

  @Test
  void spaceCommand() {
    assertEquals("", Main.argsToString(new String[]{" "}));
  }

  @Test
  void oneStringCommandNoLimit() {
    assertEquals("one", Main.argsToString(new String[]{"one"}));
  }

  @Test
  void twoStringsCommandNoLimit() {
    assertEquals("one two", Main.argsToString(new String[]{"one two"}));
  }


  @Test
  void onlyLimit() {
    assertEquals("", Main.argsToString(new String[]{"-limit"}));
  }

  @Test
  void spaceAfterLimit() {
    assertEquals("", Main.argsToString(new String[]{"-limit", " "}));
  }

  @Test
  void oneStringAfterLimit() {
    assertEquals("one", Main.argsToString(new String[]{"-limit", "one"}));
  }

  @Test
  void twoStringsAfterLimit() {
    assertEquals("one two", Main.argsToString(new String[]{"-limit", "one", "two"}));
  }

  @Test
  void printEmptyCommandWithLimit() {
    Main.isLimit = true;
    /** Code Source of converting system.out.println to String:
     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
     */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    twentyOrInfinitePrints("");
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n", baos.toString());
  }

  @Test
  void printSpaceCommandWithLimit() {
    Main.isLimit = true;
    /** Code Source of converting system.out.println to String:
     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
     */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    twentyOrInfinitePrints("");
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n", baos.toString());
  }

  @Test
  void printOneCommandWithLimit() {
    Main.isLimit = true;
    /** Code Source of converting system.out.println to String:
     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
     */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    twentyOrInfinitePrints("one");
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n"
        + "one\n", baos.toString());
  }

  @Test
  void printTwoCommandsWithLimit() {
    Main.isLimit = true;
    /** Code Source of converting system.out.println to String:
     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
     */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    twentyOrInfinitePrints("one two");
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n"
        + "one two\n", baos.toString());
  }
}
