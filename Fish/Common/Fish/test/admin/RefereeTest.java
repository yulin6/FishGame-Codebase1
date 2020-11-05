package admin;

import common.models.*;
import org.junit.Before;
import org.junit.Test;
import player.HousePlayer;
import player.IPlayer;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RefereeTest {


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


    private Referee referee1;
    private Referee referee2;
    private Referee referee3;
    private Referee referee4;

    @Before
    public void init() {


        playerInfoRed = new PlayerInfo(11);
        playerInfoBlack = new PlayerInfo(12);
        playerInfoWhite = new PlayerInfo(13);
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

        referee1 = new Referee(threeHousePlayers);
        referee2 = new Referee(twoHousePlayers);
        referee3 = new Referee(cheatingPlayers);
        referee4 = new Referee(twoCheatingPlayers);

//        gameState1 = new FishState(fourByEightFishModel, threePlayerInfos);
//        gameState2 = new FishState(oneByThreeFishModel, twoPlayerInfos);
    }

    @Test
    public void constructRightColors(){
        ArrayList<IPlayer> players = referee1.getOriginalPlayers();
        PenguinColor red = players.get(0).getInfoCopy().getPenguinColor();
        assertEquals(PenguinColor.red, red);

        PenguinColor black = players.get(1).getInfoCopy().getPenguinColor();
        assertEquals(PenguinColor.black, black);

        PenguinColor white = players.get(2).getInfoCopy().getPenguinColor();
        assertEquals(PenguinColor.white, white);
    }

    @Test
    public void constructRightColors2(){
        ArrayList<IPlayer> players = referee2.getOriginalPlayers();
        PenguinColor red = players.get(0).getInfoCopy().getPenguinColor();
        assertEquals(PenguinColor.red, red);

        PenguinColor black = players.get(1).getInfoCopy().getPenguinColor();
        assertEquals(PenguinColor.black, black);
    }

    @Test
    public void invalidNumOfPlayers(){
        IPlayer housePlayer = new HousePlayer(playerInfoRed, 2);
        ArrayList<IPlayer> players = new ArrayList<>();
        players.add(housePlayer);
        try {
            Referee referee = new Referee(players);
        } catch (IllegalArgumentException e){
            assertEquals("Error: Invalid number of players for constructing a game.", e.getMessage());
        }
    }

    @Test
    public void invalidNumOfPlayers1(){
        IPlayer housePlayer = new HousePlayer(playerInfoRed, 2);
        ArrayList<IPlayer> players = new ArrayList<>();
        players.add(housePlayer);
        players.add(housePlayer);
        players.add(housePlayer);
        players.add(housePlayer);
        try {
            Referee referee = new Referee(players);
        } catch (IllegalArgumentException e){
            assertEquals("Error: Invalid number of players for constructing a game.", e.getMessage());
        }
    }

    @Test
    public void playersSortedByAge() {
        ArrayList<IPlayer> players = referee1.getOriginalPlayers();
        int redAge = players.get(0).getInfoCopy().getAge();
        int blackAge = players.get(1).getInfoCopy().getAge();
        int whiteAge = players.get(2).getInfoCopy().getAge();

        assertEquals(11, redAge);
        assertEquals(12, blackAge);
        assertEquals(13, whiteAge);
    }

    @Test
    public void playersSortedByAge1() {
        ArrayList<IPlayer> players = referee2.getOriginalPlayers();
        int redAge = players.get(0).getInfoCopy().getAge();
        int blackAge = players.get(1).getInfoCopy().getAge();

        assertEquals(11, redAge);
        assertEquals(12, blackAge);
    }

    @Test
    public void setUpModel() {
        assertEquals(null, referee1.getCurrentModel());
        referee1.setUpModel(10, 10, 2, true);

        assertEquals(10, referee1.getCurrentModel().getBoard().size());
        assertEquals(10, referee1.getCurrentModel().getBoard().get(0).size());
    }

    @Test
    public void setUpModel1() {
        assertEquals(null, referee2.getCurrentModel());
        referee1.setUpModel(5, 6, 2, false);

        assertEquals(6, referee1.getCurrentModel().getBoard().size());
        assertEquals(5, referee1.getCurrentModel().getBoard().get(0).size());
    }

    @Test
    public void createHole1() {
        referee1.setUpModel(5, 6, 2, false);
        referee1.createHole(0,0);
        assertEquals(true, referee1.getCurrentModel().getBoard().get(0).get(0).isHole());
    }

    @Test
    public void createHole2() {
        referee1.setUpModel(5, 6, 2, false);
        referee1.createHole(0,0);
        assertEquals(true, referee1.getCurrentModel().getBoard().get(0).get(0).isHole());

        referee1.createHole(2,3);
        assertEquals(true, referee1.getCurrentModel().getBoard().get(3).get(2).isHole());
    }

    @Test
    public void setUpState() {
        referee1.setUpModel(5, 6, 2, false);
        assertEquals(null, referee1.getCurrentState());
        assertEquals(GamePhase.setup, referee1.getGamePhase());
        referee1.setUpInitialState();
        assertNotEquals(null, referee1.getCurrentState());
        assertEquals(GamePhase.placing, referee1.getGamePhase());

    }

    @Test
    public void setUpState2() {
        referee2.setUpModel(7, 7, 2, true);
        assertEquals(null, referee2.getCurrentState());
        assertEquals(GamePhase.setup, referee2.getGamePhase());
        referee2.setUpInitialState();
        assertNotEquals(null, referee2.getCurrentState());
        assertEquals(GamePhase.placing, referee2.getGamePhase());
    }

    @Test
    public void allWinners() {
        referee1.setUpModel(3, 3, 2, false);
        referee1.setUpInitialState();
        GameReport report = referee1.startGameTillTheEnd();

        assertEquals(3, report.getWinningPlayers().size());
    }

    @Test
    public void allWinners1() {
        referee1.setUpModel(3, 4, 2, false);
        referee1.setUpInitialState();
        GameReport report = referee1.startGameTillTheEnd();

        assertEquals(3, report.getWinningPlayers().size());
        assertEquals(8, report.getWinningPlayers().get(0).getInfoCopy().getTotalFish());
        assertEquals(8, report.getWinningPlayers().get(1).getInfoCopy().getTotalFish());
        assertEquals(8, report.getWinningPlayers().get(2).getInfoCopy().getTotalFish());
    }

    @Test
    public void allWinners2() {
        referee1.setUpModel(3, 4, 2, false);
        referee1.createHole(0,3);
        referee1.createHole(1,3);
        referee1.createHole(2,3);
        referee1.setUpInitialState();
        GameReport report = referee1.startGameTillTheEnd();

        assertEquals(3, report.getWinningPlayers().size());
        assertEquals(6, report.getWinningPlayers().get(0).getInfoCopy().getTotalFish());
        assertEquals(6, report.getWinningPlayers().get(1).getInfoCopy().getTotalFish());
        assertEquals(6, report.getWinningPlayers().get(2).getInfoCopy().getTotalFish());
    }

    @Test
    public void twoWinners() {
        referee1.setUpModel(3, 4, 2, false);
        referee1.createHole(0,3);
        referee1.setUpInitialState();
        GameReport report = referee1.startGameTillTheEnd();

        assertEquals(2, report.getWinningPlayers().size());
        assertEquals(8, report.getWinningPlayers().get(0).getInfoCopy().getTotalFish());
        assertEquals(8, report.getWinningPlayers().get(1).getInfoCopy().getTotalFish());

        assertEquals(1, report.getFailingPlayers().size());
        assertEquals(6, report.getFailingPlayers().get(0).getInfoCopy().getTotalFish());
    }

    @Test
    public void oneWinner() {
        referee1.setUpModel(3, 4, 2, false);
        referee1.createHole(0,3);
        referee1.createHole(1,3);
        referee1.setUpInitialState();
        GameReport report = referee1.startGameTillTheEnd();

        assertEquals(1, report.getWinningPlayers().size());
        assertEquals(8, report.getWinningPlayers().get(0).getInfoCopy().getTotalFish());

        assertEquals(2, report.getFailingPlayers().size());
        assertEquals(6, report.getFailingPlayers().get(0).getInfoCopy().getTotalFish());
        assertEquals(6, report.getFailingPlayers().get(1).getInfoCopy().getTotalFish());
    }

    @Test
    public void allCheatingPlayers() {
        referee3.setUpModel(3, 4, 2, false);
        referee3.setUpInitialState();
        GameReport report = referee3.startGameTillTheEnd();

        assertEquals(3, report.getCheatingPlayers().size());
        assertEquals(0, report.getCheatingPlayers().get(0).getInfoCopy().getTotalFish());
        assertEquals(0, report.getCheatingPlayers().get(1).getInfoCopy().getTotalFish());
        assertEquals(0, report.getCheatingPlayers().get(2).getInfoCopy().getTotalFish());
    }

    @Test
    public void twoCheatingPlayers() {

        referee4.setUpModel(3, 4, 2, false);
        referee4.setUpInitialState();
        GameReport report = referee4.startGameTillTheEnd();

        assertEquals(2, report.getCheatingPlayers().size());
        assertEquals(0, report.getCheatingPlayers().get(0).getInfoCopy().getTotalFish());
        assertEquals(0, report.getCheatingPlayers().get(1).getInfoCopy().getTotalFish());

        assertEquals(1, report.getWinningPlayers().size());
        assertEquals(24, report.getWinningPlayers().get(0).getInfoCopy().getTotalFish());
    }

    @Test
    public void originalPlayers(){
        assertEquals(3, referee1.getOriginalPlayers().size());
        assertEquals(2, referee2.getOriginalPlayers().size());
        assertEquals(3, referee3.getOriginalPlayers().size());
        assertEquals(3, referee4.getOriginalPlayers().size());
    }

    @Test
    public void originalPlayersAfterRemoving(){
        assertEquals(3, referee3.getOriginalPlayers().size());
        referee3.setUpModel(3, 4, 2, false);
        referee3.setUpInitialState();
        GameReport report = referee3.startGameTillTheEnd();
        assertEquals(3, referee3.getOriginalPlayers().size());
    }

    @Test
    public void originalPlayersAfterRemoving1(){
        assertEquals(3, referee4.getOriginalPlayers().size());
        referee4.setUpModel(3, 4, 2, false);
        referee4.setUpInitialState();
        GameReport report = referee4.startGameTillTheEnd();
        assertEquals(3, referee4.getOriginalPlayers().size());
    }

    @Test
    public void setGamePhaseToSetUp(){
        referee1.setUpModel(3, 4, 2, false);
        referee1.createHole(0,3);
        referee1.createHole(1,3);
        referee1.setUpInitialState();
        GameReport report = referee1.startGameTillTheEnd();

        assertEquals(GamePhase.over, referee1.getGamePhase());
        referee1.setGamePhase(GamePhase.setup);
        assertEquals(GamePhase.setup, referee1.getGamePhase());
    }

    @Test
    public void setGamePhaseToOver(){
        referee1.setUpModel(3, 4, 2, false);
        referee1.createHole(0,3);
        referee1.createHole(1,3);
        referee1.setUpInitialState();

        assertEquals(GamePhase.placing, referee1.getGamePhase());
        referee1.setGamePhase(GamePhase.over);
        assertEquals(GamePhase.over, referee1.getGamePhase());
    }

    @Test
    public void getGameReport(){
        referee1.setUpModel(3, 4, 2, false);
        referee1.createHole(0,3);
        referee1.createHole(1,3);
        referee1.setUpInitialState();
        referee1.startGameTillTheEnd();

        GameReport report = referee1.getGameReport();
        assertEquals(1, report.getWinningPlayers().size());
        assertEquals(8, report.getWinningPlayers().get(0).getInfoCopy().getTotalFish());

        assertEquals(2, report.getFailingPlayers().size());
        assertEquals(6, report.getFailingPlayers().get(0).getInfoCopy().getTotalFish());
        assertEquals(6, report.getFailingPlayers().get(1).getInfoCopy().getTotalFish());
    }

    @Test
    public void getGameReport1(){
        referee3.setUpModel(3, 4, 2, false);
        referee3.setUpInitialState();
        referee3.startGameTillTheEnd();

        GameReport report = referee3.getGameReport();

                assertEquals(3, report.getCheatingPlayers().size());
        assertEquals(0, report.getCheatingPlayers().get(0).getInfoCopy().getTotalFish());
        assertEquals(0, report.getCheatingPlayers().get(1).getInfoCopy().getTotalFish());
        assertEquals(0, report.getCheatingPlayers().get(2).getInfoCopy().getTotalFish());
    }









}