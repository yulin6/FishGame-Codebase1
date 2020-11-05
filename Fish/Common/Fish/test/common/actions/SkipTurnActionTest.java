package common.actions;

import common.models.actions.SkipTurnAction;
import common.models.FishModel;
import common.models.FishState;
import common.models.PenguinColor;
import common.models.PlayerInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SkipTurnActionTest {

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
    public void skipOneTurn() {
        SkipTurnAction skipTurnAction = new SkipTurnAction();
        FishState nextState = skipTurnAction.performAction(gameState1);
        int currentPlayerIndex = nextState.getCurrentPlayerIndex();

        assertEquals(1, currentPlayerIndex);

    }

    @Test
    public void skipTwoTurns() {
        SkipTurnAction skipTurnAction = new SkipTurnAction();
        FishState nextState = skipTurnAction.performAction(gameState1);
        int currentPlayerIndex = nextState.getCurrentPlayerIndex();
        assertEquals(1, currentPlayerIndex);

        FishState nextState1 = skipTurnAction.performAction(nextState);
        currentPlayerIndex = nextState1.getCurrentPlayerIndex();
        assertEquals(2, currentPlayerIndex);
    }

    @Test
    public void skipThreeTurns() {
        SkipTurnAction skipTurnAction = new SkipTurnAction();
        FishState nextState = skipTurnAction.performAction(gameState1);
        int currentPlayerIndex = nextState.getCurrentPlayerIndex();
        assertEquals(1, currentPlayerIndex);

        FishState nextState1 = skipTurnAction.performAction(nextState);
        currentPlayerIndex = nextState1.getCurrentPlayerIndex();
        assertEquals(2, currentPlayerIndex);

        FishState nextState2 = skipTurnAction.performAction(nextState1);
        currentPlayerIndex = nextState2.getCurrentPlayerIndex();
        assertEquals(0, currentPlayerIndex);
    }

    @Test
    public void skipFourTurns() {
        SkipTurnAction skipTurnAction = new SkipTurnAction();
        FishState nextState = skipTurnAction.performAction(gameState1);
        int currentPlayerIndex = nextState.getCurrentPlayerIndex();
        assertEquals(1, currentPlayerIndex);

        FishState nextState1 = skipTurnAction.performAction(nextState);
        currentPlayerIndex = nextState1.getCurrentPlayerIndex();
        assertEquals(2, currentPlayerIndex);

        FishState nextState2 = skipTurnAction.performAction(nextState1);
        currentPlayerIndex = nextState2.getCurrentPlayerIndex();
        assertEquals(0, currentPlayerIndex);

        FishState nextState3 = skipTurnAction.performAction(nextState2);
        currentPlayerIndex = nextState3.getCurrentPlayerIndex();
        assertEquals(1, currentPlayerIndex);
    }


}