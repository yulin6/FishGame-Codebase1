package models;

import java.util.ArrayList;


/** The game state of the fish game tournament. It stores the FishModel, which is a
 * game board class of the fish game. Board is an ArrayList of ArrayList of Tile that is
 * taken from the FishModel. playersSortedByAgeAscend is the sorted array of Player by age.
 * penguinsOnBoard is an empty ArrayList of Penguin that will be populated in the FishGameState.
 * currentPlayerNum is the current index of the player, which is 0. totalPlayerNum is the total
 * number of players that will be playing in this tournament.
 **/
public class FishGameState {
  private FishModel fishModel;
  private ArrayList<ArrayList<Tile>> board;
  private ArrayList<Player> playersSortedByAgeAscend;
  private ArrayList<Penguin> penguinsOnBoard;
  private int currentPlayerNum;
  private int totalPlayerNum;

  /** The constructor of the FishGameState takes in a FishModel and an ArrayList of Player.
   *
   * @param fishModel is the current model that the tournament will be using.
   * @param players is the ArrayList of Player that will be playing in the tournament.
   **/
  public FishGameState(FishModel fishModel, ArrayList<Player> players){
    this.fishModel = fishModel;
    this.board = fishModel.getBoard();
    players.sort((p1, p2) -> Integer.valueOf(p1.getAge()).compareTo(p2.getAge()));
    this.playersSortedByAgeAscend = players;
    this.penguinsOnBoard = new ArrayList<Penguin>();
    this.currentPlayerNum = 0;
    this.totalPlayerNum = players.size();
  }


  /** placeInitPenguin places the initial position of the penguin based on the specific
   * row and column.
   *
   * @param targetX the target x position or column of the board.
   * @param targetY the target y position or the row of the board.
   * @param penguin the penguin that will be placed.
   **/
  public void placeInitPenguin(int targetX, int targetY, Penguin penguin){
      Player player = penguin.getPlayer();
      if (isPlayerTurn(player)) {
        if (!isPosOutOfBoard(targetX, targetY)) {
          updatePenguinPos(targetX, targetY, penguin);
        } else
          System.out.println("Error: Target position is out of board.");
      } else
        System.out.println("Error: Not your turn.");
  }


  /** makeMovement moves an existing penguin on the board to a specified row and column within the
   * board.
   *
   * @param targetX the target x position or column of the board.
   * @param targetY the target y position or row of the board.
   * @param penguin the existing penguin on the board.
   **/
  public void makeMovement(int targetX, int targetY, Penguin penguin){
      Player player = penguin.getPlayer();
      int startX = penguin.getXPos();
      int startY = penguin.getYPos();

      if (isPlayerTurn(player)) {
        if (!isPosOutOfBoard(targetX, targetY)) {
          ArrayList<Tile> possibleMoves = fishModel.getPossibleMoves(startX, startY);
          Tile targetTile = board.get(targetY).get(targetX);
          Tile startTile = board.get(startY).get(startX);

          if (possibleMoves.contains(targetTile)) {
            updatePenguinPos(targetX, targetY, penguin);
            startTile.setEmpty(); //make the tile a hole when the penguin leaves it.
            startTile.setPenguin(null);
          } else
            System.out.println("Error: Invalid position to move to.");
        } else
          System.out.println("Error: Target position is out of board.");
      } else
        System.out.println("Error: Not your turn.");
  }

  /**isGameOver determines whether the fish game tournament is over or not. This is determined
   * only if there are no penguins in the board that can move.
   *
   * @return a boolean value that determines if a game is over or not.
   **/
  public boolean isGameOver(){
      boolean isGameOver = true;
      if(penguinsOnBoard.size() == 0) return false;
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


  /**isPosOutOfBoard is a helper function that checks whether a chosen position is out of the
   * range of the board or not.
   *
   * @param xPos the x position or the column of the board.
   * @param yPos the y position or the row of the board.
   * @return a boolean value that determines if a position is out of the board or not.
   **/
  private boolean isPosOutOfBoard(int xPos, int yPos){
    if(board != null) {
      int boardHeight = board.size();
      int boardWidth = board.get(0).size();
      boolean isOutOfBoard = xPos < 0 || xPos >= boardWidth || yPos < 0 || yPos >= boardHeight;
      return isOutOfBoard;
    } else {
      System.out.println("Error: Board is empty.");
      return true;
    }
  }

  /** isPlayerTurn is a helper function that checks whether the current player should be
   * the one moving its penguin.
   *
   * @param player the player that is trying to move a penguin.
   * @return boolean value that determines whether its actually a player's turn.
   **/
  private boolean isPlayerTurn(Player player){
    Player currentPlayer = playersSortedByAgeAscend.get(currentPlayerNum);
    return currentPlayer.equals(player);
  }

  /** nextPlayerTurn is a helper function that increments the currentPlayerNum.
   **/
  private void nextPlayerTurn(){
    if(currentPlayerNum >= totalPlayerNum - 1) currentPlayerNum = 0;
    else ++currentPlayerNum;
  }

  /** updatePenguinPos is a helper function that updates the penguin's position within the board.
   *
   * @param targetX the x position or the column of the board.
   * @param targetY the y position or the row of the board.
   * @param penguin the penguin that is being updated.
   **/
  private void updatePenguinPos(int targetX, int targetY, Penguin penguin){

    Tile targetTile = board.get(targetY).get(targetX);
    boolean alreadyHadPenguin = targetTile.getPenguin() != null;

    if(!alreadyHadPenguin) {
      if(!penguinsOnBoard.contains(penguin)){
        penguin.setXPos(targetX);
        penguin.setYPos(targetY);
        penguinsOnBoard.add(penguin);
      } else {
        penguin.setXPos(targetX);
        penguin.setYPos(targetY);
      }
      targetTile.setPenguin(penguin);
      updatePlayersFish(targetTile, penguin);
      nextPlayerTurn();
    } else System.out.println("Error: There is already one penguin on the target tile.");

  }

  /** updatePlayerFish is helper function that adds the total number of fish gathered by a penguin.
   *
   * @param targetTile the tile that the penguin is moving to.
   * @param penguin the moving penguin.
   **/
  private void updatePlayersFish(Tile targetTile, Penguin penguin) {
    Player player = penguin.getPlayer();
    int fishNum = targetTile.getFishNum();
    player.addTotalFish(fishNum);
  }



}
