package common.actions;

import common.models.FishModel;
import common.models.FishState;
import common.models.PenguinColor;
import common.models.PlayerInfo;
import common.models.actions.PlacePenguinAction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlacePenguinActionTest {

    private int width1;
    private int height1;
    private int width2;
    private int height2;
    private int maxFishNum1;
    private boolean isRandom;
    private FishModel fourByEightFishModel;
    private FishModel oneByThreeFishModel;
    private PlayerInfo playerInfoRed;
    private PlayerInfo playerInfoBlack;
    private PlayerInfo playerInfoWhite;
    private ArrayList<PlayerInfo> twoPlayerInfos;
    private ArrayList<PlayerInfo> threePlayerInfos;
    private FishState gameState1;
    private FishState gameState2;


    @Before
    public void init() {

        width1 = 4;
        height1 = 8;
        maxFishNum1 = 1;
        isRandom = true;
        fourByEightFishModel = new FishModel(width1, height1, maxFishNum1, isRandom);
        width2 = 1;
        height2 = 3;
        oneByThreeFishModel = new FishModel(width2, height2, maxFishNum1, isRandom);
        playerInfoRed = new PlayerInfo(11, PenguinColor.red);
        playerInfoBlack = new PlayerInfo(12, PenguinColor.black);
        playerInfoWhite = new PlayerInfo(13, PenguinColor.white);
        twoPlayerInfos = new ArrayList<PlayerInfo>();
        twoPlayerInfos.add(playerInfoRed);
        twoPlayerInfos.add(playerInfoBlack);
        threePlayerInfos = new ArrayList<PlayerInfo>();
        threePlayerInfos.add(playerInfoRed);
        threePlayerInfos.add(playerInfoBlack);
        threePlayerInfos.add(playerInfoWhite);
        gameState1 = new FishState(fourByEightFishModel, threePlayerInfos);
        gameState2 = new FishState(oneByThreeFishModel, twoPlayerInfos);
    }

    @Test
    public void placeRedTo00(){
        PlacePenguinAction placePenguinAction = new PlacePenguinAction(0, 0);
        FishState nextState = placePenguinAction.performAction(gameState1);
        assertEquals(0, nextState.getPenguinsOnBoard().get(0).getXPos());
        assertEquals(0, nextState.getPenguinsOnBoard().get(0).getYPos());
    }

    @Test
    public void placeRedTwoPenguins(){
        PlacePenguinAction placePenguinAction = new PlacePenguinAction(0, 0);
        FishState nextState = placePenguinAction.performAction(gameState1);
        assertEquals(0, nextState.getPenguinsOnBoard().get(0).getXPos());
        assertEquals(0, nextState.getPenguinsOnBoard().get(0).getYPos());

        PlacePenguinAction placePenguinAction1 = new PlacePenguinAction(2, 0);
        FishState nextState1 = placePenguinAction1.performAction(nextState);
        assertEquals(2, nextState1.getPenguinsOnBoard().get(1).getXPos());
        assertEquals(0, nextState1.getPenguinsOnBoard().get(1).getYPos());
    }

}