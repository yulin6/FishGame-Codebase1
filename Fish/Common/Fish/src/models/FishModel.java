package models;

import java.util.ArrayList;
import java.util.Random;

/**
 * The model class of the fish game. Main methods are emptyTile, isPossibleMove and getBoardCopy.
 */
public class FishModel {

  private ArrayList<ArrayList<FishTile>> board = new ArrayList<ArrayList<FishTile>>();
  private boolean isRandomized;
  private int maxFishNum;


  /**
   * The constructor of FishModel class, takes in width, height, maxFishNum and a boolean
   * isRandomized to generate the board.
   *
   * @param width width of the game board.
   * @param height height of the game board.
   * @param maxFishNum max fish number of a tile.
   * @param isRandomized should the number of fishes on a tile be randomized from 1 to maxFishNum.
   */
  public FishModel(int width, int height, int maxFishNum, boolean isRandomized) {
    this.isRandomized = isRandomized;
    this.maxFishNum = maxFishNum;

    for (int i = 0; i < height; ++i) {
      ArrayList<FishTile> row = new ArrayList<FishTile>();
      board.add(row);
      for (int j = 0; j < width; ++j) {
        if (!isRandomized) {
          FishTile tile = new FishTile(maxFishNum);
          row.add(tile);
        } else {
          //
          Random ran = new Random();
          int low = 1;
          int high = maxFishNum + 1;
          int randomFishNum = ran.nextInt(high - 1) + low;

          FishTile tile = new FishTile(randomFishNum);
          row.add(tile);
        }
      }
    }
//    System.out.println(board.size());
  }

  /**
   * A constructor that construct a empty game board for testing.
   */
  public FishModel() {
  }

  /**
   * Takes in a x position and a y position, which will be used for emptying the tile in the board,
   * setting its isEmpty attribute to true.
   *  @param xPos x position of the tile to be emptied.
   * @param yPos y position of the tile to be emptied.
   */
  public void emptyTile(int xPos, int yPos) {
    //when the game board is not empty
    if (board.size() != 0) {
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
   * Takes in the x and y positions of starting tile and targeting tile, and determine if they are
   * in a "straight line", which means the line connects the two tiles can only be crossing borders;
   * and if they or any of the tiles in between them are empty, return false. Otherwise, return
   * true.
   *
   * @param startX x position of the starting tile.
   * @param startY y position of the starting tile.
   * @param targetX x position of the targeting tile.
   * @param targetY y position of the targeting tile.
   * @return a boolean determines that if the move is valid.
   */
  public boolean isValidMove(int startX, int startY, int targetX, int targetY) {
    int counter = 0;
    ArrayList<FishTile> possibleMoveTiles = new ArrayList<FishTile>();

    //example: 0 0 0 0
    if (startX == targetX && startY == targetY) {
      return false; //true or false, depends on design choice.
    }
    if (board.size() != 0) {//When the board is not empty
      int height = board.size();
      int width = board.get(0).size();
      if (startX < 0 || startY < 0 || startX > width - 1 || startY > height - 1 ||
          targetX < 0 || targetY < 0 || targetX > width - 1 || targetY > height - 1) {//outside of the board
        return false;
      } else {
        if (startX == targetX) {//In the same column
          boolean isNotBothEvenOrOdd =
              (startY % 2 == 0 && targetY % 2 != 0) || (startY % 2 != 0 && targetY % 2 == 0);
          if (startY > targetY) {//Moving to same x smaller y, example: 0 1 0 0
            if (startY - targetY == 1) {//example: 0 1 0 0
              FishTile fishTile = board.get(targetY).get(targetX);
              possibleMoveTiles.add(fishTile);
            } else if (isNotBothEvenOrOdd) {//example: 1 5 1 0
              return false;
            } else {//Moving straight up, example: 0 2 0 0
              for (int i = targetY; i <= startY; i += 2) {
                FishTile fishTile = board.get(i).get(targetX);
                possibleMoveTiles.add(fishTile);
              }
            }
          } else {//Moving to same x larger y, example: 0 0 0 1
            if (targetY - startY == 1) {//example: 0 1 0 2
              FishTile fishTile = board.get(targetY).get(targetX);
              possibleMoveTiles.add(fishTile);
            } else if (isNotBothEvenOrOdd) {//example: 1 0 1 5
              return false;
            } else {//Moving straight down, example: 0 0 0 2
              for (int i = startY; i <= targetY; i += 2) {
                FishTile fishTile = board.get(i).get(targetX);
                possibleMoveTiles.add(fishTile);
              }
            }
          }
        } else if (startY == targetY) {//Moving left or right, example: 0 0 1 0
          return false;
        } else if (startX > targetX && startY > targetY) {//Moving to top left, example: 1 2 0 0
          for (int i = startY; i >= targetY; --i) {
            if (startX - counter >= 0) {//Avoid indexOutOfBoundException, example: 1 4 0 1
              if (i % 2 == 0) {//example: 1 2 0 0
                FishTile fishTile = board.get(i).get(startX - counter);
                possibleMoveTiles.add(fishTile);
                ++counter;
              } else {//example: 1 3 0 0
                FishTile fishTile = board.get(i).get(startX - counter);
                possibleMoveTiles.add(fishTile);
              }
            }
          }

        } else if (startX > targetX && startY < targetY) {//Moving to bottom left, example: 1 2 0 4
          for (int i = startY; i <= targetY; ++i) {
            if (startX - counter >= 0) {//Avoid indexOutOfBoundException, example: 1 2 0 5
              if (i % 2 == 0) {//example: 1 2 0 4
                FishTile fishTile = board.get(i).get(startX - counter);
                possibleMoveTiles.add(fishTile);
                ++counter;
              } else {//example: 0 3 0 4
                FishTile fishTile = board.get(i).get(startX - counter);
                possibleMoveTiles.add(fishTile);
              }
            }
          }
        } else if (startX < targetX && startY < targetY) {//Moving to bottom right, example: 1 2 2 4
          for (int i = startY; i <= targetY; ++i) {
            if (startX + counter < width) {//Avoid indexOutOfBoundException, example: 2 3 3 6, width 4 height 8
              if (i % 2 == 0) {//example: 1 2 2 4
                FishTile fishTile = board.get(i).get(startX + counter);
                possibleMoveTiles.add(fishTile);
              } else {//example: 0 1 2 4
                FishTile fishTile = board.get(i).get(startX + counter);
                possibleMoveTiles.add(fishTile);
                ++counter;
              }
            }
          }
        } else if (startX < targetX && startY > targetY) {//Moving to top right, example: 1 2 2 0
          for (int i = startY; i >= targetY; --i) {
            if (startX + counter
                < width) {//Avoid indexOutOfBoundException, example: 1 6 2 0, width 4 height 8
              if (i % 2 == 0) {//example: 1 2 2 0
                FishTile fishTile = board.get(i).get(startX + counter);
                possibleMoveTiles.add(fishTile);
              } else {//example: 0 3 2 0
                FishTile fishTile = board.get(i).get(startX + counter);
                possibleMoveTiles.add(fishTile);
                ++counter;
              }
            }
          }
        }
      }
      FishTile targetFishTile = board.get(targetY).get(targetX);
      //It is not in the "straight line", example: 1 2 2 7
      if (!possibleMoveTiles.contains(targetFishTile)) {
        return false;
      }

      for (FishTile tile : possibleMoveTiles) {
        //Any tiles in the "straight line" can't be a hole.
        if (tile.isEmpty()) {
          return false;
        }
      }
    } else {
      return false; //when the board is empty.
    }

    return true;
  }


  /**
   * Generate a copy of current game board.
   *
   * @return a 2d ArrayList, a copy of the the current game board.
   */
  public ArrayList<ArrayList<FishTile>> getBoardCopy() {
    ArrayList<ArrayList<FishTile>> boardCopy = new ArrayList<ArrayList<FishTile>>();
    if (board.size() != 0) {
      for (ArrayList<FishTile> row : board) {
        ArrayList<FishTile> rowClone = (ArrayList<FishTile>) row.clone();
        boardCopy.add(rowClone);
      }
    }
    return boardCopy;
  }

}
