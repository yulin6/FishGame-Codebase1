package player;

import common.models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StrategyTest {

    private int width1;
    private int height1;
    private int width2;
    private int height2;
    private int maxFishNum1;
    private boolean isRandom;
    private FishModel fourByEightFishModel;
    private FishModel oneByThreeFishModel;
    private FishModel twoBySevenFishModel;
    private Player playerRed;
    private Player playerBlack;
    private Player playerWhite;
    private ArrayList<Player> twoPlayers;
    private ArrayList<Player> threePlayers;
    private FishState gameState1;
    private FishState gameState2;
    private FishState gameState3;
    private Strategy strategy;


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
        twoBySevenFishModel = new FishModel(2, 7, 3, isRandom);
        playerRed = new Player(11, PenguinColor.red);
        playerBlack = new Player(12, PenguinColor.black);
        playerWhite = new Player(13, PenguinColor.white);
        twoPlayers = new ArrayList<Player>();
        twoPlayers.add(playerRed);
        twoPlayers.add(playerBlack);
        threePlayers = new ArrayList<Player>();
        threePlayers.add(playerRed);
        threePlayers.add(playerBlack);
        threePlayers.add(playerWhite);
        gameState1 = new FishState(fourByEightFishModel, threePlayers);
        gameState2 = new FishState(oneByThreeFishModel, twoPlayers);
        gameState3 = new FishState(twoBySevenFishModel, threePlayers);
        strategy = new Strategy();
    }

    @Test
    public void nextZigZagPlacementTo10(){
        FishState nextState = gameState1.placeInitPenguin(0, 0, playerRed);
        int penguinX = nextState.getPenguinsOnBoard().get(0).getXPos();
        int penguinY = nextState.getPenguinsOnBoard().get(0).getYPos();
        assertEquals(0, penguinX);
        assertEquals(0, penguinY);
        Position position = strategy.nextZigZagPlacement(nextState);
        assertEquals(1, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void nextZigZagPlacementTo20(){
        FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);
        int penguinX1 = nextState1.getPenguinsOnBoard().get(0).getXPos();
        int penguinY1 = nextState1.getPenguinsOnBoard().get(0).getYPos();
        assertEquals(0, penguinX1);
        assertEquals(0, penguinY1);

        ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
        playerBlack = players.get(nextState1.getCurrentPlayerIndex());
        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerBlack);
        int penguinX2 = nextState2.getPenguinsOnBoard().get(1).getXPos();
        int penguinY2 = nextState2.getPenguinsOnBoard().get(1).getYPos();
        assertEquals(1, penguinX2);
        assertEquals(0, penguinY2);

        Position position = strategy.nextZigZagPlacement(nextState2);
        assertEquals(2, position.getX());
        assertEquals(0, position.getY());
    }


    @Test
    public void miniMaxGainTwoTurns() {
        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerRed);

        ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
        playerBlack = players.get(nextState1.getCurrentPlayerIndex());
        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerBlack);

        ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
        playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);

        ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
        playerRed = players3.get(nextState3.getCurrentPlayerIndex());
        FishState nextState4 = nextState3.placeInitPenguin(0, 1, playerRed);

        ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
        playerBlack = players4.get(nextState4.getCurrentPlayerIndex());
        FishState nextState5 = nextState4.placeInitPenguin(1, 2, playerBlack);

        ArrayList<Player> players5 = nextState5.getPlayersSortedByAgeAscend();
        playerWhite = players5.get(nextState5.getCurrentPlayerIndex());
        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerWhite);

        ArrayList<Player> players6 = nextState6.getPlayersSortedByAgeAscend();
        playerRed = players6.get(nextState6.getCurrentPlayerIndex());
        FishState nextState7 = nextState6.placeInitPenguin(0, 2, playerRed);

        ArrayList<Player> players7 = nextState7.getPlayersSortedByAgeAscend();
        playerBlack = players7.get(nextState7.getCurrentPlayerIndex());
        FishState nextState8 = nextState7.placeInitPenguin(3, 2, playerBlack);

        ArrayList<Player> players8 = nextState8.getPlayersSortedByAgeAscend();
        playerWhite = players8.get(nextState8.getCurrentPlayerIndex());
        FishState nextState9 = nextState8.placeInitPenguin(1, 6, playerWhite);

        FishTreeNode treeNode = new FishTreeNode(null, gameState3);

    }



}