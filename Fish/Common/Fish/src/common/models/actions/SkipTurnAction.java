package common.models.actions;

import common.models.FishState;

/**
 * SkipTurnAction is an action that is used for skipping the turn of the current player. The performAction() will
 * output the next state of the game for the next player.
 */
public class SkipTurnAction implements IAction {

    @Override
    public FishState performAction(FishState fishState) throws IllegalArgumentException {
        return fishState.createNextState();
    }

}
