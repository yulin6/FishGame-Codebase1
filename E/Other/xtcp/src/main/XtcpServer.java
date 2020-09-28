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

/** An XtcpServer is a class that consumes a well-formed JSON values
 * from the input side of a TCP connection and delivers JSON to the output side of a TCP
 * connection after the input side is closed.
 **/

public class XtcpServer {

  /** A main class that communicates with linux server through inputting a server.
   *
   * @param args a server port number.
   **/
  public static void main(String[] args) {

    int serverPort = 4567;
    if (args.length > 1) {
      System.out.println("Invalid input arguments, "
          + "please input one port number between 0 to 65535 inclusively.");
      return;
    } else if (args.length == 1) {
      if (isValidPortNum(args[0])) {
        serverPort = Integer.parseInt(args[0]);
      } else {
        System.out.println("Invalid input argument, "
            + "please input one port number between 0 to 65535 inclusively.");
        return;
      }
    }

    serverOperations(serverPort);

  }

  /** serverOperations takes in a serverPort and creates a connection from client that stores
   * the clientInput and serverOutput data and then outputs a JSON values to the console based on
   * the clientInput.
   *
   * @param serverPort the server port number.
   **/
  public static void serverOperations(int serverPort) {
    try {
      ServerSocket server = new ServerSocket(serverPort);
      System.out.println("*** Server running, current port is: " + serverPort + " ***");
      System.out.println("Waiting for client to connect...");
      server.setSoTimeout(3000); //Setting timeout if no client is connecting in 3 seconds.

      Socket clientConnection = server.accept(); //accept & create connection from client.
      DataInputStream clientInput = new DataInputStream(clientConnection.getInputStream());
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

  /** isValidPortNum determines whether a string is a valid server port number and check whether
   * its and integer and its values are between 0 to 65535.
   *
   * @param s server port number from console input.
   * @return a boolean value to determine if the port number is valid.
   **/
  public static boolean isValidPortNum(String s) {
    int i;
    try {
      i = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return i > 0 && i <= 65535;
  }

  /** generateJsonArray builds a JsonArray object based on the parsed data that is scanned from
   * the user file.
   *
   * @param scanner is a scanned values from the user file.
   **/
  public static JsonArray generateJsonArray(Scanner scanner) {
    StringBuilder builder = new StringBuilder();
    JsonArray jsonArray = new JsonArray();
    JsonElement element;

    while (scanner.hasNextLine()) {
      builder.append(scanner.nextLine());
      // When the input of client user is not empty.
      if (!builder.toString().trim().equals("")) {
        JsonStreamParser parser = new JsonStreamParser(builder.toString());
        while (parser.hasNext()) {
          element = parser.next();
          jsonArray.add(element);
        }
        builder = new StringBuilder();
      }
    }
    return jsonArray;
  }

  /** generateOutputs fills the DataOutputStream with the populated JsonArray into
   * the appropriate JSON output.
   *
   * @param jsonArray populated JsonArray from the clientInputData.
   * @param serverOutput an empty DataOutputStream.
   **/
  public static void generateOutputs(JsonArray jsonArray, DataOutputStream serverOutput) {
    JsonElement jsonArraySize = new JsonPrimitive(jsonArray.size());

    JsonObject firstOutput = new JsonObject();
    firstOutput.add("count", jsonArraySize);
    firstOutput.add("seq", jsonArray);

    JsonArray secondOutput = new JsonArray();
    secondOutput.add(jsonArraySize);
    for (int i = jsonArray.size() - 1; i >= 0; --i) {
      secondOutput.add(jsonArray.get(i));
    }

    try {
      serverOutput.writeChars(firstOutput.toString());
      serverOutput.writeChars("\n");
      serverOutput.writeChars(secondOutput.toString());
      serverOutput.writeChars("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}

