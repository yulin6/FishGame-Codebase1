package xjson;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** A main class for the program xjson.
 **/

public class Main {

  /** A main method that consumes a sequence of well-formed JSON values and delivers
   * JSON to STDOUT.
   * @param args arbitrarily long sequence of JSON values.
   */

  public static void main(String args[]) throws ParseException {

    Scanner scanner = new Scanner(System.in);
    JSONArray inputsAsJsonArray = new JSONArray();
    JSONParser parser = new JSONParser();

    Map<String, String> mapOfBracesOrBrackets = new HashMap<String, String>();
    mapOfBracesOrBrackets.put("{", "}");
    mapOfBracesOrBrackets.put("[", "]");
    String[] arrayOfBooleanOrNull = new String[]{"true", "false", "null"};


    while (scanner.hasNextLine()) {
      String currentLine = scanner.nextLine();

      for (String openingBracesOrBrackets : mapOfBracesOrBrackets.keySet()) {
        currentLine = currentLine.replace(openingBracesOrBrackets,
            " " + openingBracesOrBrackets);
      }
      for (String closingBracesOrBrackets : mapOfBracesOrBrackets.values()) {
        currentLine = currentLine.replace(closingBracesOrBrackets,
            closingBracesOrBrackets + " ");
      }
      for (String booleanOrNull : arrayOfBooleanOrNull) {
        currentLine = currentLine.replace(booleanOrNull,
            " " + booleanOrNull + " ");
      }

      String properSpacedLine = addSpaceForQuotes(currentLine);
      currentLine = properSpacedLine;

      Scanner rescanCurrentLine = new Scanner(currentLine);
      while (rescanCurrentLine.hasNext()) {
        String currentToken = rescanCurrentLine.next();
        while (!isBracesOrBracketsCompleted(mapOfBracesOrBrackets, currentToken)) {
          if (rescanCurrentLine.hasNext()) {
            currentToken = currentToken + rescanCurrentLine.next();
          }
        }
        inputsAsJsonArray.add(parser.parse(currentToken));
      }
    }

    printAnswers(inputsAsJsonArray);

  }

  /** If there is a string with extra quotes inside of it, it will turn the quotes
   * into spaces instead.
   *
   * @param str the input String that needs to be changed.
   **/
  public static String addSpaceForQuotes(String str) {
    String strTemp = str;
    boolean isRight = false;

    for (int i = 0; i < strTemp.length(); ++i) {
      char character = strTemp.charAt(i);
      if (character == '\"') {

        if (!isRight) {
          if (i != 0) {
            if (strTemp.charAt(i - 1) != ' ') {
              String tmp1 = strTemp.substring(0, i);
              String tmp2 = strTemp.substring(i);
              strTemp = tmp1 + " " + tmp2;
            }
          }
        } else {
          if (i != strTemp.length() - 1) {
            if (strTemp.charAt(i + 1) != ' ') {
              String tmp1 = strTemp.substring(0, i + 1);
              String tmp2 = strTemp.substring(i + 1);
              strTemp = tmp1 + " " + tmp2;
            }
          }
        }

        isRight = !isRight;
      }
    }
    return strTemp;
  }

  /** Checks whether the map of json value that is within an array or list has
   * a complete opening and closing bracket.
   *
   * @param mapOfBracesOrBrackets a map of Strings that are inside a bracket.
   * @param str a string that could possibly be inside or outside the map.
   * @return a boolean value that determines if the map is a complete json array or list.
   **/

  public static boolean isBracesOrBracketsCompleted(Map<String, String> mapOfBracesOrBrackets,
      String str) {
    for (Entry<String, String> e : mapOfBracesOrBrackets.entrySet()) {
      String opening = e.getKey();
      String closing = e.getValue();

      int count1 = 0;
      int index1 = 0;
      while ((index1 = str.indexOf(opening, index1)) != -1) {
        ++count1;
        ++index1;
      }
      int count2 = 0;
      int index2 = 0;
      while ((index2 = str.indexOf(closing, index2)) != -1) {
        ++count2;
        ++index2;
      }

      if (count1 != count2) return false;
    }
    return true;
  }

  /** Prints the JSONArray into the console.
   *
   * @param jArray JSONArray of JSON values.
   */

  public static void printAnswers(JSONArray jArray) {
    JSONObject firstAnswer = new JSONObject();
    firstAnswer.put("count", jArray.size());
    firstAnswer.put("seq", jArray);

    JSONArray secondAnswer = new JSONArray();
    for (int i = 0; i < jArray.size(); ++i) secondAnswer.add(0, jArray.get(i));

    secondAnswer.add(0, jArray.size());

    System.out.println(firstAnswer);
    System.out.println(secondAnswer);
  }

}
