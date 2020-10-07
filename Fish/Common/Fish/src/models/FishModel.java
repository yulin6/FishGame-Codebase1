package models;

import java.util.ArrayList;
import java.util.Random;

public class FishModel {

  private ArrayList<ArrayList<FishTile>> board = new ArrayList<ArrayList<FishTile>>();
  private boolean isRandomized;
  private int maxFishNum;


  public FishModel(int width, int height, int maxFishNum, boolean isRandomized){
    this.isRandomized = isRandomized;
    this.maxFishNum = maxFishNum;

    for(int i = 0; i < height; ++i){
      ArrayList<FishTile> row = new ArrayList<FishTile>();
      board.add(row);
      for (int j = 0; j < width; ++j){
        if(!isRandomized) {
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

  public void emptyTile(int xPos, int yPos){
    int boardWidth = board.size();
    int boardHeight = board.get(0).size();

    if(xPos > -1 && xPos < boardWidth && yPos > -1 && yPos < boardHeight) {
      board.get(yPos).get(xPos).setEmpty();
      System.out.println("Emptied.");
    } else {
      System.out.println("Error: Invalid position of a tile.");
    }
  }

  public boolean isPossibleMove(int startX, int startY, int targetX, int targetY) {
    int counter = 0;
    ArrayList<FishTile> possibleMoveTiles = new ArrayList<FishTile>();

    //example: 0 0 0 0
    if(startX == targetX && startY == targetY) return true;

    if (board.size() != 0) {

      int height = board.size();
      int width = board.get(0).size();
      if (startX < 0 || startY < 0 || startX > width - 1 || startY > height - 1 ||
          targetX < 0 || targetY < 0 || targetX > width - 1 || targetY > height - 1) {
        return false;
      } else {
        if (startX == targetX) {
          if (startY > targetY) {//Moving to same x lower y, example: 0 1 0 0
            if(startY - targetY == 1){
              FishTile fishTile = board.get(targetY).get(targetX);
              possibleMoveTiles.add(fishTile);
            } else {//Moving up, example: 0 2 0 0
              for (int i = targetY; i <= startY; i += 2) {
                FishTile fishTile = board.get(i).get(targetX);
                possibleMoveTiles.add(fishTile);
              }
            }
          } else {//Moving to same x higher y, example: 0 0 0 1
            if (targetY - startY == 1) {
              FishTile fishTile = board.get(targetY).get(targetX);
              possibleMoveTiles.add(fishTile);
            } else {//Moving down, example: 0 0 0 2
              for (int i = startY; i <= targetY; i += 2) {
                FishTile fishTile = board.get(i).get(targetX);
                possibleMoveTiles.add(fishTile);
              }
            }
          }
        } else if (startY == targetY) {//Moving left or right, example: 0 0 1 0
          return false;
        } else if(startX > targetX && startY > targetY) {//Moving to top left, example: 1 2 0 0
            for (int i = startY; i >= targetY; --i) {
              if(i % 2 == 0) {//example: 1 2 0 0
                FishTile fishTile = board.get(i).get(startX - counter);
                possibleMoveTiles.add(fishTile);
                ++counter;
              } else {//example: 1 3 0 0
                FishTile fishTile = board.get(i).get(startX - counter);
                possibleMoveTiles.add(fishTile);
              }
            }

        } else if(startX > targetX && startY < targetY){//Moving to bottom left, example: 1 2 0 4
          for (int i = startY; i <= targetY; ++i){
            if(i % 2 == 0) {//example: 1 2 0 4
              FishTile fishTile = board.get(i).get(startX - counter);
              possibleMoveTiles.add(fishTile);
              ++counter;
            } else {//example: 1 3 0 4
              FishTile fishTile = board.get(i).get(startX - counter);
              possibleMoveTiles.add(fishTile);
            }
          }
        } else if(startX < targetX && startY < targetY){//Moving to bottom right, example: 1 2 2 4
          for (int i = startY; i <= targetY; ++i){
            if(i % 2 == 0){//example: 1 2 2 4
              FishTile fishTile = board.get(i).get(startX + counter);
              possibleMoveTiles.add(fishTile);
            } else {//example: 0 1 2 4
              FishTile fishTile = board.get(i).get(startX + counter);
              possibleMoveTiles.add(fishTile);
              ++counter;
            }
          }
        } else if (startX < targetX && startY > targetY){//Moving to top right, example: 1 2 2 0
          for(int i = startY; i >= targetY; --i){
            if(i % 2 == 0){//example: 1 2 2 0
              FishTile fishTile = board.get(i).get(startX + counter);
              possibleMoveTiles.add(fishTile);
            } else{//example: 0 3 2 0
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
    if(!possibleMoveTiles.contains(targetFishTile)) return false;

    for (FishTile tile: possibleMoveTiles){
      //Any tiles in the "straight line" can't be a hole.
      if(tile.isEmpty()) return false;
    }

    return true;

  }


  public ArrayList<ArrayList<FishTile>> getBoardCopy() {

    ArrayList<ArrayList<FishTile>> boardCopy = new ArrayList<ArrayList<FishTile>>();
    if(board.size() != 0) {
      for (ArrayList<FishTile> row : board) {
        ArrayList<FishTile> rowClone = (ArrayList<FishTile>) row.clone();
        boardCopy.add(rowClone);
      }
    }
    return boardCopy;
  }

  public int getMaxFishNum() {
    return maxFishNum;
  }


}
