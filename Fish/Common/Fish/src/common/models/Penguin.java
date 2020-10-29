package common.models;

/**
 * a class that represents the penguin in the game.
 */
public class Penguin {

  //  Player PlayerX;
  private PenguinColor color;
  private long id;
  private int xPos;
  private int yPos;

  /**
   * Create a penguin based on the input playerId and penguinColor.
   *
   * @param color color of the penguin.
   */
  public Penguin( PenguinColor color) {
//    this.PlayerX = PlayerX;
    this.color = color;
    this.id = System.nanoTime();
  }

  //A empty constructor that's needed for Kryo library
  private Penguin(){

  }


  public long getId() {
    return id;
  }

  /**
   * get the color of the penguin.
   *
   * @return PenguinColor enum.
   */
  public PenguinColor getColor() {
    return color;
  }

  /**
   * set the color of the penguin.
   *
   * @param color PenguinColor enum.
   */
  public void setColor(PenguinColor color) {
    this.color = color;
  }

  /**
   * gets the x position of the penguin.
   * @return the x position or the column number of the penguin.
   */
  public int getXPos() {
    return xPos;
  }

  /**
   * sets the x position or the column number of the penguin.
   * @param xPos or column number of the penguin.
   */
  public void setXPos(int xPos) {
    this.xPos = xPos;
  }

  /**
   * gets the y position of the penguin.
   * @return the y position or the row number of the penguin.
   */
  public int getYPos() {
    return yPos;
  }

  /**
   * sets the y position or the row number of the penguin.
   * @param yPos or row number of the penguin.
   */
  public void setYPos(int yPos) {
    this.yPos = yPos;
  }

}
