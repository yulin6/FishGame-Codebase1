package models;

/**
 * a class that represents the penguin in the game.
 */
public class Penguin {

//  Player player;
  PenguinColor color;
  int xPos;
  int yPos;

  /**
   * Create a penguin based on the input playerId and penguinColor.
   *
   * @param color color of the penguin.
   */
  public Penguin( PenguinColor color) {
//    this.player = player;
    this.color = color;
  }

//  public Player getPlayer() {
//    return player;
//  }

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

  public int getXPos() {
    return xPos;
  }

  public void setXPos(int xPos) {
    this.xPos = xPos;
  }

  public int getYPos() {
    return yPos;
  }

  public void setYPos(int yPos) {
    this.yPos = yPos;
  }

}
