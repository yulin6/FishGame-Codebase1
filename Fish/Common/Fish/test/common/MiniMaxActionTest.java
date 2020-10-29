package common;

import common.models.Actions.MiniMaxAction;
import common.models.Actions.MovePenguinAction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MiniMaxActionTest {

    MiniMaxAction miniMaxAction;

    @Before
    public void init() {
        miniMaxAction = new MiniMaxAction();
    }

    @Test
    public void addMovePenguinAction(){
        MovePenguinAction movePenguinAction = new MovePenguinAction(0,0,23,23);
        miniMaxAction.addMovePenguinAction(movePenguinAction);
        MovePenguinAction movePenguinAction1 = miniMaxAction.getMovePenguinActions().get(0);
        ArrayList positions = movePenguinAction1.getMoveActionPositions();
        assertEquals(0, positions.get(0));
        assertEquals(0, positions.get(1));
        assertEquals(23, positions.get(2));
        assertEquals(23, positions.get(3));
    }

    @Test
    public void addMovePenguinAction2(){
        MovePenguinAction movePenguinAction = new MovePenguinAction(0,0,23,23);
        miniMaxAction.addMovePenguinAction(movePenguinAction);
        MovePenguinAction movePenguinAction1 = miniMaxAction.getMovePenguinActions().get(0);
        ArrayList positions = movePenguinAction1.getMoveActionPositions();
        assertEquals(0, positions.get(0));
        assertEquals(0, positions.get(1));
        assertEquals(23, positions.get(2));
        assertEquals(23, positions.get(3));

        MovePenguinAction movePenguinAction2 = new MovePenguinAction(2,34,0,2);
        miniMaxAction.addMovePenguinAction(movePenguinAction2);
        MovePenguinAction movePenguinAction3 = miniMaxAction.getMovePenguinActions().get(1);
        ArrayList positions1 = movePenguinAction3.getMoveActionPositions();
        assertEquals(2, positions1.get(0));
        assertEquals(34, positions1.get(1));
        assertEquals(0, positions1.get(2));
        assertEquals(2, positions1.get(3));
    }

    @Test
    public void getMovePenguinActions() {
        MovePenguinAction movePenguinAction = new MovePenguinAction(0,0,23,23);
        miniMaxAction.addMovePenguinAction(movePenguinAction);
        ArrayList<MovePenguinAction> movePenguinActions = miniMaxAction.getMovePenguinActions();
        assertEquals(1, movePenguinActions.size());

    }

    @Test
    public void getMovePenguinActions2() {
        MovePenguinAction movePenguinAction = new MovePenguinAction(0,0,23,23);
        miniMaxAction.addMovePenguinAction(movePenguinAction);
        ArrayList<MovePenguinAction> movePenguinActions = miniMaxAction.getMovePenguinActions();
        assertEquals(1, movePenguinActions.size());

        MovePenguinAction movePenguinAction2 = new MovePenguinAction(2,34,0,2);
        miniMaxAction.addMovePenguinAction(movePenguinAction2);
        ArrayList<MovePenguinAction> movePenguinActions2 = miniMaxAction.getMovePenguinActions();
        assertEquals(2, movePenguinActions2.size());

    }
}