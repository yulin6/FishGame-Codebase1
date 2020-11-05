package common.models.actions;

import common.models.FishState;

/**
 * IAction is an interface that allows users the ability to apply a certain action to a certain
 * FishState using performAction() method.
 **/
public interface IAction {

  /**
   * A method that allows an action to happen on a Fish state based on a class that implements
   * the interface.
   *
   * @param fishState the FishState that is going to be modified.
   * @return a modified FishState.
   **/
  FishState performAction(FishState fishState) throws IllegalArgumentException;

}
