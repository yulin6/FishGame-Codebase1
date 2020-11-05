package player;

import common.models.*;
import common.models.actions.IAction;
import common.models.actions.MovePenguinAction;
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
    private FishModel twoByFiveFishModel;
    private PlayerInfo playerInfoRed;
    private PlayerInfo playerInfoBlack;
    private PlayerInfo playerInfoWhite;
    private ArrayList<PlayerInfo> twoPlayerInfos;
    private ArrayList<PlayerInfo> threePlayerInfos;
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
        twoByFiveFishModel = new FishModel(2, 5, 3, isRandom);
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
        gameState3 = new FishState(twoByFiveFishModel, threePlayerInfos);
        strategy = new Strategy();
    }

    @Test
    public void nextZigZagPlacementTo10() {
        FishState nextState = gameState1.placeInitPenguin(0, 0, playerInfoRed);
        int penguinX = nextState.getPenguinsOnBoard().get(0).getXPos();
        int penguinY = nextState.getPenguinsOnBoard().get(0).getYPos();
        assertEquals(0, penguinX);
        assertEquals(0, penguinY);
        Position position = strategy.nextZigZagPlacement(nextState);
        assertEquals(1, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void nextZigZagPlacementTo20() {
        FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);
        int penguinX1 = nextState1.getPenguinsOnBoard().get(0).getXPos();
        int penguinY1 = nextState1.getPenguinsOnBoard().get(0).getYPos();
        assertEquals(0, penguinX1);
        assertEquals(0, penguinY1);

        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);
        int penguinX2 = nextState2.getPenguinsOnBoard().get(1).getXPos();
        int penguinY2 = nextState2.getPenguinsOnBoard().get(1).getYPos();
        assertEquals(1, penguinX2);
        assertEquals(0, penguinY2);

        Position position = strategy.nextZigZagPlacement(nextState2);
        assertEquals(2, position.getX());
        assertEquals(0, position.getY());
    }

//    @Test
//    public void nextZigZagPlacementTo14() {
//        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);
//
//        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
//        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
//        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
//        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
//        FishState nextState3 = nextState2.placeInitPenguin(0, 1, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
//        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
//        FishState nextState4 = nextState3.placeInitPenguin(1, 1, playerInfoRed);
//
//        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
//        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
//        FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
//        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
//        FishState nextState6 = nextState5.placeInitPenguin(1, 2, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
//        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
//        FishState nextState7 = nextState6.placeInitPenguin(0, 3, playerInfoRed);
//
//        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
//        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
//        FishState nextState8 = nextState7.placeInitPenguin(1, 3, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
//        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
//        FishState nextState9 = nextState8.placeInitPenguin(0, 4, playerInfoWhite);
//
//        Position position = strategy.nextZigZagPlacement(nextState9);
//        assertEquals(1, position.getX());
//        assertEquals(4, position.getY());
//    }

//    @Test
//    public void nextZigZagPlacementInvalidBoard() {
//        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);
//
//        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
//        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
//        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
//        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
//        FishState nextState3 = nextState2.placeInitPenguin(0, 1, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
//        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
//        FishState nextState4 = nextState3.placeInitPenguin(1, 1, playerInfoRed);
//
//        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
//        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
//        FishState nextState5 = nextState4.placeInitPenguin(0, 2, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
//        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
//        FishState nextState6 = nextState5.placeInitPenguin(0, 3, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
//        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
//        FishState nextState7 = nextState6.placeInitPenguin(1, 2, playerInfoRed);
//
//        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
//        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
//        FishState nextState8 = nextState7.placeInitPenguin(1, 3, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
//        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
//        FishState nextState9 = nextState8.placeInitPenguin(0, 4, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players9 = nextState9.getAllPlayerInfos();
//        playerInfoRed = players9.get(nextState9.getCurrentPlayerIndex());
//        Penguin penguinRed = nextState9.getPenguinsOnBoard().get(6);
//        FishState nextState10 = nextState9.makeMovement(1, 4, penguinRed, playerInfoRed);
//
//
//        try {
//            Position position = strategy.nextZigZagPlacement(nextState9);
//        } catch (IllegalArgumentException e) {
//            assertEquals("Error: Referee did not set up a large enough board.", e.getMessage());
//        }
//    }

//    @Test
//    public void miniMaxGainZeroTurn() {
//        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);
//
//        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
//        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
//        FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
//        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
//        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
//        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
//        FishState nextState4 = nextState3.placeInitPenguin(0, 2, playerInfoRed);
//
//        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
//        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
//        FishState nextState5 = nextState4.placeInitPenguin(1, 0, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
//        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
//        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
//        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
//        FishState nextState7 = nextState6.placeInitPenguin(0, 4, playerInfoRed);
//
//        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
//        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
//        FishState nextState8 = nextState7.placeInitPenguin(1, 2, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
//        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
//        FishState nextState9 = nextState8.placeInitPenguin(1, 4, playerInfoWhite);
//
//        FishTreeNode treeNode = new FishTreeNode(null, nextState9);
//        MiniMaxAction miniMaxAction = strategy.minimaxGain(treeNode, 0, PenguinColor.red);
//        ArrayList<MovePenguinAction> movePenguinActions = miniMaxAction.getMovePenguinActions();
//
//
//        assertEquals(0, movePenguinActions.size());
//
//
//    }

    @Test
    public void miniMaxGainOneTurn() {
        FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);

        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
        FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
        FishState nextState4 = nextState3.placeInitPenguin(0, 2, playerInfoRed);

        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
        FishState nextState5 = nextState4.placeInitPenguin(1, 0, playerInfoBlack);

        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerInfoWhite);

        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
        FishState nextState7 = nextState6.placeInitPenguin(0, 4, playerInfoRed);

        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
        FishState nextState8 = nextState7.placeInitPenguin(1, 2, playerInfoBlack);

        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
        FishState nextState9 = nextState8.placeInitPenguin(1, 4, playerInfoWhite);

        FishTreeNode treeNode = new FishTreeNode(null, nextState9);
        IAction movePenguinAction = strategy.findMinimaxAction(treeNode, 1, PenguinColor.red);

//        System.out.println(movePenguinAction.getStartX());
//        System.out.println(movePenguinAction.getStartY());
//        System.out.println(movePenguinAction.getTargetX());
//        System.out.println(movePenguinAction.getTargetY());
//        MiniMaxAction miniMaxAction = strategy.minimaxGain(treeNode, 1, PenguinColor.red);
//        ArrayList<MovePenguinAction> movePenguinActions = miniMaxAction.getMovePenguinActions();
//
//        MovePenguinAction onlyAction = movePenguinActions.get(0);
//        ArrayList moveActionPositions = onlyAction.getMoveActionPositions();
//
//        assertEquals(1, movePenguinActions.size());
//        assertEquals(0, moveActionPositions.get(0));
//        assertEquals(2, moveActionPositions.get(1));
//        assertEquals(0, moveActionPositions.get(2));
//        assertEquals(3, moveActionPositions.get(3));

    }

//    @Test
//    public void miniMaxGainTwoTurns() {
//        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);
//
//        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
//        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
//        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
//        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
//        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
//        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
//        FishState nextState4 = nextState3.placeInitPenguin(0, 1, playerInfoRed);
//
//        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
//        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
//        FishState nextState5 = nextState4.placeInitPenguin(1, 2, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
//        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
//        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
//        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
//        FishState nextState7 = nextState6.placeInitPenguin(0, 2, playerInfoRed);
//
//        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
//        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
//        FishState nextState8 = nextState7.placeInitPenguin(0, 3, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
//        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
//        FishState nextState9 = nextState8.placeInitPenguin(1, 4, playerInfoWhite);
//
//        FishTreeNode treeNode = new FishTreeNode(null, nextState9);
//        MiniMaxAction miniMaxAction = strategy.minimaxGain(treeNode, 2, PenguinColor.red);
//        ArrayList<MovePenguinAction> movePenguinActions = miniMaxAction.getMovePenguinActions();
//
//        MovePenguinAction onlyAction = movePenguinActions.get(0);
//        ArrayList moveActionPositions = onlyAction.getMoveActionPositions();
//
//        assertEquals(1, movePenguinActions.size());
//        assertEquals(0, moveActionPositions.get(0));
//        assertEquals(2, moveActionPositions.get(1));
//        assertEquals(0, moveActionPositions.get(2));
//        assertEquals(4, moveActionPositions.get(3));
//
//    }
//
//    @Test
//    public void miniMaxGainNegtiveTurn() {
//        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);
//
//        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
//        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
//        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
//        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
//        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
//        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
//        FishState nextState4 = nextState3.placeInitPenguin(0, 1, playerInfoRed);
//
//        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
//        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
//        FishState nextState5 = nextState4.placeInitPenguin(1, 2, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
//        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
//        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
//        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
//        FishState nextState7 = nextState6.placeInitPenguin(0, 2, playerInfoRed);
//
//        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
//        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
//        FishState nextState8 = nextState7.placeInitPenguin(0, 3, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
//        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
//        FishState nextState9 = nextState8.placeInitPenguin(1, 4, playerInfoWhite);
//
//        FishTreeNode treeNode = new FishTreeNode(null, nextState9);
//        try {
//            MiniMaxAction miniMaxAction = strategy.minimaxGain(treeNode, -1, PenguinColor.red);
//        } catch (IllegalArgumentException e){
//            assertEquals("Error: N turn cannot be less than 0.", e.getMessage());
//        }
//
//
//    }
//
//    @Test
//    public void miniMaxGainNoMoreAction() {
//        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);
//
//        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
//        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
//        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
//        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
//        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
//        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
//        FishState nextState4 = nextState3.placeInitPenguin(0, 1, playerInfoRed);
//
//        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
//        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
//        FishState nextState5 = nextState4.placeInitPenguin(1, 2, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
//        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
//        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
//        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
//        FishState nextState7 = nextState6.placeInitPenguin(0, 2, playerInfoRed);
//
//        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
//        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
//        FishState nextState8 = nextState7.placeInitPenguin(0, 3, playerInfoBlack);
//
//        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
//        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
//        FishState nextState9 = nextState8.placeInitPenguin(1, 4, playerInfoWhite);
//
//        ArrayList<PlayerInfo> players9 = nextState9.getAllPlayerInfos();
//        playerInfoRed = players9.get(nextState9.getCurrentPlayerIndex());
//        Penguin penguinRed = nextState9.getPenguinsOnBoard().get(6);
//        FishState nextState10 = nextState9.makeMovement(0, 4, penguinRed, playerInfoRed);
//
//        FishTreeNode treeNode = new FishTreeNode(null, nextState10);
//        MiniMaxAction miniMaxAction = strategy.minimaxGain(treeNode, 2, PenguinColor.red);
//        ArrayList<MovePenguinAction> movePenguinActions = miniMaxAction.getMovePenguinActions();
//
//
//        assertEquals(0, movePenguinActions.size());
//    }




}