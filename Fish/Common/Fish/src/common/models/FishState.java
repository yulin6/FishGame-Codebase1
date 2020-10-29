package common.models;

import static common.models.PenguinColor.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * The game state of the fish game tournament. It stores the FishModel, which is a game board class
 * of the fish game. Board is an ArrayList of ArrayList of Tile that is taken from the FishModel.
 * playersSortedByAgeAscend is the sorted array of Player by age. penguinsOnBoard is an empty
 * ArrayList of Penguin that will be populated in the FishState. currentPlayerIndex is the current
 * index of the PlayerX, which is 0. totalPlayerNum is the total number of players that will be
 * playing in this tournament.
 **/
public class FishState {


  private FishModel fishModel;
  private ArrayList<ArrayList<Tile>> board;
  private ArrayList<Player> playersSortedByAgeAscend;
  private ArrayList<Penguin> penguinsOnBoard;
  private int currentPlayerIndex;
  private int totalPlayerNum;

  /**
   * The constructor of the FishState takes in a FishModel and an ArrayList of Player.
   *
   * @param fishModel is the current model that the tournament will be using.
   * @param players is the ArrayList of Player that will be playing in the tournament.
   **/
  public FishState(FishModel fishModel, ArrayList<Player> players) {
    setUpState(fishModel, players);
    this.penguinsOnBoard = new ArrayList<Penguin>();
    this.currentPlayerIndex = 0;
  }

  /**
   * The private constructor is used for making copy of the current state and use it as the
   * next state. The fishModel, players, penguinsOnBoard will be exactly the same when creating
   * the state, but the ideal currentPlayerIndex should be the next PlayerX index.
   *
   * @param fishModel is the model copy for the state.
   * @param players the array list copy of players for the state.
   * @param penguinsOnBoard the array list copy of penguins on board for the state.
   * @param currentPlayerIndex the current PlayerX index for the state.
   */
  private FishState(FishModel fishModel, ArrayList<Player> players,
      ArrayList<Penguin> penguinsOnBoard, int currentPlayerIndex) {
    setUpState(fishModel, players);
    this.penguinsOnBoard = penguinsOnBoard;
    this.currentPlayerIndex = currentPlayerIndex;
  }

  /**
   * A helper method which would be used in the constructors, it checks if the number of players
   * is valid, and then assign the variables and sort the players list of their age.
   *
   * @param fishModel is the model copy for the state.
   * @param players the array list copy of players for the state.
   */
  private void setUpState(FishModel fishModel, ArrayList<Player> players) {
    this.totalPlayerNum = players.size();
    if (totalPlayerNum < 2 || totalPlayerNum > 4) {
      throw new IllegalArgumentException("Error: Invalid number of players.");
    }
    throwIfPlayersColorDuplicate(players);
    this.fishModel = fishModel;
    this.board = fishModel.getBoard();
    players.sort((p1, p2) -> Integer.valueOf(p1.getAge()).compareTo(p2.getAge()));
    this.playersSortedByAgeAscend = players;
  }

  public FishModel getFishModel() {
    return fishModel;
  }

  /**
   * get all the penguins on the board from the current game state.
   *
   * @return arrayList of penguins
   */
  public ArrayList<Penguin> getPenguinsOnBoard() {
    return penguinsOnBoard;
  }


  /**
   * Getter method of the the board, which return a 2d array list of tiles.
   *
   * @return a 2d array list of tiles represents the board.
   */
  public ArrayList<ArrayList<Tile>> getBoard() {
    return board;
  }

  /**
   * Get the players list, sorted by age, in the current state.
   *
   * @return array list of PlayerX in the state, which is sorted by age.
   */
  public ArrayList<Player> getPlayersSortedByAgeAscend() {
    return playersSortedByAgeAscend;
  }

  /**
   * Get the index of the PlayerX who is allowed to make action. The index is used for finding
   * the actual Player instance from the playersSortedByAgeAscend list.
   *
   * @return the index of the PlayerX who is allowed to make action.
   */
  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  private void throwIfPlayersColorDuplicate(ArrayList<Player> players) throws IllegalArgumentException{
    int redNum = 0;
    int blackNum = 0;
    int whiteNum = 0;
    int brownNum = 0;
    for(Player player : players){
      if (player.getPenguinColor().equals(red))  ++redNum;
      else if (player.getPenguinColor().equals(black))  ++blackNum;
      else if (player.getPenguinColor().equals(white))  ++whiteNum;
      else if (player.getPenguinColor().equals(brown))  ++brownNum;
    }
    if(redNum > 1 || blackNum > 1 || whiteNum > 1 || brownNum > 1){
      throw new IllegalArgumentException("Error: Found players with duplicated colors.");
    }
  }

  /**
   * Check whether can any new penguins be placed on the board, the valid PlayerX number should
   * be 2 to 4, and the valid number of penguin in each color should be 6 minus the PlayerX number.
   *
   * @return boolean represents whether all penguins are placed.
   */
  public boolean areAllPenguinsPlaced() {
    int penguinNumEachPlayer = 6 - totalPlayerNum;
    int redNum = penguinNumEachPlayer;
    int blackNum = penguinNumEachPlayer;
    int whiteNum = penguinNumEachPlayer;
    int brownNum = penguinNumEachPlayer;

    for (Player player : playersSortedByAgeAscend){
      PenguinColor color = player.getPenguinColor();
      if(color.equals(red)) redNum = 0;
      else if (color.equals(black)) blackNum = 0;
      else if (color.equals(white)) whiteNum = 0;
      else if (color.equals(brown)) brownNum = 0;
    }

    for (Penguin penguin : penguinsOnBoard) {

      if (penguin.getColor().equals(red)) {
        ++redNum;
      } else if (penguin.getColor().equals(black)) {
        ++blackNum;
      } else if (penguin.getColor().equals(white)) {
        ++whiteNum;
      } else if (penguin.getColor().equals(brown)) {
        ++brownNum;
      }
    }

    return redNum == penguinNumEachPlayer && blackNum == penguinNumEachPlayer
        && whiteNum == penguinNumEachPlayer && brownNum == penguinNumEachPlayer;
  }

  /**
   * Make a copy of the variables in the current state, and then construct a new state with
   * these variables, the only difference is the currentPlayerIndex, which will the next index
   * of the players list when constructing the state copy.
   *
   * @return a copy of the the current state, its currentPlayerIndex will be the next index
   * of the players list
   */
  private FishState createStateCopy() {
    Gson gson = new Gson();
    FishModel modelCopy = gson.fromJson(gson.toJson(fishModel), FishModel.class);

    Type playersType = new TypeToken<ArrayList<Player>>() {}.getType();
    ArrayList<Player> playersCopy = gson.fromJson(gson.toJson(playersSortedByAgeAscend), playersType);

    Type penguinsType = new TypeToken<ArrayList<Penguin>>() {}.getType();
    ArrayList<Penguin> penguinsOnBoardCopy = gson.fromJson(gson.toJson(penguinsOnBoard), penguinsType);
    int nextPlayerIndex = getNextPlayerIndex();
    FishState fishStateCopy = new FishState(modelCopy, playersCopy, penguinsOnBoardCopy,
        nextPlayerIndex);
    return fishStateCopy;
  }

  /**
   * placeInitPenguin places the initial position of the penguin based on the specific row and
   * column. A PlayerX should only be able to place a penguin when its their turn, and the target
   * position is in side of the board. The placement of penguin will be shown in the returned
   * next state.
   *
   * @param targetX the target x position or column of the board.
   * @param targetY the target y position or the row of the board.
   * @param player the PlayerX who is going to place their penguin onto board.
   * @return FishState the next state after making the placement of penguin.
   * @throws IllegalArgumentException when its now the PlayerX's turn or the target position is out
   * of the board.
   **/


  public FishState placeInitPenguin(int targetX, int targetY, Player player)
      throws IllegalArgumentException {
    if (!isPlayerTurn(player)) {
      throw new IllegalArgumentException("Error: Not your turn.");
    }
    if (isPosOutOfBoard(targetX, targetY)) {
      throw new IllegalArgumentException("Error: Target position is out of board.");
    }
    if (areAllPenguinsPlaced()) {
      throw new IllegalArgumentException("Error: no more penguins can be added.");
    }


    PenguinColor penguinColor = player.getPenguinColor();
    Penguin penguin = new Penguin(penguinColor);
    FishState nextState = createStateCopy();
    nextState.updatePenguinPos(targetX, targetY, penguin);
    nextState.addPlayerTotalFish(targetX, targetY, player.getId());

    return nextState;
  }





  /**
   * makeMovement moves an existing penguin on the board to a specified row and column within the
   * board. A PlayerX should only be able to move when its their turn, the penguin is theirs, and the
   * target position is in side of the board. The movement of penguin will be shown in the returned
   * next state.
   *
   * @param targetX the target x position or column of the board.
   * @param targetY the target y position or row of the board.
   * @param penguin the existing penguin on the board.
   * @param player the PlayerX who is going to move a penguin.
   * @return FishState the next state after the movement is made.
   * @throws IllegalArgumentException when its now the PlayerX's turn, the PlayerX is not the owner of
   * the penguin, the target position is out of the board or the target position is invalid to move
   * to.
   **/
  public FishState makeMovement(int targetX, int targetY, Penguin penguin, Player player)
      throws IllegalArgumentException {

    int startX = penguin.getXPos();
    int startY = penguin.getYPos();

    if (!isPlayerTurn(player)) {
      throw new IllegalArgumentException("Error: Not your turn.");
    }
    if (!alreadyOnBoard(penguin.getId())){
      throw new IllegalArgumentException("Error: The penguin is not on the board.");
    }
    if (!isPenguinOwner(penguin, player)) {
      throw new IllegalArgumentException("Error: Not the owner of the penguin.");
    }
    if (isPosOutOfBoard(targetX, targetY)) {
      throw new IllegalArgumentException("Error: Target position is out of board.");
    }


    ArrayList<Tile> possibleMoves = fishModel.getPossibleMoves(startX, startY);
    Tile targetTile = board.get(targetY).get(targetX);

    if (!possibleMoves.contains(targetTile)) {
      throw new IllegalArgumentException("Error: Invalid position to move to.");
    }

    FishState nextState = createStateCopy();
    nextState.updatePenguinPos(targetX, targetY, penguin);
    nextState.addPlayerTotalFish(targetX, targetY, player.getId());
    nextState.emptyStartTile(startX, startY);
    return nextState;

  }

  /**
   * Set the attributes, isEmpty and Penguin, of the tile to true and null.
   *
   * @param startX x position of the start tile.
   * @param startY y position of the start tile.
   */
  private void emptyStartTile(int startX, int startY) {
    Tile startTile = board.get(startY).get(startX);
    startTile.setToHole(); //makes the tile a hole when the penguin leaves it.
  }

  /**
   * isPenguinOwner takes in a penguin and a PlayerX, and check if their penguin color are the same,
   * which means whether the owner of the penguin is the PlayerX.
   *
   * @param penguin a penguin
   * @param player a PlayerX
   * @return boolean determines whether the penguin and the PlayerX have a same penguin color.
   */
  public boolean isPenguinOwner(Penguin penguin, Player player) {
    PenguinColor penguinColor = penguin.getColor();
    PenguinColor playColor = player.getPenguinColor();
    return penguinColor.equals(playColor);
  }

  /**
   * isGameOver determines whether the fish game tournament is over or not. This is determined only
   * if there are no penguins in the board that can move.
   *
   * @return a boolean value that determines if a game is over or not.
   **/
  public boolean isGameOver() {
    boolean isGameOver = true;
    if (penguinsOnBoard.size() == 0) {
      return false;
    }
    for (Penguin penguin : penguinsOnBoard) {
      int xPos = penguin.getXPos();
      int yPos = penguin.getYPos();

      ArrayList<Tile> possibleMoves = fishModel.getPossibleMoves(xPos, yPos);
      if (possibleMoves.size() == 0) {
        isGameOver = isGameOver && true;
      } else {
        isGameOver = false;
      }
    }
    return isGameOver;
  }


  /**
   * isPosOutOfBoard is a helper function that checks whether a chosen position is out of the range
   * of the board or not.
   *
   * @param xPos the x position or the column of the board.
   * @param yPos the y position or the row of the board.
   * @return a boolean value that determines if a position is out of the board or not.
   * @throws IllegalArgumentException when the position is out of the board.
   **/
  private boolean isPosOutOfBoard(int xPos, int yPos) throws IllegalArgumentException {
    if (board != null) {
      int boardHeight = board.size();
      int boardWidth = board.get(0).size();
      boolean isOutOfBoard = xPos < 0 || xPos >= boardWidth || yPos < 0 || yPos >= boardHeight;
      return isOutOfBoard;
    } else {
      throw new IllegalArgumentException("Error: Board is empty.");
    }
  }


  /**
   * isPlayerTurn is a helper function that checks whether the current PlayerX should be the one
   * moving its penguin.
   *
   * @param player the PlayerX that is trying to move a penguin.
   * @return boolean value that determines whether its actually a PlayerX's turn.
   **/
  private boolean isPlayerTurn(Player player) {
    long playerId = player.getId();
    long currentPlayerId = playersSortedByAgeAscend.get(currentPlayerIndex).getId();
    return currentPlayerId == playerId;
  }

  /**
   * nextPlayerTurn is a helper method that increments the currentPlayerIndex.
   **/
  private int getNextPlayerIndex() {
    int nextPlayerIndex = currentPlayerIndex;
    if (nextPlayerIndex >= totalPlayerNum - 1) {
      nextPlayerIndex = 0;
    } else {
      ++nextPlayerIndex;
    }
    return nextPlayerIndex;
  }


  /**
   * updatePenguinPos is a helper function that updates the penguin's position within the board.
   *
   * @param targetX the x position or the column of the board.
   * @param targetY the y position or the row of the board.
   * @param penguin the penguin that is being updated.
   * @throws IllegalArgumentException when the target tile already has a penguin.
   **/
  private void updatePenguinPos(int targetX, int targetY, Penguin penguin)
      throws IllegalArgumentException {

    Tile targetTile = board.get(targetY).get(targetX);
    boolean alreadyHadPenguin = targetTile.getPenguin() != null;

    if (!alreadyHadPenguin) {
      long penguinId = penguin.getId();
      if (!alreadyOnBoard(penguinId)) {
        penguin.setXPos(targetX);
        penguin.setYPos(targetY);
        penguinsOnBoard.add(penguin); //

      } else {
        penguin = getPenguinById(penguinId); //updating the penguin in the nextState.
        penguin.setXPos(targetX);
        penguin.setYPos(targetY);
      }
      targetTile.setPenguin(penguin);
    } else {
      throw new IllegalArgumentException("Error: There is already one penguin on the target tile.");
    }

  }

  /**
   * a helper method that checks if the penguin already exist in the penguinsOnBoard list.
   *
   * @param penguinId the id that distinguishes the penguin
   * @return whether the penguin already exist in the penguinsOnBoard list.
   */
  private boolean alreadyOnBoard(long penguinId){
    for (Penguin penguin : penguinsOnBoard){
      if(penguin.getId() == penguinId){
        return true;
      }
    }
    return false;
  }

  /**
   * a helpter method that find the penguin whose id matches the the given penguinId from
   * the playersSortedByAgeAscend list.
   *
   * @param penguinId the id of the penguin that's going to be returned.
   * @return the penguin whose id matches the the given penguinId.
   */
  private Penguin getPenguinById(long penguinId){
    for (Penguin penguin : penguinsOnBoard){
      if(penguin.getId() == penguinId){
        return penguin;
      }
    }
    throw new IllegalArgumentException("Error: no penguin on board with the id.");
  }

  /**
   * addPlayerTotalFish is helper function that adds the total number of fish gathered by a
   * penguin.
   *
   * @param targetX the x position or the column of the board.
   * @param targetY the y position or the row of the board.
   * @param playerId the id of the PlayerX whose total fish num will be added.
   **/
  private void addPlayerTotalFish(int targetX, int targetY, long playerId) {

    for (Player player : playersSortedByAgeAscend){
      if(player.getId() == playerId){
        Tile targetTile = board.get(targetY).get(targetX);
        int fishNum = targetTile.getFishNum();
        player.addTotalFish(fishNum);
      }
    }

  }

  /** A method that gets the the array list of penguins controlled by the current player from a
   * certain FishState, which means their PenguinColor are the same.
   *
//   * @param fishState the state of the Fish game.
   * @return an ArrayList of penguins that are controlled by the current player.
   *
   * @param penguinColor*/
  public ArrayList<Penguin> getPlayerPenguins(PenguinColor penguinColor) {
//    ArrayList<Penguin> penguinsOnBoard = fishState.getPenguinsOnBoard();
//    ArrayList<Player> players = fishState.getPlayersSortedByAgeAscend();

    Player currentPlayer = findPlayerWithTheColor(penguinColor);
    ArrayList<Penguin> penguinsOfSameColor = new ArrayList<>();
    for (Penguin penguin : penguinsOnBoard) {
      if (penguin.getColor().equals(currentPlayer.getPenguinColor())) {
        penguinsOfSameColor.add(penguin);
      }
    }
    return penguinsOfSameColor;
  }

  private Player findPlayerWithTheColor(PenguinColor penguinColor) {
    for(Player player: playersSortedByAgeAscend){
      if(player.getPenguinColor().equals(penguinColor)){
        return player;
      }
    }
    throw new IllegalArgumentException("Error: no player with the color.");
  }




}


