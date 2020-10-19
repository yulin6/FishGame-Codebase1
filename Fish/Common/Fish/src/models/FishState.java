package models;

import com.esotericsoftware.kryo.Kryo;
import java.util.ArrayList;


/**
 * The game state of the fish game tournament. It stores the FishModel, which is a game board class
 * of the fish game. Board is an ArrayList of ArrayList of Tile that is taken from the FishModel.
 * playersSortedByAgeAscend is the sorted array of Player by age. penguinsOnBoard is an empty
 * ArrayList of Penguin that will be populated in the FishState. currentPlayerIndex is the current
 * index of the player, which is 0. totalPlayerNum is the total number of players that will be
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
   * the state, but the ideal currentPlayerIndex should be the next player index.
   *
   * @param fishModel is the model copy for the state.
   * @param players the array list copy of players for the state.
   * @param penguinsOnBoard the array list copy of penguins on board for the state.
   * @param currentPlayerIndex the current player index for the state.
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
    this.fishModel = fishModel;
    this.board = fishModel.getBoard();
    players.sort((p1, p2) -> Integer.valueOf(p1.getAge()).compareTo(p2.getAge()));
    this.playersSortedByAgeAscend = players;
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
   * @return array list of player in the state, which is sorted by age.
   */
  public ArrayList<Player> getPlayersSortedByAgeAscend() {
    return playersSortedByAgeAscend;
  }

  /**
   * Get the index of the player who is allowed to make action. The index is used for finding
   * the actual Player instance from the playersSortedByAgeAscend list.
   *
   * @return the index of the player who is allowed to make action.
   */
  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  /**
   * Check whether can any new penguins be placed on the board, the valid player number should
   * be 2 to 4, and the valid number of penguin in each color should be 6 minus the player number.
   *
   * @return boolean represents whether all penguins are placed.
   */
  public boolean areAllPenguinsPlaced() {
    int penguinNumEachPlayer = 6 - totalPlayerNum;
    int redNum = 0;
    int blackNum = 0;
    int whiteNum = 0;
    int brownNum = 0;
    for (Penguin penguin : penguinsOnBoard) {
      if (penguin.getColor().equals(PenguinColor.RED)) {
        ++redNum;
      } else if (penguin.getColor().equals(PenguinColor.BLACK)) {
        ++blackNum;
      } else if (penguin.getColor().equals(PenguinColor.WHITE)) {
        ++whiteNum;
      } else if (penguin.getColor().equals(PenguinColor.BROWN)) {
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
    Kryo kryo = new Kryo();
    kryo.setRegistrationRequired(false);
//      kryo.register(FishModel.class);
//      kryo.register(ArrayList.class);
//      kryo.register(Tile.class);
    FishModel modelCopy = kryo.copy(fishModel);
//      kryo.register(Player.class);
    ArrayList<Player> playersCopy = kryo.copy(playersSortedByAgeAscend);
    ArrayList<Penguin> penguinsOnBoardCopy = kryo.copy(penguinsOnBoard);
    int nextPlayerIndex = getNextPlayerIndex();
    FishState fishStateCopy = new FishState(modelCopy, playersCopy, penguinsOnBoardCopy,
        nextPlayerIndex);
    return fishStateCopy;
  }

  /**
   * placeInitPenguin places the initial position of the penguin based on the specific row and
   * column. A player should only be able to place a penguin when its their turn, and the target
   * position is in side of the board. The placement of penguin will be shown in the returned
   * next state.
   *
   * @param targetX the target x position or column of the board.
   * @param targetY the target y position or the row of the board.
   * @param player the player who is going to place their penguin onto board.
   * @return FishState the next state after making the placement of penguin.
   * @throws IllegalArgumentException when its now the player's turn or the target position is out
   * of the board.
   **/


  public FishState placeInitPenguin(int targetX, int targetY, Player player)
      throws IllegalArgumentException {
//    System.out.println(currentPlayerNum);
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
   * board. A player should only be able to move when its their turn, the penguin is theirs, and the
   * target position is in side of the board. The movement of penguin will be shown in the returned
   * next state.
   *
   * @param targetX the target x position or column of the board.
   * @param targetY the target y position or row of the board.
   * @param penguin the existing penguin on the board.
   * @param player the player who is going to move a penguin.
   * @return FishState the next state after the movement is made.
   * @throws IllegalArgumentException when its now the player's turn, the player is not the owner of
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
    if (!isPenguinOwner(penguin, player)) {
      throw new IllegalArgumentException("Error: not the owner of the penguin.");
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
    startTile.setEmpty(); //makes the tile a hole when the penguin leaves it.
    startTile.setPenguin(null);
  }

  /**
   * isPenguinOwner takes in a penguin and a player, and check if their penguin color are the same,
   * which means whether the owner of the penguin is the player.
   *
   * @param penguin a penguin
   * @param player a player
   * @return boolean determines whether the penguin and the player have a same penguin color.
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
   * isPlayerTurn is a helper function that checks whether the current player should be the one
   * moving its penguin.
   *
   * @param player the player that is trying to move a penguin.
   * @return boolean value that determines whether its actually a player's turn.
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
   * @param playerId the id of the player whose total fish num will be added.
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




}


