package models;

import java.util.ArrayList;
import models.Actions.IAction;

public class FishTreeNode {

  private FishState currentState;
  private ArrayList<FishState> directReachableStates;
  private ArrayList<FishTreeNode> directReachableTreeNodes;


  public FishTreeNode(FishState currentState) {
    if (!currentState.areAllPenguinsPlaced()) {
      throw new IllegalArgumentException("Error: incomplete number of penguins for each Player");
    }
    this.currentState = currentState;
    this.directReachableStates = new ArrayList<>();
    generateDirectReachableStates();
    this.directReachableTreeNodes = new ArrayList<>();
  }


  public void generateDirectReachableStates() {
    if (!this.currentState.isGameOver()) {
//      System.out.println("hello");

      ArrayList<Player> players = currentState.getPlayersSortedByAgeAscend();
      Player currentPlayer = players.get(currentState.getCurrentPlayerIndex());
      ArrayList<ArrayList<Tile>> board = currentState.getBoard();
      FishModel model = currentState.getFishModel();
      ArrayList<Penguin> penguinsOfTheSameColor = getPenguinsOfSameColor(currentState);

      for (Penguin penguin : penguinsOfTheSameColor) {
//        System.out.println("next penguin");
        int penguinXPos = penguin.getXPos();
        int penguinYPos = penguin.getYPos();
        ArrayList<Tile> possibleMovesTiles = model.getPossibleMoves(penguinXPos, penguinYPos);
        ArrayList<Position> possibleMovesPositions = getPositionsOfTiles(possibleMovesTiles, board);

        for (Position position : possibleMovesPositions) {

          int possibleMoveX = position.getX();
          int possibleMoveY = position.getY();
//          System.out.println(possibleMoveX + " " + possibleMoveY);
          FishState nextState = currentState.makeMovement(possibleMoveX, possibleMoveY, penguin,
              currentPlayer);
//          FishTreeNode newTree = new FishTreeNode(nextState);
          directReachableStates.add(nextState);
        }
      }
    }
  }

  public void generateDirectReachableTreeNodes() {
    for (FishState fishState : directReachableStates) {
      FishTreeNode treeNode = new FishTreeNode(fishState);
      directReachableTreeNodes.add(treeNode);
    }
  }

  public FishState actOnFishState(FishTreeNode fishTreeNode, IAction action)
      throws IllegalArgumentException {
    FishState fishState = fishTreeNode.getCurrentState();
    return action.performAction(fishState);
  }

  public ArrayList<FishState> applyFunctionToStates(FishTreeNode fishTreeNode, IAction action)
      throws IllegalArgumentException {

    ArrayList<FishState> states = fishTreeNode.getDirectReachableStates();
    ArrayList<FishState> newStates = new ArrayList<>();
    for (FishState state : states) {
      newStates.add(action.performAction(state));
    }
    return newStates;
  }

  private ArrayList<Position> getPositionsOfTiles(ArrayList<Tile> tiles,
      ArrayList<ArrayList<Tile>> board) {
    ArrayList<Position> possibleMovesPositions = new ArrayList<>();
    for (Tile tile : tiles) {
      for (int i = 0; i < board.size(); i++) {
        ArrayList<Tile> row = board.get(i);
        if (row.contains(tile)) {
          int xPos = row.indexOf(tile);
          int yPos = i;
          Position position = new Position(xPos, yPos);
          possibleMovesPositions.add(position);
        }
      }
    }

    return possibleMovesPositions;
  }


  private ArrayList<Penguin> getPenguinsOfSameColor(FishState fishState) {
    ArrayList<Penguin> penguinsOnBoard = fishState.getPenguinsOnBoard();
    ArrayList<Player> players = fishState.getPlayersSortedByAgeAscend();
    Player currentPlayer = players.get(fishState.getCurrentPlayerIndex());
    ArrayList<Penguin> penguinsOfSameColor = new ArrayList<>();
    for (Penguin penguin : penguinsOnBoard) {
      if (penguin.getColor().equals(currentPlayer.getPenguinColor())) {
        penguinsOfSameColor.add(penguin);
      }
    }
    return penguinsOfSameColor;
  }


  public FishState getCurrentState() {
    return currentState;
  }

  public ArrayList<FishState> getDirectReachableStates() {
    return directReachableStates;
  }

  public ArrayList<FishTreeNode> getDirectReachableTreeNodes() {
    return directReachableTreeNodes;
  }


}
