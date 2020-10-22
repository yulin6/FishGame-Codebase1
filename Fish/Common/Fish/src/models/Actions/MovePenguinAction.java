package models.Actions;

import models.FishState;
import models.Penguin;
import models.Player;

public class MovePenguinAction implements IAction{

  private int targetX;
  private int targetY;
  private Penguin penguin;
  private Player player;


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
