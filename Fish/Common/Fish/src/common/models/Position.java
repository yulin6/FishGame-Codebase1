package common.models;

/** Position class that represent a position on the board, the x represent the column and the y
 * represent the row on the Fish board.
 **/
public class Position {
  int x;
  int y;

  /** A position constructor that takes in an integer value x and y that represent the column and
   * the row on the Fish board respectively.
   **/
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** Gets the x or column on the board as an int.
   * @return an integer representing a column on the board.
   **/
  public int getX() {
    return x;
  }

  /** Gets the y or row on the board as an int.
   * @return an integer representing a row on the board.
   **/
  public int getY() {
    return y;
  }

}
