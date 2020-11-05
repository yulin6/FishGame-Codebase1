package player;

import admin.CheatingPlayerMock;
import admin.Referee;
import common.models.*;
import common.models.actions.IAction;
import common.models.actions.MovePenguinAction;
import common.models.actions.PlacePenguinAction;
import common.models.actions.SkipTurnAction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HousePlayerTest {

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
    private HousePlayer housePlayerRed;
    private HousePlayer housePlayerBlack;
    private HousePlayer housePlayerWhite;
    private CheatingPlayerMock cheatingPlayerRed;
    private CheatingPlayerMock cheatingPlayerBlack;
    private CheatingPlayerMock cheatingPlayerWhite;
    private ArrayList<PlayerInfo> twoPlayerInfos;
    private ArrayList<PlayerInfo> threePlayerInfos;
    private ArrayList<IPlayer> twoHousePlayers;
    private ArrayList<IPlayer> threeHousePlayers;
    private ArrayList<IPlayer> cheatingPlayers;
    private ArrayList<IPlayer> twoCheatingPlayers;
    private FishState gameState1;
    private FishState gameState2;
    private FishState gameState3;



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


        housePlayerRed = new HousePlayer(playerInfoRed, 1);
        housePlayerBlack = new HousePlayer(playerInfoBlack, 1);
        housePlayerWhite = new HousePlayer(playerInfoWhite, 1);
        cheatingPlayerRed = new CheatingPlayerMock(playerInfoRed);
        cheatingPlayerBlack = new CheatingPlayerMock(playerInfoBlack);
        cheatingPlayerWhite = new CheatingPlayerMock(playerInfoWhite);
        twoHousePlayers = new ArrayList<>();
        twoHousePlayers.add(housePlayerBlack);
        twoHousePlayers.add(housePlayerRed);
        threeHousePlayers = new ArrayList<>();
        threeHousePlayers.add(housePlayerBlack);
        threeHousePlayers.add(housePlayerRed);
        threeHousePlayers.add(housePlayerWhite);
        cheatingPlayers = new ArrayList<>();
        cheatingPlayers.add(cheatingPlayerRed);
        cheatingPlayers.add(cheatingPlayerBlack);
        cheatingPlayers.add(cheatingPlayerWhite);
        twoCheatingPlayers = new ArrayList<>();
        twoCheatingPlayers.add(housePlayerRed);
        twoCheatingPlayers.add(cheatingPlayerBlack);
        twoCheatingPlayers.add(cheatingPlayerWhite);

        gameState1 = new FishState(fourByEightFishModel, threePlayerInfos);
        gameState2 = new FishState(oneByThreeFishModel, twoPlayerInfos);
        gameState3 = new FishState(twoByFiveFishModel, threePlayerInfos);
    }

    @Test
    public void getPlacePenguinAction() {
        PlacePenguinAction placePenguinAction = housePlayerRed.getPlacePenguinAction(gameState1);
        assertEquals(0, placePenguinAction.getxPos());
        assertEquals(0, placePenguinAction.getyPos());
    }

    @Test
    public void getPlacePenguinAction1() {
        PlacePenguinAction placePenguinAction = housePlayerRed.getPlacePenguinAction(gameState1);
        assertEquals(0, placePenguinAction.getxPos());
        assertEquals(0, placePenguinAction.getyPos());

        FishState nextState = placePenguinAction.performAction(gameState1);
        PlacePenguinAction placePenguinAction1 = housePlayerBlack.getPlacePenguinAction(nextState);
        assertEquals(1, placePenguinAction1.getxPos());
        assertEquals(0, placePenguinAction1.getyPos());
    }

    @Test
    public void gotSkipTurnAction(){
        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);

        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);

        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
        FishState nextState4 = nextState3.placeInitPenguin(0, 1, playerInfoRed);

        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
        FishState nextState5 = nextState4.placeInitPenguin(1, 2, playerInfoBlack);

        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerInfoWhite);

        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
        FishState nextState7 = nextState6.placeInitPenguin(0, 2, playerInfoRed);

        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
        FishState nextState8 = nextState7.placeInitPenguin(0, 3, playerInfoBlack);

        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
        FishState nextState9 = nextState8.placeInitPenguin(1, 4, playerInfoWhite);

        ArrayList<PlayerInfo> players9 = nextState9.getAllPlayerInfos();
        playerInfoRed = players9.get(nextState9.getCurrentPlayerIndex());
        Penguin penguinRed = nextState9.getPenguinsOnBoard().get(6);
        FishState nextState10 = nextState9.makeMovement(0, 4, penguinRed, playerInfoRed);

        IAction iAction = housePlayerBlack.getMovePenguinAction(nextState10);
        assertTrue(iAction instanceof SkipTurnAction);
    }

    @Test
    public void gotSkipTurnAction1(){
        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);

        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);

        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
        FishState nextState4 = nextState3.placeInitPenguin(0, 1, playerInfoRed);

        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
        FishState nextState5 = nextState4.placeInitPenguin(1, 2, playerInfoBlack);

        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerInfoWhite);

        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
        FishState nextState7 = nextState6.placeInitPenguin(0, 2, playerInfoRed);

        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
        FishState nextState8 = nextState7.placeInitPenguin(0, 3, playerInfoBlack);

        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
        FishState nextState9 = nextState8.placeInitPenguin(1, 4, playerInfoWhite);

        ArrayList<PlayerInfo> players9 = nextState9.getAllPlayerInfos();
        playerInfoRed = players9.get(nextState9.getCurrentPlayerIndex());
        Penguin penguinRed = nextState9.getPenguinsOnBoard().get(6);
        FishState nextState10 = nextState9.makeMovement(0, 4, penguinRed, playerInfoRed);

        IAction iAction = housePlayerWhite.getMovePenguinAction(nextState10);
        assertTrue(iAction instanceof SkipTurnAction);
    }

    @Test
    public void gotMovePenguinAction(){
        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);

        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);

        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
        FishState nextState4 = nextState3.placeInitPenguin(0, 1, playerInfoRed);

        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
        FishState nextState5 = nextState4.placeInitPenguin(1, 2, playerInfoBlack);

        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerInfoWhite);

        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
        FishState nextState7 = nextState6.placeInitPenguin(0, 2, playerInfoRed);

        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
        FishState nextState8 = nextState7.placeInitPenguin(0, 3, playerInfoBlack);

        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
        FishState nextState9 = nextState8.placeInitPenguin(1, 4, playerInfoWhite);


        IAction iAction = housePlayerBlack.getMovePenguinAction(nextState9);
        assertTrue(iAction instanceof MovePenguinAction);
        MovePenguinAction movePenguinAction = (MovePenguinAction) iAction;
        assertEquals(0, movePenguinAction.getStartX());
        assertEquals(2, movePenguinAction.getStartY());
        assertEquals(0, movePenguinAction.getTargetX());
        assertEquals(4, movePenguinAction.getTargetY());
    }

    @Test
    public void gotMovePenguinAction1(){
        FishState nextState1 = gameState3.placeInitPenguin(0, 0, playerInfoRed);

        ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
        playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
        FishState nextState2 = nextState1.placeInitPenguin(1, 0, playerInfoBlack);

        ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
        playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
        FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

        ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
        playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
        FishState nextState4 = nextState3.placeInitPenguin(0, 1, playerInfoRed);

        ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
        playerInfoBlack = players4.get(nextState4.getCurrentPlayerIndex());
        FishState nextState5 = nextState4.placeInitPenguin(1, 2, playerInfoBlack);

        ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
        playerInfoWhite = players5.get(nextState5.getCurrentPlayerIndex());
        FishState nextState6 = nextState5.placeInitPenguin(1, 3, playerInfoWhite);

        ArrayList<PlayerInfo> players6 = nextState6.getAllPlayerInfos();
        playerInfoRed = players6.get(nextState6.getCurrentPlayerIndex());
        FishState nextState7 = nextState6.placeInitPenguin(0, 2, playerInfoRed);

        ArrayList<PlayerInfo> players7 = nextState7.getAllPlayerInfos();
        playerInfoBlack = players7.get(nextState7.getCurrentPlayerIndex());
        FishState nextState8 = nextState7.placeInitPenguin(0, 3, playerInfoBlack);

        ArrayList<PlayerInfo> players8 = nextState8.getAllPlayerInfos();
        playerInfoWhite = players8.get(nextState8.getCurrentPlayerIndex());
        FishState nextState9 = nextState8.placeInitPenguin(1, 4, playerInfoWhite);


        IAction iAction = housePlayerWhite.getMovePenguinAction(nextState9);
        assertTrue(iAction instanceof MovePenguinAction);
        MovePenguinAction movePenguinAction = (MovePenguinAction) iAction;
        assertEquals(0, movePenguinAction.getStartX());
        assertEquals(2, movePenguinAction.getStartY());
        assertEquals(0, movePenguinAction.getTargetX());
        assertEquals(4, movePenguinAction.getTargetY());
    }


    @Test
    public void getNTurn() {
        assertEquals(1, housePlayerRed.getNTurn());
        assertEquals(1, housePlayerBlack.getNTurn());
        assertEquals(1, housePlayerWhite.getNTurn());
    }

    @Test
    public void setNTurn() {
        assertEquals(1, housePlayerRed.getNTurn());
        housePlayerRed.setNTurn(5);
        assertEquals(5, housePlayerRed.getNTurn());
    }
    
    @Test
    public void getInfoCopy() {
        PlayerInfo playerInfo = housePlayerRed.getInfoCopy();
        assertEquals(11, playerInfo.getAge());

        PlayerInfo playerInfo1 = housePlayerBlack.getInfoCopy();
        assertEquals(12, playerInfo1.getAge());

        PlayerInfo playerInfo2 = housePlayerWhite.getInfoCopy();
        assertEquals(13, playerInfo2.getAge());
    }

    @Test
    public void setInfoCopy() {
        PlayerInfo playerInfo = housePlayerRed.getInfoCopy();
        assertEquals(11, playerInfo.getAge());

        PlayerInfo newPlayerInfo = new PlayerInfo(55);
        housePlayerRed.setInfoCopy(newPlayerInfo);
        assertEquals(55, housePlayerRed.getInfoCopy().getAge());
    }




}