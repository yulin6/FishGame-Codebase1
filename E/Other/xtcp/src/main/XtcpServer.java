package main;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonStreamParser;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * An XtcpServer is a class that consumes a well-formed JSON values from the input side of a TCP
 * connection and delivers JSON to the output side of a TCP connection after the input side is
 * closed.
 **/

public class XtcpServer {


  public static void main(String[] args) {

    int serverPort = 4567;
    if (args.length > 1) {
      //System.out.println in the code are alerts for the server side.
      System.out.println("Invalid input arguments, "
          + "please input one port number between 1 to 65535 inclusively.");
      return;
    } else if (args.length == 1) {
      if (isValidPortNum(args[0])) {
        serverPort = Integer.parseInt(args[0]);
      } else {
        System.out.println("Invalid input argument, "
            + "please input one port number between 1 to 65535 inclusively.");
        return;
      }
    }
    serverOperations(serverPort);
  }

  /**
   * serverOperations takes in a serverPort and creates a connection from client that stores the
   * clientInput and serverOutput data and then outputs a JSON values to the console based on the
   * clientInput.
   *
   * @param serverPort the server port number.
   **/
  public static void serverOperations(int serverPort) {
    int timeoutNum = 3000;
    try {
      ServerSocket server = new ServerSocket(serverPort); // throws IOException
      System.out.println("*** Server running, current port is: " + serverPort + " ***");
      System.out.println("Waiting for client to connect...");
      server.setSoTimeout(timeoutNum); //Setting timeout if no client is connecting in 3 seconds.

      Socket clientConnection = server.accept(); //accept & create connection from client.

      // Reading data from client's input stream
      DataInputStream clientInput = new DataInputStream(clientConnection.getInputStream());
      // Writing data to the client's output stream
      DataOutputStream serverOutput = new DataOutputStream(clientConnection.getOutputStream());

      System.out.println("Client connected.");

      Scanner scanner = new Scanner(clientInput);
      JsonArray jsonArray = generateJsonArray(scanner);
      generateOutputs(jsonArray, serverOutput);

      clientConnection.close(); //close the connection with client.
      System.out.println("Output complete, client disconnected.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * isValidPortNum determines whether a string is a valid server port number and check whether its
   * and integer and its values are between 0 to 65535.
   *
   * @param s server port number from console input.
   * @return a boolean value to determine if the port number is valid.
   **/
  public static boolean isValidPortNum(String s) {
    int i;
    int startNum = 1;
    int endNum = 65535;
    try {
      i = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return i >= startNum && i <= endNum;
  }

  /**
   * generateJsonArray builds a JsonArray object based on the parsed data that is scanned from the
   * user input.
   *
   * @param scanner is a scanned values from the user input.
   **/
  public static JsonArray generateJsonArray(Scanner scanner) {
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
    return jsonArray;
  }

  /**
   * generateOutputs fills the DataOutputStream with the populated JsonArray into the appropriate
   * JSON output.
   *
   * @param jsonArray populated JsonArray from the clientInputData.
   * @param serverOutput an empty DataOutputStream.
   **/
  public static void generateOutputs(JsonArray jsonArray, DataOutputStream serverOutput) {
//    JsonElement jsonArraySize = new JsonPrimitive(jsonArray.size());

    int jsonArraySize = jsonArray.size();
    JsonObject firstOutput = new JsonObject();
    //addProperty() can take in a primitive num while add() takes in a json element
    firstOutput.addProperty("count", jsonArraySize);
    firstOutput.add("seq", jsonArray);

    //reverse the array
    JsonArray secondOutput = new JsonArray();
    secondOutput.add(jsonArraySize);
    for (int i = jsonArray.size() - 1; i >= 0; --i) {
      secondOutput.add(jsonArray.get(i));
    }

    try {
      //writing chars to DataOutputStream
      serverOutput.writeChars(firstOutput.toString()); //throws IOException
      serverOutput.writeChars("\n");
      serverOutput.writeChars(secondOutput.toString());
      serverOutput.writeChars("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

