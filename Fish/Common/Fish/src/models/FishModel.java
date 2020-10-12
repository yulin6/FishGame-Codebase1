package models;

import java.util.ArrayList;
import java.util.Random;

/**
 * The model class of the fish game. It stores the game board representation, which is a
 * ArrayList of ArrayList of Fish Tiles. There are two types (modes) of game board, one (RANDOM)is
 * having randomly number of fishes on each tiles with a minimum number of 1-fish tiles.
 * Another one (NONRANDOM) has each tiles with the same number of fish numbers.
 * The Main methods are emptyTile, getPossibleMoves and getBoardCopy.
 */
public class FishModel {

  private ArrayList<ArrayList<FishTile>> board;
//  private int maxFishNum = 5;
  private int minFishNum = 1;

  /**
   * A constructor that construct a empty game board for testing.
   */
  public FishModel() {
  }

  /**
   * The main constructor of the fish model. Width and height are used for determine the size
   * of the game board. When isRandomized is true, it means we are in a RANDOM mode,
   * the third input: minOneFishNumOrFishNumOnTiles represent the minimum number of 1-fish tiles
   * on the board. When isRandomized is false, it means we are in a NONRANDOM mode,
   * the third input: minOneFishNumOrFishNumOnTiles represent the number of fishes that will
   * be displayed on each tiles.
   *
   * @param width the width of the game board.
   * @param height the height of the game board.
   * @param minOneFishNumOrFishNumOnTiles represents different things based on the mode, please refer to the explanation above.
   * @param isRandomized is in RANDOM or NONRANDOM mode.
   */
  public FishModel(int width, int height, int minOneFishNumOrFishNumOnTiles, boolean isRandomized) {

    int fishNumOnTiles = minOneFishNumOrFishNumOnTiles;
    board = new ArrayList<ArrayList<FishTile>>();

    for (int i = 0; i < height; ++i) {
      ArrayList<FishTile> row = new ArrayList<FishTile>();
      board.add(row);
      for (int j = 0; j < width; ++j) {
        if (!isRandomized) {
          //In nonrandom mode, every tile has the same number of fish based on the input number.
          FishTile tile = new FishTile(fishNumOnTiles);
          row.add(tile);
        } else {
          //In random mode, first setting the all fish tiles with 2 to 5 fishes randomly, and
          //1-fish tiles will be set later.
            Random ran = new Random();
            int low = 2;
            int high = fishNumOnTiles + 1;
            int randomFishNum = ran.nextInt(high - low) + low;

            FishTile tile = new FishTile(randomFishNum);
            row.add(tile);
          }
      }
    }

    //setting 1-fish tiles.
    if(isRandomized) {
      int oneFishTilesNum = minOneFishNumOrFishNumOnTiles;
      setOneFishTiles(oneFishTilesNum);
    }
  }

  /**
   * Inputting the number of 1-fish tiles that wants to be set on the game board, and setting
   * the number of tiles on the board randomly to 1 fish.
   *
   * @param oneFishTilesNum int, the number of 1-fish tiles that wants to be set on the game board.
   */
  public void setOneFishTiles(int oneFishTilesNum){
    if(board != null) {
      int boardHeight = board.size();
      int boardWidth = board.get(0).size();
//      ArrayList<FishTile> oneFishTiles = new ArrayList<FishTile>();

      int xPos;
      int yPos;

      Random ran = new Random();
      xPos = ran.nextInt(boardWidth);
      yPos = ran.nextInt(boardHeight);

      FishTile oneFishTile = board.get(yPos).get(xPos);
      oneFishTile.setFishNum(minFishNum);
//      oneFishTiles.add(oneFishTile);


      for (int i = 1; i < oneFishTilesNum; ++i) {
        int newX = ran.nextInt(boardWidth);
        int newY = ran.nextInt(boardHeight);
        FishTile fishTile = board.get(newY).get(newX);

        while(fishTile.getFishNum() == minFishNum){
          newX = ran.nextInt(boardWidth);
          newY = ran.nextInt(boardHeight);
          fishTile = board.get(newY).get(newX);
        }

        fishTile = board.get(newY).get(newX);
        fishTile.setFishNum(minFishNum);
      }
    }
  }



  /**
   * Takes in a x position and a y position, which will be used for emptying the tile in the board,
   * setting its isEmpty attribute to true.
   *  @param xPos x position of the tile to be emptied.
   * @param yPos y position of the tile to be emptied.
   */
  public void emptyTile(int xPos, int yPos) {
    //when the game board is not empty
    if (board != null) {
      int boardWidth = board.get(0).size();
      int boardHeight = board.size();

      if (xPos > -1 && xPos < boardWidth && yPos > -1 && yPos < boardHeight) {
        board.get(yPos).get(xPos).setEmpty();
        System.out.println("Emptied.");
      } else {
        System.out.println("Error: Invalid position of a tile.");
      }
    } else {
      System.out.println("Error: Game board is empty.");
    }
  }

  /**
   * Receiving the x and y positions of the starting tile, and outputting all tiles on the game
   * board that it can reach based on the game rule. Valid moves are straight lines crossing
   * tiles' boarders instead of corners, and they stops when there is a hole in the line.
   *
   * @param startX x position of the starting tile.
   * @param startY y position of the starting tile.
   * @return a arraylist of FishTiles on the board that the current tile can move to.
   */
  public ArrayList<FishTile> getPossibleMoves(int startX, int startY){
    ArrayList<FishTile> possibleMoveTiles = new ArrayList<FishTile>();

    if(board != null) {
      int boardHeight = board.size();
      int boardWidth = board.get(0).size();

      FishTile fishTile = board.get(startY).get(startX);
      boolean isEmpty = fishTile.isEmpty();
      if(isEmpty) return possibleMoveTiles;

      //moving up & down
      boolean isStartYEven = startY % 2 == 0;
      int startYForLoop = isStartYEven ? 0 : 1;
      int endYForLoop = isStartYEven ? boardHeight - 2 : boardHeight - 1;
      for (int i = startYForLoop; i <= endYForLoop; i += 2){
        if(startY != i){
          fishTile = board.get(i).get(startX);
          isEmpty = fishTile.isEmpty();
          if(!isEmpty) {
            possibleMoveTiles.add(fishTile);
            System.out.println("Up & down: ");
            System.out.println(startX + " " + i);
          } else break;
        }
      }


      int counter = 0;

      //moving top-left
      for (int i = startY - 1; i >= 0; --i) {
        boolean isYEven = i % 2 == 0;
        counter = isYEven ? counter : counter + 1;
        if (startX - counter >= 0) {
          fishTile = board.get(i).get(startX - counter);
          isEmpty = fishTile.isEmpty();
          if(!isEmpty) {
            possibleMoveTiles.add(fishTile);
            System.out.println("Top Left:");
            System.out.println(startX - counter + " " + i);
          } else break;
        } else break;
      }
      counter = 0;

      //moving bottom-left
      for (int i = startY + 1; i <= boardHeight - 1; ++i){
        boolean isYEven = i % 2 == 0;
        counter = isYEven ? counter : counter + 1;
        if(startX - counter >= 0){
          fishTile = board.get(i).get(startX - counter);
          isEmpty = fishTile.isEmpty();
          if(!isEmpty) {
            possibleMoveTiles.add(fishTile);
            System.out.println("Bottom Left:");
            System.out.println(startX - counter + " " + i);
          } else break;
        } else break;
      }
      counter = 0;

      //moving bottom-right
      for (int i = startY + 1; i <= boardHeight - 1; ++i){
        boolean isYEven = i % 2 == 0;
        counter = !isYEven ? counter : counter + 1;
        if(startX + counter <= boardWidth - 1){
          fishTile = board.get(i).get(startX + counter);
          isEmpty = fishTile.isEmpty();
          if(!isEmpty) {
            possibleMoveTiles.add(fishTile);
            System.out.println("Bottom Right:");
            System.out.println(startX + counter + " " + i);
          } else break;;
        } else break;
      }
      counter = 0;

      //moving top-right
      for (int i = startY - 1; i >= 0; --i){
        boolean isYEven = i % 2 == 0;
        counter = !isYEven ? counter : counter + 1;
        if(startX + counter <= boardWidth - 1){
          fishTile = board.get(i).get(startX + counter);
          isEmpty = fishTile.isEmpty();
          if(!isEmpty) {
            possibleMoveTiles.add(fishTile);
            System.out.println("Top Right:");
            System.out.println(startX + counter + " " + i);
          } else break;
        } else break;
      }

    }

    return possibleMoveTiles;
  }


  /**
   * Generate a copy of current game board.
   *
   * @return a 2d ArrayList, a copy of the the current game board.
   */
  public ArrayList<ArrayList<FishTile>> getBoardCopy() {
    ArrayList<ArrayList<FishTile>> boardCopy = new ArrayList<ArrayList<FishTile>>();
    if (board != null) {
      for (ArrayList<FishTile> row : board) {
        ArrayList<FishTile> rowClone = (ArrayList<FishTile>) row.clone();
        boardCopy.add(rowClone);
      }
    }
    return boardCopy;
  }

}
