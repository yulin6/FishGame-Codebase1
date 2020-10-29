package common.models.Actions;

import common.models.FishState;

import java.util.ArrayList;

/**
 * A MiniMaxAction is class that represent the moves a player can make through utilizing the minimaxGain strategy and
 * has a parameter of an ArrayList of MovePenguinAction that moves the penguin for which movement decided by the
 * strategy. The purpose of the class is for gathering and displaying the moves that a player can make to minimax their
 * gain, so the performAction method is not going to perform the list of movePenguinActions.
 * Instead, the player should call performAction() on each of the movePenguinAction to make the move when it is their turn.
 */
public class MiniMaxAction implements IAction {

    private ArrayList<MovePenguinAction> movePenguinActions = new ArrayList<>();

    /**
     * A default MiniMaxAction constructor that does not take anything. It will wait for MovePenguinActions
     * to be added into the local list using addMovePenguinAction method.
     */
    public MiniMaxAction() {

    }


    @Override
    public FishState performAction(FishState fishState) throws IllegalArgumentException {
        throw new IllegalArgumentException("Error: cannot perform action on MiniMaxAction");
    }

    /**
     * A getter method to get the ArrayList of MovePenguinAction.
     *
     * @return the ArrayList of MovePenguinAction.
     */
    public ArrayList<MovePenguinAction> getMovePenguinActions() {
        return movePenguinActions;
    }

    /**
     * A method to add a MovePenguinAction into the ArrayList of MovePenguinAction.
     *
     * @param movePenguinAction
     */
    public void addMovePenguinAction(MovePenguinAction movePenguinAction) {
        this.movePenguinActions.add(movePenguinAction);
    }
}
