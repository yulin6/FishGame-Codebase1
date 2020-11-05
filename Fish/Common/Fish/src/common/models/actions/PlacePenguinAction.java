package common.models.actions;

import common.models.FishState;
import common.models.PlayerInfo;

/**
 * PlacePenguinAction is an action that knows the position to place a penguin, by calling the performAction(), it will
 * produce a new state where the penguin is placed on the position, if the position is valid.
 */
public class PlacePenguinAction implements IAction{

    private int xPos;
    private int yPos;


    /**
     * the constructor of the PlacePenguinAction, which contains the x and y position of the place where
     * the penguin is going to be placed.
     * @param xPos x position
     * @param yPos y position
     */
    public PlacePenguinAction(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public FishState performAction(FishState fishState) throws IllegalArgumentException {
        int currentPlayerIndex = fishState.getCurrentPlayerIndex();
        PlayerInfo currentPlayerInfo = fishState.getAllPlayerInfos().get(currentPlayerIndex);
        FishState newState = fishState.placeInitPenguin(xPos, yPos, currentPlayerInfo);
        return newState;
    }

    /**
     * get the x position of the targeting penguin placement.
     * @return x position
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * get the y position of the targeting penguin placement.
     * @return y position
     */
    public int getyPos() {
        return yPos;
    }
}
