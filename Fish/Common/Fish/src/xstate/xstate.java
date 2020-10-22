package xstate;

import static com.esotericsoftware.minlog.Log.WARN;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;
import models.FishModel;
import models.FishState;
import models.Penguin;
import models.PenguinColor;
import models.Player;
import models.Position;
import models.Tile;

public class xstate {

  public static void main(String[] args) throws IllegalArgumentException {

    Scanner scanner = new Scanner(System.in);
    StringBuilder builder = new StringBuilder();
    while (scanner.hasNextLine()) {
      builder.append(scanner.nextLine());
    }
    String jsonString = builder.toString();
    JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

    JsonArray boardJosnArray = jsonObject.getAsJsonArray("board");
    int[][] intBoard = new Gson().fromJson(boardJosnArray, int[][].class);
    ArrayList<ArrayList<Integer>> holes = new ArrayList<ArrayList<Integer>>();

    if (intBoard.length > 0) {
      int boardHeight = intBoard.length;
      int boardWidth = intBoard[0].length;
      int tilesNum = boardHeight * boardWidth;
      if (tilesNum > 25) {
        throw new IllegalArgumentException(
            "Error: tiles number cannot be greater than 25 for the test.");
      }

      int maxFishNum = 4;
      boolean isRandom = false;
      FishModel fishModel = new FishModel(boardWidth, boardHeight, maxFishNum, isRandom);
      ArrayList<ArrayList<Tile>> board = fishModel.getBoard();
      for(int i = 0; i < intBoard.length; ++i){
        for(int j = 0; j < intBoard[i].length; ++j){
          Tile tile = board.get(i).get(j);
          int fishNum = intBoard[i][j];
          if(fishNum == 0){
            tile.setEmpty();
          } else {
            tile.setFishNum(fishNum);
          }
        }
      }


      JsonArray playersJsonArray = jsonObject.getAsJsonArray("players");
      ArrayList<Player> players = new ArrayList<>();

      for (JsonElement element : playersJsonArray) {

        JsonObject object = element.getAsJsonObject();
        JsonPrimitive colorObject = object.get("color").getAsJsonPrimitive();
        PenguinColor penguinColor = new Gson().fromJson(colorObject, PenguinColor.class);
        int score = object.get("score").getAsInt();


        Player player = new Player(0, penguinColor);
//        player.addTotalFish(score);
        players.add(player);

      }

      FishState fishState = new FishState(fishModel, players);

//      ArrayList<Player> playersSortedByAgeAscend = fishState.getPlayersSortedByAgeAscend();

      boolean isAllPenguinPlaced = false;

      int penguinIndex = 0;
      int roundForPenguinIndex = 0;

      while (!isAllPenguinPlaced) {

        for (int i = 0; i < playersJsonArray.size(); ++i) {
          JsonElement element = playersJsonArray.get(i);
          JsonObject object = element.getAsJsonObject();
          int currentPlayerIndex = fishState.getCurrentPlayerIndex();
          Player currentPlayer = fishState.getPlayersSortedByAgeAscend().get(currentPlayerIndex);

          JsonArray positions = object.getAsJsonArray("places");
          int[][] places = new Gson().fromJson(positions, int[][].class);

          int[] place = places[penguinIndex];
          if (place.length == 2) {
            int xPos = place[1];
            int yPos = place[0];

            fishState = fishState.placeInitPenguin(xPos, yPos, currentPlayer);

            if (i == playersJsonArray.size() - 1) {
              ++penguinIndex;
              if (roundForPenguinIndex != positions.size() - 1) {
                ++roundForPenguinIndex;
              } else {
                isAllPenguinPlaced = true;
              }
            }

          }else {
            throw new IllegalArgumentException("Error: malformed position.");
          }
        }
      }

      Player firstPlayer = fishState.getPlayersSortedByAgeAscend().get(0);
      ArrayList<Penguin> penguinsOnBoard = fishState.getPenguinsOnBoard();
      Penguin firstPenguin = getFirstPenguin(firstPlayer, penguinsOnBoard);
      int penguinX = firstPenguin.getXPos();
      int penguinY = firstPenguin.getYPos();

      fishModel = fishState.getFishModel();
      ArrayList<ArrayList<Tile>> fishBoard = fishState.getBoard();
      ArrayList<Tile> possibleMoves = fishModel.getPossibleMoves(penguinX, penguinY);
      if(possibleMoves.size() != 0) {
        ArrayList<Position> possibleMovesPos = getPositionsOfTiles(possibleMoves, fishBoard);
        Position firstPosition = possibleMovesPos.get(0);
        int xPos = firstPosition.getX();
        int yPos = firstPosition.getY();

        fishState = fishState.makeMovement(xPos, yPos, firstPenguin, firstPlayer);

        serializeFishState(fishState);
      } else {
        System.out.println("False");
      }

    } else {
      throw new IllegalArgumentException("Error: no tiles on the board.");
    }


  }

  private static void serializeFishState(FishState fishstate){
    ArrayList<Player> players = fishstate.getPlayersSortedByAgeAscend();
    ArrayList<PlayerX> playerXES = new ArrayList<>();
    ArrayList<Penguin> penguins = fishstate.getPenguinsOnBoard();
    for(Player player : players){
      PenguinColor penguinColor = player.getPenguinColor();
      int score = player.getTotalFish();
      PlayerX playerX = new PlayerX(score, penguinColor);
      for(Penguin penguin: penguins){
        if(penguin.getColor().equals(player.getPenguinColor())){
          int xPos = penguin.getXPos();
          int yPos = penguin.getYPos();
          ArrayList<Integer> place = new ArrayList<>();
          place.add(yPos);
          place.add(xPos);
          playerX.addPlace(place);
        }
      }
      playerXES.add(playerX);
    }

    ArrayList<ArrayList<Integer>> intBoard = new ArrayList<>();
    ArrayList<ArrayList<Tile>> board = fishstate.getBoard();
    for (ArrayList<Tile> tiles : board) {
      ArrayList<Integer> intRow = new ArrayList<>();
      for (Tile tile : tiles) {
        int fishNum = tile.getFishNum();
        intRow.add(fishNum);
      }
      intBoard.add(intRow);
    }


    FishStateX state = new FishStateX(playerXES, intBoard);
    String stateString = new Gson().toJson(state);
    System.out.println(stateString);

  }

  private static Penguin getFirstPenguin(Player firstPlayer, ArrayList<Penguin> penguinsOnBoard) {
    for(Penguin penguin : penguinsOnBoard){
      if(penguin.getColor().equals(firstPlayer.getPenguinColor())){
        return penguin;
      }
    }
    throw new IllegalArgumentException("Error: no penguin with the same color.");
  }

  private static ArrayList<Position> getPositionsOfTiles(ArrayList<Tile> tiles, ArrayList<ArrayList<Tile>> board){
    ArrayList<Position> possibleMovesPositions = new ArrayList<>();
    for (Tile tile : tiles) {
      for (int i = 0; i < board.size(); i++) {
        ArrayList<Tile> row = board.get(i);
        if (row.contains(tile)){
          int xPos = row.indexOf(tile);
          int yPos = i;
          Position position = new Position(xPos, yPos);
          possibleMovesPositions.add(position);
        }
      }
    }

    return possibleMovesPositions;
  }

}
