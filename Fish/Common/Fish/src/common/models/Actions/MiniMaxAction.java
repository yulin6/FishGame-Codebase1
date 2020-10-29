package common.models.Actions;

import common.models.FishState;

import java.util.ArrayList;

public class MiniMaxAction implements IAction{




    private ArrayList<MovePenguinAction> movePenguinActions = new ArrayList<>();

    public MiniMaxAction() {

    }


    @Override
    public FishState performAction(FishState fishState) throws IllegalArgumentException {
        throw new IllegalArgumentException("Error: cannot perform action on MiniMaxAction");
    }

    public ArrayList<MovePenguinAction> getMovePenguinActions() {
        return movePenguinActions;
    }

    public void addMovePenguinAction(MovePenguinAction movePenguinAction) {
        this.movePenguinActions.add(movePenguinAction);
    }
}
