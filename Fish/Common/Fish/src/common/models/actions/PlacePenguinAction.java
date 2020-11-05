package common.models.actions;

import common.models.FishState;
import common.models.PlayerInfo;

public class PlacePenguinAction implements IAction{

    private int xPos;
    private int yPos;


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
}
