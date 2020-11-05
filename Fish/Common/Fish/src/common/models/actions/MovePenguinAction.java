package common.models.actions;

import common.models.FishState;
import common.models.Penguin;
import common.models.PlayerInfo;
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


  /**
   * MovePenguinAction constructor that acts as a mameMovement method that takes in a targetX,
   * targetY, penguin and player and creates a movement from these variables.
   * @param startX x coordinate of the start position in the move action.
   * @param startY y coordinate of the start position in the move action.
   * @param targetX x coordinate of the target position in the move action.
   * @param targetY y coordinate of the target position in the move action.
   */
  public MovePenguinAction(int startX, int startY, int targetX, int targetY) {
    this.targetX = targetX;
    this.targetY = targetY;
    this.startX = startX;
    this.startY = startY;
  }

  @Override
  public FishState performAction(FishState fishState) throws IllegalArgumentException{

    Penguin currPenguin = getPenguinOnState(fishState);
    PlayerInfo currPlayerInfo = getPlayerOnState(fishState);
    FishState actionPerformedState = fishState.makeMovement(targetX, targetY,
            currPenguin, currPlayerInfo);
    return actionPerformedState;
  }

  /**
   * Gets the penguin on the board based on the FishState given in order to perform an action
   * to that specific penguin based on its current x and y position.
   * @param fishState is the current state of the game.
   * @return a penguin that is going to be moved.
   */
  private Penguin getPenguinOnState(FishState fishState) {
    ArrayList<Penguin> penguinsOnBoard = fishState.getPenguinsOnBoard();
    for (Penguin penguin: penguinsOnBoard) {
      if (penguin.getXPos() == this.startX && penguin.getYPos() == this.startY) {
        return penguin;
      }
    }
    throw new IllegalArgumentException("Error: Penguin not Found in Position");
  }

  /**
   * Get the player on the board corresponding to the current penguin that is about to be moved.
   * @param fishState is the current state of the game.
   * @return a player that controls a specific penguin.
   */
  private PlayerInfo getPlayerOnState(FishState fishState) {
    ArrayList<PlayerInfo> playerInfos = fishState.getAllPlayerInfos();
    Penguin currPenguin = this.getPenguinOnState(fishState);
    for (PlayerInfo playerInfo : playerInfos) {
      if (playerInfo.getPenguinColor() == currPenguin.getColor()) {
        return playerInfo;
      }
    }
    throw new IllegalArgumentException("Error: Player not Found");
  }



  /**
   * The getter method forms the startX, startY, targetX and targetY into a arraylist of Integer,
   * which can be used for checking the starting and targeting positions of the move action.
   *
   * @return arraylist of Integer which is formed by startX, startY, targetX and targetY
   */
  public ArrayList<Integer> getMoveActionPositions(){
    ArrayList<Integer> positions = new ArrayList<>();
    positions.add(startX);
    positions.add(startY);
    positions.add(targetX);
    positions.add(targetY);
    return positions;
  }

  public int getStartX() {
    return startX;
  }

  public int getStartY() {
    return startY;
  }

  public int getTargetX() {
    return targetX;
  }

  public int getTargetY() {
    return targetY;
  }


}

