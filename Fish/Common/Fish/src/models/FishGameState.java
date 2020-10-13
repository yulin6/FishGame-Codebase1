package models;

import java.util.ArrayList;


public class FishGameState {
  private FishModel fishModel;
  private ArrayList<ArrayList<Tile>> board;
  private ArrayList<Player> playersSortedByAgeAscend;
  private ArrayList<Penguin> penguinsOnBoard;
  private int currentPlayerNum;
  private int totalPlayerNum;

  public FishGameState(FishModel fishModel, ArrayList<Player> players){
    this.fishModel = fishModel;
    this.board = fishModel.getBoard();
    players.sort((p1, p2) -> Integer.valueOf(p1.getAge()).compareTo(p2.getAge()));
    this.playersSortedByAgeAscend = players;
    this.penguinsOnBoard = new ArrayList<Penguin>();
    this.currentPlayerNum = 0;
    this.totalPlayerNum = players.size();
  }


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

  private boolean isPlayerTurn(Player player){
    Player currentPlayer = playersSortedByAgeAscend.get(currentPlayerNum);
    return currentPlayer.equals(player);
  }

  private void nextPlayerTurn(){
    if(currentPlayerNum >= totalPlayerNum - 1) currentPlayerNum = 0;
    else ++currentPlayerNum;
  }

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

  private void updatePlayersFish(Tile targetTile, Penguin penguin) {
    Player player = penguin.getPlayer();
    int fishNum = targetTile.getFishNum();
    player.addTotalFish(fishNum);
  }



}
