package models;

import java.util.ArrayList;
import java.util.Random;

public class FishBoard {
  ArrayList<ArrayList<FishTile>> board = new ArrayList<ArrayList<FishTile>>();
  boolean isRandomized;
  int maxFishNum;


  public FishBoard(int width, int height, int maxFishNum, boolean isRandomized){
    this.isRandomized = isRandomized;
    this.maxFishNum = maxFishNum;

    for(int i = 0; i < width; ++i){
      ArrayList<FishTile> row = new ArrayList<FishTile>();
      for (int j = 0; j < height; ++j){
        if(isRandomized) {
          FishTile tile = new FishTile(maxFishNum, i, j);
          row.add(tile);
        } else {
          Random ran = new Random();
          int low = 1;
          int high = maxFishNum + 1;
          int randomFishNum = ran.nextInt(high - 1) + low;

          FishTile tile = new FishTile(randomFishNum, i, j);
          row.add(tile);
        }
      }
    }
  }

  public void removeTile(int xPos, int yPos){
    int boardWidth = board.size();
    int boardHeight = board.get(0).size();

    if(xPos > -1 && xPos <= boardWidth && yPos > -1 && yPos <= boardHeight) {
      board.get(yPos).get(xPos).setEmpty();
    } else {
      System.out.println("invalid position of tile");
    }
  }











}
