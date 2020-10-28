package common.models;

import java.util.ArrayList;
import java.util.Random;

/**
 * The game board class of the fish game. It stores the game board representation, which is a
 * ArrayList of ArrayList of Fish Tiles. There are two types (modes) of game board, one (RANDOM)is
 * having randomly number of fishes on each tiles with a minimum number of 1-fish tiles. Another one
 * (NONRANDOM) has each tiles with the same number of fish numbers. The common.Main methods are emptyTile,
 * getPossibleMoves and getBoardCopy.
 */
public class FishModel {

  private ArrayList<ArrayList<Tile>> board;
  private int maxFishNumOnOneTile = 5;
  private int minFishNumOnOneTile = 1;


  /**
   * A constructor that construct a empty game board for testing.
   */
  public FishModel() {
  }


  /**
   * The main constructor of the fish model. Width and height are used for determine the size of the
   * game board. When isRandomized is true, it means we are in a RANDOM mode, the third input:
   * minOneFishNumOrFishNumOnTiles represent the minimum number of 1-fish tiles on the board. When
   * isRandomized is false, it means we are in a NONRANDOM mode, the third input:
   * minOneFishNumOrFishNumOnTiles represent the number of fishes that will be displayed on each
   * tiles.
   *
   * @param width the width of the game board.
   * @param height the height of the game board.
   * @param minOneFishNumOrFishNumOnTiles represents different things based on the mode, please
   * refer to the explanation above.
   * @param isRandomized is in RANDOM or NONRANDOM mode.
   */
  public FishModel(int width, int height, int minOneFishNumOrFishNumOnTiles, boolean isRandomized) {

    board = new ArrayList<ArrayList<Tile>>();

    for (int i = 0; i < height; ++i) {
      ArrayList<Tile> row = new ArrayList<Tile>();
      board.add(row);
      for (int j = 0; j < width; ++j) {
        if (!isRandomized) {
          //In nonrandom mode, every tile has the same number of fish based on the input number.
          int fishNumOnTiles = minOneFishNumOrFishNumOnTiles;
          Tile tile = new Tile(fishNumOnTiles);
          row.add(tile);
        } else {
          //In random mode, first setting the all fish tiles with 2 to 5 fishes randomly, and
          //1-fish tiles will be set later.
          Random ran = new Random();
          int low = 2;
          int high = maxFishNumOnOneTile + 1;
          int randomFishNum = ran.nextInt(high - low) + low;

          Tile tile = new Tile(randomFishNum);
          row.add(tile);
        }
      }
    }

    //setting 1-fish tiles.
    if (isRandomized) {
      int oneFishTilesNum = minOneFishNumOrFishNumOnTiles;
      setOneFishTiles(oneFishTilesNum);
    }
  }

  /**
   * Inputting the number of 1-fish tiles that wants to be set on the game board, and setting the
   * number of tiles on the board randomly to 1 fish.
   *
   * @param oneFishTilesNum int, the number of 1-fish tiles that wants to be set on the game board.
   */
  public void setOneFishTiles(int oneFishTilesNum) {
    if (board != null) {
      int boardHeight = board.size();
      int boardWidth = board.get(0).size();
//      ArrayList<Tile> oneFishTiles = new ArrayList<Tile>();

      int xPos;
      int yPos;

      Random ran = new Random();
      xPos = ran.nextInt(boardWidth);
      yPos = ran.nextInt(boardHeight);

      Tile oneFishTile = board.get(yPos).get(xPos);
      oneFishTile.setFishNum(minFishNumOnOneTile);
//      oneFishTiles.add(oneFishTile);

      for (int i = 1; i < oneFishTilesNum; ++i) {
        int newX = ran.nextInt(boardWidth);
        int newY = ran.nextInt(boardHeight);
        Tile tile = board.get(newY).get(newX);

        while (tile.getFishNum() == minFishNumOnOneTile) {
          newX = ran.nextInt(boardWidth);
          newY = ran.nextInt(boardHeight);
          tile = board.get(newY).get(newX);
        }

        tile = board.get(newY).get(newX);
        tile.setFishNum(minFishNumOnOneTile);
      }
    }
  }


  /**
   * Takes in a x position and a y position, which will be used for emptying the tile in the board,
   * setting its isEmpty attribute to true.
   *
   * @param xPos x position of the tile to be emptied.
   * @param yPos y position of the tile to be emptied.
   * @throws IllegalArgumentException throw an exception when the input argument is invalid.
   */
  public void emptyTile(int xPos, int yPos) throws IllegalArgumentException {
    inputPosChecking(xPos, yPos);
    board.get(yPos).get(xPos).setToHole();
  }

  /**
   * If the board is empty or the input tile position is out of the board, throw the exception.
   *
   * @param xPos x position of the tile.
   * @param yPos y position of the tile.
   * @throws IllegalArgumentException If the board is empty or the input tile position is out of the
   * board, throw the exception.
   */
  private void inputPosChecking(int xPos, int yPos) throws IllegalArgumentException {
    if (board != null) {
      int boardWidth = board.get(0).size();
      int boardHeight = board.size();
      if (xPos < 0 || xPos >= boardWidth || yPos < 0 || yPos >= boardHeight) {
        throw new IllegalArgumentException("Error: Invalid position of a tile.");
      }
    } else {
      throw new IllegalArgumentException("Error: Game board is empty.");
    }
  }

  /**
   * Receiving the x and y positions of the starting tile, and outputting all tiles on the game
   * board that it can reach based on the game rule. Valid moves are straight lines crossing tiles'
   * boarders instead of corners, and they stops when there is a hole in the line.
   *
   * @param startX x position of the starting tile.
   * @param startY y position of the starting tile.
   * @return a arraylist of FishTiles on the board that the current tile can move to.
   */
  public ArrayList<Tile> getPossibleMoves(int startX, int startY) throws IllegalArgumentException {

    inputPosChecking(startX, startY);
    ArrayList<Tile> possibleMoveTiles = new ArrayList<Tile>();

    int boardHeight = board.size();
    int boardWidth = board.get(0).size();

    Tile tile = board.get(startY).get(startX);
    boolean isEmpty = tile.isHole();
    if (isEmpty) {
      return possibleMoveTiles;
    }

    //Moving up
    boolean isStartYEven = startY % 2 == 0;
    int topYForLoop = isStartYEven ? 0 : 1;
//    boolean isHeightEven = boardHeight % 2 == 0;
//    int endYForLoop = isStartYEven && isHeightEven ? boardHeight - 2 : boardHeight - 1;
    for (int i = startY; i >= topYForLoop; i -= 2) {
      if (startY != i) {
        tile = board.get(i).get(startX);
        isEmpty = tile.isHole();
        boolean hasPenguin = tile.getPenguin() != null;
        if (!isEmpty && !hasPenguin) {
          possibleMoveTiles.add(tile);
//          System.out.println("north");
//          System.out.println(startX + " " + i);
        } else {
          break;
        }
      }
    }

    int counter = 0;

    //moving top-right
    for (int i = startY - 1; i >= 0; --i) {
      boolean isYEven = i % 2 == 0;
      counter = !isYEven ? counter : counter + 1;
      if (startX + counter <= boardWidth - 1) {
        tile = board.get(i).get(startX + counter);
        isEmpty = tile.isHole();
        boolean hasPenguin = tile.getPenguin() != null;
        if (!isEmpty && !hasPenguin) {
          possibleMoveTiles.add(tile);
//          System.out.println("northeast");
//          System.out.println(startX + counter + " " + i);
        } else {
          break;
        }
      } else {
        break;
      }
    }

    counter = 0;

    //moving bottom-right
    for (int i = startY + 1; i <= boardHeight - 1; ++i) {
      boolean isYEven = i % 2 == 0;
      counter = !isYEven ? counter : counter + 1;
      if (startX + counter <= boardWidth - 1) {
        tile = board.get(i).get(startX + counter);
        isEmpty = tile.isHole();
        boolean hasPenguin = tile.getPenguin() != null;
        if (!isEmpty && !hasPenguin) {
          possibleMoveTiles.add(tile);
//          System.out.println("southeast");
//          System.out.println(startX + counter + " " + i);
        } else {
          break;
        }
        ;
      } else {
        break;
      }
    }
    counter = 0;

    //moving down
    boolean isHeightEven = boardHeight % 2 == 0;
    int endYForLoop = isStartYEven && isHeightEven ? boardHeight - 2 : boardHeight - 1;
    for (int i = startY; i <= endYForLoop; i += 2) {
      if (startY != i) {
        tile = board.get(i).get(startX);
        isEmpty = tile.isHole();
        boolean hasPenguin = tile.getPenguin() != null;
        if (!isEmpty && !hasPenguin) {
          possibleMoveTiles.add(tile);
//          System.out.println("south");
//          System.out.println(startX + " " + i);
        } else {
          break;
        }
      }
    }

    counter = 0;

    //moving bottom-left
    for (int i = startY + 1; i <= boardHeight - 1; ++i) {
      boolean isYEven = i % 2 == 0;
      counter = isYEven ? counter : counter + 1;
      if (startX - counter >= 0) {
        tile = board.get(i).get(startX - counter);
        isEmpty = tile.isHole();
        boolean hasPenguin = tile.getPenguin() != null;
        if (!isEmpty && !hasPenguin) {
          possibleMoveTiles.add(tile);
//          System.out.println("southwest");
//          System.out.println(startX - counter + " " + i);
        } else {
          break;
        }
      } else {
        break;
      }
    }
    counter = 0;

    //moving top-left
    for (int i = startY - 1; i >= 0; --i) {
      boolean isYEven = i % 2 == 0;
      counter = isYEven ? counter : counter + 1;
      if (startX - counter >= 0) {
        tile = board.get(i).get(startX - counter);
        isEmpty = tile.isHole();
        boolean hasPenguin = tile.getPenguin() != null;
        if (!isEmpty && !hasPenguin) {
          possibleMoveTiles.add(tile);
//          System.out.println("northwest");
//          System.out.println(startX - counter + " " + i);
        } else {
          break;
        }
      } else {
        break;
      }
    }
//      counter = 0;

    return possibleMoveTiles;
  }


  /**
   * Generate a copy of current game board.
   *
   * @return a 2d ArrayList, a copy of the the current game board.
   */
  public ArrayList<ArrayList<Tile>> getBoard() {
    return this.board;
//    ArrayList<ArrayList<Tile>> boardCopy = new ArrayList<ArrayList<Tile>>();
//    if (board != null) {
//      for (ArrayList<Tile> row : board) {
//        ArrayList<Tile> rowClone = (ArrayList<Tile>) row.clone();
//        boardCopy.add(rowClone);
//      }
//    }
//    return boardCopy;
  }

//  public ArrayList<ArrayList<Tile>> getBoardCopy() {
//    Kyro kyro = new Kyro();
//  }


}
