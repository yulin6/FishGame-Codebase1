import static junit.framework.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.junit.Test;
import xjson.Main;

/**
 * A test class that test the cases for the main class of xjson program.
 */

public class xjsonTest {

  @Test
  public void testAddSpaceQuoteNothing() {
    assertEquals(Main.addSpaceForQuotes(""), "");
  }

  @Test
  public void testIsBracesOrBracketsCompleteNot() {
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put("num", "1");
    map1.put("string", "hello");
    assertEquals(Main.isBracesOrBracketsCompleted(map1, "hello"), false);
  }

  @Test
  public void testIsBracesOrBracketsNothing() {
    Map<String, String> map1 = new HashMap<String, String>();
    assertEquals(Main.isBracesOrBracketsCompleted(map1, "NotInside"), true);
  }

  @Test
  public void testIsBracesOrBracketsComplete() {
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put("num", "1");
    map1.put("string", "hello");
    assertEquals(Main.isBracesOrBracketsCompleted(map1, "NotInside"), true);
  }

  @Test
  public void testIsBracesOrBracketsCompleteNot2() {
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put("num", "1");
    map1.put("string", "hello");
    assertEquals(Main.isBracesOrBracketsCompleted(map1, "1"), false);
  }


  @Test
  public void printNothing() {
    JSONArray list = new JSONArray();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    Main.printAnswers(list);
    System.out.flush();
    System.setOut(old);
    assertEquals("{\"count\":0,\"seq\":[]}\n"
        + "[0]\n", baos.toString());
  }

  @Test
  public void printNumbers() {
    JSONArray list = new JSONArray();
    list.add("1");
    list.add("2");
    list.add("3");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    Main.printAnswers(list);
    System.out.flush();
    System.setOut(old);
    assertEquals("{\"count\":3,\"seq\":[\"1\",\"2\",\"3\"]}\n"
        + "[3,\"3\",\"2\",\"1\"]\n", baos.toString());
  }

  @Test
  public void printJson() {
    JSONArray list = new JSONArray();
    list.add("[1]");
    list.add("{2,3}");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    Main.printAnswers(list);
    System.out.flush();
    System.setOut(old);
    assertEquals("{\"count\":2,\"seq\":[\"[1]\",\"{2,3}\"]}\n"
        + "[2,\"{2,3}\",\"[1]\"]\n", baos.toString());
  }

  @Test
  public void printEverything() {
    JSONArray list = new JSONArray();
    list.add("[1]");
    list.add("{2,3}");
    list.add("4");
    list.add("a");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    Main.printAnswers(list);
    System.out.flush();
    System.setOut(old);
    assertEquals("{\"count\":4,\"seq\":[\"[1]\",\"{2,3}\",\"4\",\"a\"]}\n"
        + "[4,\"a\",\"4\",\"{2,3}\",\"[1]\"]\n", baos.toString());
  }

  @Test
  public void printString() {
    JSONArray list = new JSONArray();
    list.add("\"a\"\"b\"");
    list.add("hello");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    Main.printAnswers(list);
    System.out.flush();
    System.setOut(old);
    assertEquals("{\"count\":2,\"seq\":[\"\\\"a\\\"\\\"b\\\"\",\"hello\"]}\n"
        + "[2,\"hello\",\"\\\"a\\\"\\\"b\\\"\"]\n", baos.toString());
  }
}
