package testingTasks;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Scanner;
import common.models.FishModel;

public class xboard {

  public static void main(String[] args) throws IllegalArgumentException {


    Scanner scanner = new Scanner(System.in);
    StringBuilder builder = new StringBuilder();
    while (scanner.hasNextLine()) {
      builder.append(scanner.nextLine());
    }
    String jsonString = builder.toString();
    JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

    JsonArray board = jsonObject.getAsJsonArray("board");
    int[][] intBoard = new Gson().fromJson(board, int[][].class);
    ArrayList<ArrayList<Integer>> holes = new ArrayList<ArrayList<Integer>>();

    if(intBoard.length > 0) {
      int boardHeight = intBoard.length;
      int boardWidth = intBoard[0].length;
      int tilesNum = boardHeight * boardWidth;
      if (tilesNum > 25) {
        throw new IllegalArgumentException("Error: tiles number cannot be greater than 25 for the test.");
      }
      for(int i = 0; i < boardHeight; ++i){
        for (int j = 0; j < boardWidth; ++j){
          int fishNum = intBoard[i][j];
          if(fishNum == 0){
            ArrayList<Integer> hole = new ArrayList<Integer>();
            hole.add(j);
            hole.add(i);
            holes.add(hole);
          }
        }
      }
      int maxFishNum = 4;
      boolean isRandom = false;
      FishModel fishModel = new FishModel(boardWidth, boardHeight, maxFishNum, isRandom);

      if(holes.size() != 0) {
//      int holesArrayWidth = holes.get(0).size();
        int holesArrayHeight = holes.size();
        for (int i = 0; i < holesArrayHeight; ++i) {
          ArrayList<Integer> hole = holes.get(i);
            int holeX = hole.get(0);
            int holeY = hole.get(1);
            fishModel.createHole(holeX, holeY);
        }
      }

      JsonArray position = jsonObject.getAsJsonArray("position");
      int[] intPosition = new Gson().fromJson(position, int[].class);
      if(intPosition.length == 2) {
        int xPos = intPosition[1];
        int yPos = intPosition[0];
        int possibleMoveSize = fishModel.getPossibleMoves(xPos, yPos).size();
//        System.out.println(holes.toString());
        System.out.println(possibleMoveSize);
      } else throw new IllegalArgumentException("Error: malformed tile position.");
    } else throw new IllegalArgumentException("Error: no tiles on the board.");


//    System.out.println(Arrays.toString(intBoard[1]));

  }



}
