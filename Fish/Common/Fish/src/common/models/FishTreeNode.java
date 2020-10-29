package common.models;

import java.util.ArrayList;
import common.models.Actions.IAction;

/**
 * FishTreeNode is a representation of a tree of FishState and FishTreeNodes. The parentNode is
 * a node its parent node of the current node, if it is the start of the tree, the parentNode
 * will be null. CurrentState is the current fishState of a certain game at certain point in time.
 * DirectReachableStates is the states that are directly reachable from the current FishState.
 * childNodes is an ArrayList<FishTreeNode>, which are the directly connected tree nodes,
 * and it will only be generated when the method by calling generateChildNodes().
 */
public class FishTreeNode {

  private FishTreeNode parentNode;
  private FishState currentState;
  private ArrayList<FishState> directReachableStates;
  private ArrayList<FishTreeNode> childNodes;


  /** The constructor of the FishTreeNode. The FishTreeNode is only created if all the penguins
   * have been placed on the FishBoard. The constructor takes in FishTreeNode as the parentNode and
   * the currentState of the game, if the there is no upper layer of the tree, the parentNode
   * will be null. From the currentState that directReachableStates are populated
   * by a method called GenerateDirectReachableStates.
   *
   * @param parentNode  is the parent of the current node.
   * @param currentState is the current FishState of the game.
   **/
  public FishTreeNode(FishTreeNode parentNode, FishState currentState) {
    if (!currentState.areAllPenguinsPlaced()) {
      throw new IllegalArgumentException("Error: incomplete number of penguins for each Player");
    }
    this.parentNode = parentNode;
    this.currentState = currentState;
    this.directReachableStates = new ArrayList<>();
    generateDirectReachableStates();
    this.childNodes = new ArrayList<>();
  }

  /**
   * A private Method that generates all directly reachable states from the current state of the game.
   */
  public void generateDirectReachableStates() {
    if (!this.currentState.isGameOver()) {
//      System.out.println("hello");

      ArrayList<Player> players = currentState.getPlayersSortedByAgeAscend();
      Player currentPlayer = players.get(currentState.getCurrentPlayerIndex());
      PenguinColor currentPlayerColor = currentPlayer.getPenguinColor();
      ArrayList<ArrayList<Tile>> board = currentState.getBoard();
      FishModel model = currentState.getFishModel();
      ArrayList<Penguin> penguinsOfTheSameColor = currentState.getPlayerPenguins(currentPlayerColor);

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

  /** A method that generate child nodes from all directly reachable states of the current state.
   */
  public void generateChildNodes() {
    for (FishState fishState : directReachableStates) {
      FishTreeNode treeNode = new FishTreeNode(this, fishState);
      childNodes.add(treeNode);
    }
  }

  /** A method that applies a function to the current state of a FishTreeNode.
   * @param fishTreeNode is a node that will be modified.
   * @param action is an interface that allow to modify a FishTreeNode.
   * @return a modified FishState based on a specific action.
   **/
  public FishState applyActionToState(FishTreeNode fishTreeNode, IAction action)
      throws IllegalArgumentException {
    FishState fishState = fishTreeNode.getCurrentState();
    return action.performAction(fishState);
  }

  /** A method that applies a function to all the directly reachable states from the FishTreeNode.
   * @param fishTreeNode is a node that will be modified.
   * @param action is an interface that allow to modify a FishTreeNode.
   * @return a modified ArrayList of FishStates based on a specific action.
   **/
  public ArrayList<FishState> applyActionToStates(FishTreeNode fishTreeNode, IAction action)
      throws IllegalArgumentException {

    ArrayList<FishState> states = fishTreeNode.getDirectReachableStates();
    ArrayList<FishState> newStates = new ArrayList<>();
    for (FishState state : states) {
      newStates.add(action.performAction(state));
    }
    return newStates;
  }

  /** A method that gets the Positions of tiles based on their positions on the board.
   *
   * @param tiles an ArrayList of tiles.
   * @param board an ArrayList of ArrayList of tiles that represent the fish board game.
   * @return an ArrayList of positions of the given tiles on the board.
   **/
  public static ArrayList<Position> getPositionsOfTiles(ArrayList<Tile> tiles,
      ArrayList<ArrayList<Tile>> board) {
    ArrayList<Position> positions = new ArrayList<>();
    for (Tile tile : tiles) {
      for (int i = 0; i < board.size(); i++) {
        ArrayList<Tile> row = board.get(i);
        if (row.contains(tile)) {
          int xPos = row.indexOf(tile);
          int yPos = i;
          Position position = new Position(xPos, yPos);
          positions.add(position);
        }
      }
    }

    return positions;
  }


  /** Gets the current parent node from the FishTreeNode.
   * @return a parent node.
   **/
  public FishTreeNode getParentNode() {
    return parentNode;
  }
  /** Gets the current state from the FishTreeNode.
   * @return the current state of the fish game.
   **/
  public FishState getCurrentState() {
    return currentState;
  }

  /** Gets the directly reachable states from the FishTreeNode.
   * @return an ArrayList of FishStates.
   **/
  public ArrayList<FishState> getDirectReachableStates() {
    return directReachableStates;
  }

  /** Gets the childNodes from the FishTreeNode.
   * @return an ArrayList of FishTreeNode.
   **/
  public ArrayList<FishTreeNode> getChildNodes() {
    return childNodes;
  }


}
