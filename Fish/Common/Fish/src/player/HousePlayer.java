package player;

import common.models.*;
import common.models.actions.IAction;
import common.models.actions.PlacePenguinAction;

/**
 * A HousePlayer implements the IPlayer interface. It contains a infoCopy, which contains age, color and score.
 * Also it is constructed with a nTurn variable, which will be used for finding minimax move in getMovePenguinAction
 * method. The class utilize the strategy class for making the decision of penguin placement and movement.
 */
public class HousePlayer implements IPlayer{


    private PlayerInfo infoCopy;
    private Strategy strategy;
    private int nTurn;


    /**
     * the constructor of the HousePlayer, it takes in its PlayerInfo, which contains age, color and score. And a
     * nTurn variable, which will be used for finding minimax move in getMovePenguinAction method.
     * @param infoCopy the copy of its PlayerInfo
     * @param nTurn nTurn variable will be used for finding minimax move.
     */
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

    /**
     * get the nTurn variable of the HousePlayer.
     * @return an int of the nTurn variable.
     */
    public int getNTurn() {
        return nTurn;
    }

    /**
     * set the nTurn variable of the HousePlayer with the given int.
     * @param nTurn the int nTurn that will be set to the HousePlayer.
     */
    public void setNTurn(int nTurn) {
        this.nTurn = nTurn;
    }




}
