package common.models.Actions;

import common.models.FishState;
import common.models.Penguin;
import common.models.Player;
import java.util.ArrayList;

/** MovePenguinAction is a class that represent a movement on the Fish game that implements on the
 * IAction interface. In order to move penguins and modify fish states, there are 4 parameters that
 * needs to be inserted to move a penguin. A targetX representing the column of the fish board, a
 * targetY representing the row of the fish board, a penguin and a player.
 **/
public class MovePenguinAction implements IAction {

  private int startX;
  private int startY;
  private int targetX;
  private int targetY;
  private Penguin penguin;
  private Player player;

  /** MovePenguinAction constructor that acts as a mameMovement method that takes in a targetX,
   * targetY, penguin and player and creates a movement from these variables.
   * @param targetX the column on the fish board.
   * @param targetY the row on the fish board.
//   * @param penguin the penguin that is controlled by a player.
//   * @param player a player playing the game of Fish.
   **/
  public MovePenguinAction(int startX, int startY, int targetX, int targetY) {
    this.targetX = targetX;
    this.targetY = targetY;
    this.startX = startX;
    this.startY = startY;
//    this.penguin = penguin;
//    this.player = player;
  }

  @Override
  public FishState performAction(FishState fishState) throws IllegalArgumentException{
    ArrayList<Penguin> penguinsOnBoard = fishState.getPenguinsOnBoard();
    ArrayList<Player> players = fishState.getPlayersSortedByAgeAscend();
    Penguin currPenguin = getPenguinOnState(fishState);
    Player currPlayer = getPlayerOnState(fishState);
    FishState actionPerformedState = fishState.makeMovement(targetX, targetY,
            currPenguin, currPlayer);
    return actionPerformedState;
  }

  private Penguin getPenguinOnState(FishState fishState) {
    ArrayList<Penguin> penguinsOnBoard = fishState.getPenguinsOnBoard();
    for (Penguin penguin: penguinsOnBoard) {
      if (penguin.getXPos() == this.startX && penguin.getYPos() == this.startY) {
        return penguin;
      }
    }
    throw new IllegalArgumentException("Error: Penguin not Found in Position");
  }

  private Player getPlayerOnState(FishState fishState) {
    ArrayList<Player> players = fishState.getPlayersSortedByAgeAscend();
    Penguin currPenguin = this.getPenguinOnState(fishState);
    for (Player player: players) {
      if (player.getPenguinColor() == currPenguin.getColor()) {
        return player;
      }
    }
    throw new IllegalArgumentException("Error: Player not Found");
  }


}

