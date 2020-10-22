package models.Actions;

import models.FishState;

public interface IAction {

  FishState performAction(FishState fishState) throws IllegalArgumentException;

}
