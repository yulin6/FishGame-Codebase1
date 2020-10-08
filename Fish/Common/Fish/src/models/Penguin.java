package models;

/**
 * a class that represents the penguin in the game.
 */
public class Penguin {

  int playerId;
  PenguinColor color;

  /**
   * Create a penguin based on the input playerId and penguinColor.
   *
   * @param playerId int that represents the playerId
   * @param color color of the penguin.
   */
  public Penguin(int playerId, PenguinColor color) {
    this.playerId = playerId;
    this.color = color;
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

}
