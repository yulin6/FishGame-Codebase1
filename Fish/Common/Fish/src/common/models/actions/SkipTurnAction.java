package common.models.actions;

import common.models.FishState;

public class SkipTurnAction implements IAction {

    @Override
    public FishState performAction(FishState fishState) throws IllegalArgumentException {
        return fishState.createNextState();
    }

}
