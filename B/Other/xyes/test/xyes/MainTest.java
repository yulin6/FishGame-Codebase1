package xyes;

//import static org.junit.jupiter.api.Assertions.*;
import static junit.framework.TestCase.assertEquals;
import static xyes.Main.twentyOrInfinitePrints;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

/**
 * A class with test cases for the main class of xyes program.
 */
public class MainTest {

  @Test
  public void noCommand() {
    assertEquals("hello world", Main.argsToString(new String[]{}));
  }

  @Test
  public void emptyStringCommand() {
    assertEquals("hello world", Main.argsToString(new String[]{""}));
  }

  @Test
  public void spaceCommand() {
    assertEquals("hello world", Main.argsToString(new String[]{" "}));
  }

  @Test
  public void oneStringCommandNoLimit() {
    assertEquals("one", Main.argsToString(new String[]{"one"}));
  }

  @Test
  public void twoStringsCommandNoLimit() {
    assertEquals("one two", Main.argsToString(new String[]{"one two"}));
  }


  @Test
  public void onlyLimit() {
    assertEquals("hello world", Main.argsToString(new String[]{"-limit"}));
  }

  @Test
  public void spaceAfterLimit() {
    assertEquals("hello world", Main.argsToString(new String[]{"-limit", " "}));
  }

  @Test
  public void oneStringAfterLimit() {
    assertEquals("one", Main.argsToString(new String[]{"-limit", "one"}));
  }

  @Test
  public void twoStringsAfterLimit() {
    assertEquals("one two", Main.argsToString(new String[]{"-limit", "one", "two"}));
  }

  @Test
  public void printEmptyCommandWithLimit() {
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
  public void printSpaceCommandWithLimit() {
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
  public void printOneCommandWithLimit() {
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
  public void printTwoCommandsWithLimit() {
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
