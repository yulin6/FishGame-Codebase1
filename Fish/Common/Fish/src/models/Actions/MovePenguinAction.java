package models.Actions;

import models.FishState;
import models.Penguin;
import models.Player;

/** MovePenguinAction is a class that represent a movement on the Fish game that implements on the
 * IAction interface. In order to move penguins and modify fish states, there are 4 parameters that
 * needs to be inserted to move a penguin. A targetX representing the column of the fish board, a
 * targetY representing the row of the fish board, a penguin and a player.
 **/
public class MovePenguinAction implements IAction {

  private int targetX;
  private int targetY;
  private Penguin penguin;
  private Player player;

  /** MovePenguinAction constructor that acts as a mameMovement method that takes in a targetX,
   * targetY, penguin and player and creates a movement from these variables.
   * @param targetX the column on the fish board.
   * @param targetY the row on the fish board.
   * @param penguin the penguin that is controlled by a player.
   * @param player a player playing the game of Fish.
   **/
  public MovePenguinAction(int targetX, int targetY, Penguin penguin, Player player) {
    this.targetX = targetX;
    this.targetY = targetY;
    this.penguin = penguin;
    this.player = player;
  }

  @Override
  public FishState performAction(FishState fishState) throws IllegalArgumentException{
    FishState actionPerformedState = fishState.makeMovement(targetX, targetY, penguin, player);
    return actionPerformedState;
  }
}
