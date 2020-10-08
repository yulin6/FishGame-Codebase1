package models;

/**
 * Class of FishTile, which contains how a fish tile is constructed.
 */
public class FishTile {

  private boolean isEmpty;
  private int fishNum;
//  private int xPos;
//  private int yPos;

  /**
   * Constructor of the FishTile class, takes in the fish number that will be displayed on the
   * tile.
   *
   * @param fishNum a integer of fish number that will be displayed on the tile.
   */
  public FishTile(int fishNum) {
    isEmpty = false;
    this.fishNum = fishNum;
//    this.xPos = xPos;
//    this.yPos = yPos;
  }

  /**
   * is the current tile empty.
   *
   * @return boolean of whether is the current tile empty.
   */
  public boolean isEmpty() {
    return isEmpty;
  }

  /**
   * Setting the current tile to empty.
   */
  public void setEmpty() {
    isEmpty = true;
  }

  /**
   * get the number of fish on the tile.
   *
   * @return an int represent the number of fish on the tile.
   */
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
