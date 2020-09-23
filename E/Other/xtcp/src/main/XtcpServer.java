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

public class XtcpServer {

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
      JsonArray jsonArray = jsonArrayGenerator(scanner);
      generateOutputs(jsonArray, serverOutput);

      clientConnection.close(); //close the connection with client.
      System.out.println("Output complete, client disconnected.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static boolean isValidPortNum(String s) {
    int i;
    try {
      i = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return i >= 0 && i <= 65535;
  }

  public static JsonArray jsonArrayGenerator(Scanner scanner) {
    StringBuilder builder = new StringBuilder();
    JsonArray jsonArray = new JsonArray();
    JsonElement element;

    while (scanner.hasNextLine()) {
      builder.append(scanner.nextLine());
      // When the input of client user is not empty.
      if(!builder.toString().equals("")){
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

