package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/** A MainTest that tests the methods in the main class.
 **/

class MainTest {

  @Test
  public void testIsPoint() {
    assertEquals(true, Main.isPosInt("1"));
    assertEquals(true, Main.isPosInt("100"));
    assertEquals(false, Main.isPosInt("-10"));
    assertEquals(false, Main.isPosInt("a"));
    assertEquals(false, Main.isPosInt("0"));
    assertEquals(false, Main.isPosInt("1000 1000"));
  }

  @Test
  public void testMainString() {
    String[] num = new String[]{"a"};
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    Main.main(num);
    System.out.flush();
    System.setOut(old);
    assertEquals("Invalid input: a. "
        + "Please input a positive integer instead.\n", baos.toString());
  }

  @Test
  public void testMainNegative() {
    String[] num = new String[]{"-10"};
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    Main.main(num);
    System.out.flush();
    System.setOut(old);
    assertEquals("Invalid input: -10. "
        + "Please input a positive integer instead.\n", baos.toString());
  }

  @Test
  public void testMainTwoInts() {
    String[] num = new String[]{"10 10"};
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    Main.main(num);
    System.out.flush();
    System.setOut(old);
    assertEquals("Invalid input: 10 10. "
        + "Please input a positive integer instead.\n", baos.toString());
  }

}
