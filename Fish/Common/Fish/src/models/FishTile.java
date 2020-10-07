package models;

import javax.swing.Spring;

public class FishTile {

  private boolean isEmpty;
  private int fishNum;
//  private int xPos;
//  private int yPos;

  public FishTile(int fishNum){
    isEmpty = false;
    this.fishNum = fishNum;
//    this.xPos = xPos;
//    this.yPos = yPos;
  }

  public boolean isEmpty() {
    return isEmpty;
  }

  public void setEmpty() {
    isEmpty = true;
  }

  public int getFishNum() {
    return fishNum;
  }

//  public int getxPos() {
//    return xPos;
//  }
//
//  public int getyPos() {
//    return yPos;
//  }



}
