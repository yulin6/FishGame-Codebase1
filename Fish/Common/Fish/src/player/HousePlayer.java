package player;

import common.models.*;
import common.models.actions.IAction;
import common.models.actions.PlacePenguinAction;


public class HousePlayer implements IPlayer{


    private PlayerInfo infoCopy;
//    private FishState stateCopy;
    private Strategy strategy;
    private int nTurn;



    public HousePlayer(PlayerInfo infoCopy, int nTurn){
        this.infoCopy = infoCopy;
        this.strategy = new Strategy();
        this.nTurn = nTurn;
    }

    @Override
    public PlacePenguinAction getPlacePenguinAction(FishState fishState) throws IllegalArgumentException{
        Position placement = strategy.nextZigZagPlacement(fishState);
        PlacePenguinAction placePenguinAction = new PlacePenguinAction(placement.getX(), placement.getY());
        return placePenguinAction;
    }

    @Override
    public IAction getMovePenguinAction(FishState fishState) throws IllegalArgumentException{
        FishTreeNode currentNode = new FishTreeNode(null, fishState);
        PenguinColor penguinColor = infoCopy.getPenguinColor();
        return strategy.findMinimaxAction(currentNode, nTurn, penguinColor);
    }



    @Override
    public PlayerInfo getInfoCopy() {
        return infoCopy;
    }

    @Override
    public void setInfoCopy(PlayerInfo infoCopy) {
        this.infoCopy = infoCopy;
    }

    public int getNTurn() {
        return nTurn;
    }

    public void setNTurn(int nTurn) {
        this.nTurn = nTurn;
    }


//    @Override
//    public FishState getStateCopy() {
//        return stateCopy;
//    }
//
//    @Override
//    public void setStateCopy(FishState stateCopy) {
//        this.stateCopy = stateCopy;
//    }



}
