package xjson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import java.util.Scanner;

/** A main class for the program xjson.
 **/

public class Main {

  /** A main method that consumes a sequence of well-formed JSON values and delivers
   * JSON to STDOUT.
   * @param args arbitrarily long sequence of JSON values.
   */

  public static void main(String args[]) {


    Scanner  scanner = new Scanner(System.in);
    StringBuilder builder = new StringBuilder();
    JsonArray jsonArray = new JsonArray();
    //JsonArray takes in JsonElements
    JsonElement element;

    while (scanner.hasNextLine()) {
      builder.append(scanner.nextLine());

      // Handle the case when there is an empty line.
      String trimmedString = builder.toString().trim();
      if (!trimmedString.equals("")) {
        //JsonStreamParser can't parse the entire string with line breaks,
        //so we decided to parse it line by line.
        JsonStreamParser parser = new JsonStreamParser(builder.toString());
        while (parser.hasNext()) {
          element = parser.next();
          jsonArray.add(element);
        }
        //once the current line is scanned and parsed, set the string builder to empty.
        builder = new StringBuilder();
      }
    }
    generateAnswers(jsonArray);

  }

  public static void generateAnswers(JsonArray jsonArray){
//    JsonElement jsonArraySize = new JsonPrimitive(jsonArray.size());

    int jsonArraySize = jsonArray.size();
    JsonObject firstOutput = new JsonObject();
    //addProperty() can take in primitive num unlike add() takes in json element
    firstOutput.addProperty("count", jsonArraySize);
    firstOutput.add("seq", jsonArray);

    JsonArray secondOutput = new JsonArray();
    secondOutput.add(jsonArraySize);
    for (int i = jsonArray.size() - 1; i >= 0; --i) {
      secondOutput.add(jsonArray.get(i));
    }

    System.out.println(firstOutput);
    System.out.println(secondOutput);
  }


}
