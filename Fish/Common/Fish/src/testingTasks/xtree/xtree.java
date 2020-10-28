package testingTasks.xtree;

import static common.models.FishTreeNode.getPositionsOfTiles;

import com.google.gson.Gson;

import java.util.*;

import common.models.Actions.MovePenguinAction;
import common.models.FishModel;
import common.models.FishState;
import common.models.FishTreeNode;
import common.models.Penguin;
import common.models.Player;
import common.models.Position;
import common.models.Tile;
import testingTasks.xstate.FishStateX;
import testingTasks.xstate.PlayerX;

public class xtree {


  public static void main(String[] args) throws IllegalArgumentException {

    Scanner scanner = new Scanner(System.in);
    StringBuilder builder = new StringBuilder();
    while (scanner.hasNextLine()) {
      builder.append(scanner.nextLine());
    }
    String jsonString = builder.toString();
    MoveResponseQuery query = new Gson().fromJson(jsonString, MoveResponseQuery.class);

    FishStateX stateX = query.state;
    ArrayList<Integer> from = query.from;
    ArrayList<Integer> to = query.to;
    ArrayList<PlayerX> playersXES = stateX.getPlayers();
    ArrayList<ArrayList<Integer>> boardX = stateX.getBoard();

    if (boardX.size() == 0) {
      throw new IllegalArgumentException("Error: no tiles on the board.");
    }
    if (from.size() != 2 && to.size() != 2) {
      throw new IllegalArgumentException("Error: malformed from or to position");
    }

    int boardHeight = boardX.size();
    int boardWidth = boardX.get(0).size();
    int tilesNum = boardHeight * boardWidth;
    if (tilesNum > 25) {
      throw new IllegalArgumentException(
          "Error: tiles number cannot be greater than 25 for the test.");
    }

    int maxFishNum = 4;
    boolean isRandom = false;
    FishModel fishModel = new FishModel(boardWidth, boardHeight, maxFishNum, isRandom);
    ArrayList<ArrayList<Tile>> board = fishModel.getBoard();
    for (int i = 0; i < boardX.size(); ++i) {
      for (int j = 0; j < boardX.get(i).size(); ++j) {
        Tile tile = board.get(i).get(j);
        int fishNum = boardX.get(i).get(j);
        if (fishNum == 0) {
          tile.setToHole();
        } else {
          tile.setFishNum(fishNum);
        }
      }
    }

    ArrayList<Player> players = new ArrayList<>();
    for (PlayerX playerX : playersXES) {
      Player player = new Player(0, playerX.getColor());
      players.add(player);
    }

    FishState fishState = new FishState(fishModel, players);

    boolean isAllPenguinPlaced = false;
    int penguinIndex = 0;
    int roundForPenguinIndex = 0;
    while (!isAllPenguinPlaced) {
      for (int i = 0; i < playersXES.size(); ++i) {

        int currentPlayerIndex = fishState.getCurrentPlayerIndex();
        Player currentPlayer = fishState.getPlayersSortedByAgeAscend().get(currentPlayerIndex);
        PlayerX playerX = playersXES.get(i);
        ArrayList<ArrayList<Integer>> places = playerX.getPlaces();

        ArrayList<Integer> place = places.get(penguinIndex);
        if (place.size() == 2) {
          int xPos = place.get(1);
          int yPos = place.get(0);

          fishState = fishState.placeInitPenguin(xPos, yPos, currentPlayer);

          if (i == playersXES.size() - 1) {
            ++penguinIndex;
            if (roundForPenguinIndex != places.size() - 1) {
              ++roundForPenguinIndex;
            } else {
              isAllPenguinPlaced = true;
            }
          }
        } else {
          throw new IllegalArgumentException("Error: malformed position.");
        }
      }
    }

    ArrayList<Penguin> penguinsOnBoard = fishState.getPenguinsOnBoard();
//    for (Penguin penguin : penguinsOnBoard) {
//      System.out.println(penguin.getXPos() + " " + penguin.getYPos());
//    }
    int currentPlayerIndex = fishState.getCurrentPlayerIndex();
    Player currentPlayer = fishState.getPlayersSortedByAgeAscend().get(currentPlayerIndex);
    Penguin targetPenguin = findTargetPenguin(from, penguinsOnBoard);

    int toX = to.get(1);
    int toY = to.get(0);
    MovePenguinAction movePenguinAction = new MovePenguinAction(toX, toY, targetPenguin,
        currentPlayer);
    FishTreeNode treeNode = new FishTreeNode(null, fishState);
    fishState = treeNode.applyActionToState(treeNode, movePenguinAction);

//    currentPlayerIndex = fishState.getCurrentPlayerIndex();
//    currentPlayer = fishState.getPlayersSortedByAgeAscend().get(currentPlayerIndex);
    FishModel model = fishState.getFishModel();
    board = fishState.getBoard();

    ArrayList<ArrayList<Integer>> neighboringTiles = findNeighboringTilesPos(toX, toY, boardX);
    ArrayList<Penguin> penguinsOfTheSameColor = fishState.getCurrentPlayerPenguins();
    Comparator<Penguin> comp = Comparator.comparing(Penguin::getYPos).thenComparing(Penguin::getXPos);
    penguinsOfTheSameColor.sort(comp);
//    System.out.println(penguinsOfTheSameColor.get(0).getXPos() + " " + penguinsOfTheSameColor.get(0).getYPos());
//    System.out.println(penguinsOfTheSameColor.get(1).getXPos() + " " + penguinsOfTheSameColor.get(1).getYPos());
//    System.out.println(penguinsOfTheSameColor.get(2).getXPos() + " " + penguinsOfTheSameColor.get(2).getYPos());



    for (ArrayList<Integer> neighboringTile : neighboringTiles) {
      int neighborX = neighboringTile.get(0);
      int neighborY = neighboringTile.get(1);
//      System.out.println(neighborX + " " + neighborY);

      for (Penguin penguin : penguinsOfTheSameColor) {
        int penguinXPos = penguin.getXPos();
        int penguinYPos = penguin.getYPos();

        ArrayList<Tile> possibleMovesTiles = model.getPossibleMoves(penguinXPos, penguinYPos);
        ArrayList<Position> possibleMovesPositions = getPositionsOfTiles(possibleMovesTiles, board);

        for (Position position : possibleMovesPositions) {

          int possibleMoveX = position.getX();
          int possibleMoveY = position.getY();

          if (possibleMoveX == neighborX && possibleMoveY == neighborY) {
            ArrayList<ArrayList<Integer>> output = new ArrayList<>();
            ArrayList<Integer> outputFrom = new ArrayList<>();
            outputFrom.add(penguinYPos);
            outputFrom.add(penguinXPos);

            ArrayList<Integer> outputTo = new ArrayList<>();
            outputTo.add(neighborY);
            outputTo.add(neighborX);

            output.add(outputFrom);
            output.add(outputTo);

            String moveString = new Gson().toJson(output);
            System.out.println(moveString);
            return;
          }
        }
      }
    }
    System.out.println("false");
  }

  private static Penguin findTargetPenguin(ArrayList<Integer> from,
      ArrayList<Penguin> penguinsOnBoard) {
    for (Penguin penguin : penguinsOnBoard) {
      int xPos = penguin.getXPos();
      int yPos = penguin.getYPos();

      if (xPos == from.get(1) && yPos == from.get(0)) {
        return penguin;
      }
    }
    return null;
  }

  private static ArrayList<ArrayList<Integer>> findNeighboringTilesPos(int xPos, int yPos,
      ArrayList<ArrayList<Integer>> board) {
    int boardHeight = board.size();
    int boardWidth = board.get(0).size();
    ArrayList<ArrayList<Integer>> positions = new ArrayList<>();

    ArrayList<Integer> north = new ArrayList<Integer>();
    north.add(xPos);
    north.add(yPos - 2);
    positions.add(north);

    boolean isYEven = yPos % 2 == 0;
    if (isYEven) {
      ArrayList<Integer> northEast = new ArrayList<Integer>();
      if (xPos >= 0 && xPos <= boardWidth - 1 && yPos - 1 >= 0 && yPos - 1 <= boardHeight - 1) {
        northEast.add(xPos);
        northEast.add(yPos - 1);
        positions.add(northEast);
      }

      ArrayList<Integer> southEast = new ArrayList<Integer>();
      if (xPos >= 0 && xPos <= boardWidth - 1 && yPos + 1 >= 0 && yPos + 1 <= boardHeight - 1) {
        southEast.add(xPos);
        southEast.add(yPos + 1);
        positions.add(southEast);
      }

      ArrayList<Integer> south = new ArrayList<Integer>();
      if (xPos >= 0 && xPos <= boardWidth - 1 && yPos + 2 >= 0 && yPos + 2 <= boardHeight - 1) {
        south.add(xPos);
        south.add(yPos + 2);
        positions.add(south);
      }

      ArrayList<Integer> southWest = new ArrayList<Integer>();
      if (xPos - 1 >= 0 && xPos - 1 <= boardWidth - 1 && yPos + 1 >= 0
          && yPos + 1 <= boardHeight - 1) {
        southWest.add(xPos - 1);
        southWest.add(yPos + 1);
        positions.add(southWest);
      }

      ArrayList<Integer> northWest = new ArrayList<Integer>();
      if (xPos - 1 >= 0 && xPos - 1 <= boardWidth - 1 && yPos - 1 >= 0
          && yPos - 1 <= boardHeight - 1) {
        northWest.add(xPos - 1);
        northWest.add(yPos - 1);
        positions.add(northWest);
      }
    } else {
      ArrayList<Integer> northEast = new ArrayList<Integer>();
      if (xPos - 1 >= 0 && xPos - 1 <= boardWidth - 1 && yPos - 1 >= 0
          && yPos - 1 <= boardHeight - 1) {
        northEast.add(xPos + 1);
        northEast.add(yPos - 1);
        positions.add(northEast);
      }

      ArrayList<Integer> southEast = new ArrayList<Integer>();
      if (xPos + 1 >= 0 && xPos + 1 <= boardWidth - 1 && yPos + 1 >= 0
          && yPos + 1 <= boardHeight - 1) {
        southEast.add(xPos + 1);
        southEast.add(yPos + 1);
        positions.add(southEast);
      }

      ArrayList<Integer> south = new ArrayList<Integer>();
      if (xPos >= 0 && xPos <= boardWidth - 1 && yPos + 2 >= 0 && yPos + 2 <= boardHeight - 1) {
        south.add(xPos);
        south.add(yPos + 2);
        positions.add(south);
      }

      ArrayList<Integer> southWest = new ArrayList<Integer>();
      if (xPos >= 0 && xPos <= boardWidth - 1 && yPos + 1 >= 0 && yPos + 1 <= boardHeight - 1) {
        southWest.add(xPos);
        southWest.add(yPos + 1);
        positions.add(southWest);
      }

      ArrayList<Integer> northWest = new ArrayList<Integer>();
      if (xPos >= 0 && xPos <= boardWidth - 1 && yPos - 1 >= 0 && yPos - 1 <= boardHeight - 1) {
        northWest.add(xPos);
        northWest.add(yPos - 1);
        positions.add(northWest);
      }
    }

//    Iterator<ArrayList<Integer>> iter = positions.iterator();
//    while (iter.hasNext()) {
//      ArrayList<Integer> pos = iter.next();
//      int posX = pos.get(0);
//      int posY = pos.get(1);
//      if( posX < 0 || posX > boardWidth - 1 || posY < 0 || posY > boardHeight - 1){
//        positions.remove(pos);
//      }
//    }
    return positions;
  }
}
