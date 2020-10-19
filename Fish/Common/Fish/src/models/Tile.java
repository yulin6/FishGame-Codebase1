package models;

/**
 * Class of Tile, which contains how a fish tile is constructed.
 */
public class Tile {

  private boolean isEmpty;
  private int fishNum;
  private Penguin penguin;


  /**
   * Constructor of the Tile class, takes in the fish number that will be displayed on the
   * tile.
   *
   * @param fishNum a integer of fish number that will be displayed on the tile.
   */
  public Tile(int fishNum) {
    isEmpty = false;
    this.fishNum = fishNum;
  }

  private Tile() {

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

  /**
   * set the number of fish on the tile.
   *
   * @param fishNum the number of fish to be set on the tile.
   */
  public void setFishNum(int fishNum){
    this.fishNum = fishNum;
  }


  /**
   * getting the penguin that's currently on the tile
   */
  public Penguin getPenguin() {
    return penguin;
  }

  /**
   * Setting the penguin that's currently on the tile
   *
   * @param penguin a Penguin.
   */
  public void setPenguin(Penguin penguin){
    this.penguin = penguin;
  }

}
