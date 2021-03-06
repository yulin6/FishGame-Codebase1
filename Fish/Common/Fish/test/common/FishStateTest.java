package common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import common.models.FishState;
import common.models.FishModel;
import common.models.Penguin;
import common.models.PenguinColor;
import common.models.PlayerInfo;
import org.junit.Before;
import org.junit.Test;


/**
 * FishTest for the FishState class.
 **/
public class FishStateTest {

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
    fourByEightFishModel = new FishModel(width1, height1, maxFishNum1, false);
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
  public void placePenguinSucceed() {
    FishState nextState = gameState1.placeInitPenguin(0, 0, playerInfoRed);
    int penguinX = nextState.getPenguinsOnBoard().get(0).getXPos();
    int penguinY = nextState.getPenguinsOnBoard().get(0).getYPos();
    assertEquals(0, penguinX);
    assertEquals(0, penguinY);
  }
//
  @Test
  public void placePenguinSucceed1() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);
    int penguinX1 = nextState1.getPenguinsOnBoard().get(0).getXPos();
    int penguinY1 = nextState1.getPenguinsOnBoard().get(0).getYPos();
    assertEquals(0, penguinX1);
    assertEquals(0, penguinY1);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);
    int penguinX2 = nextState2.getPenguinsOnBoard().get(1).getXPos();
    int penguinY2 = nextState2.getPenguinsOnBoard().get(1).getYPos();
    assertEquals(0, penguinX2);
    assertEquals(1, penguinY2);
  }

  @Test//(expected = IllegalArgumentException.class)
  public void placePenguinFailed() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);
//    int penguinX1 = nextState1.getPenguinsOnBoard().get(0).getXPos();
//    int penguinY1 = nextState1.getPenguinsOnBoard().get(0).getYPos();
////    assertEquals(0, penguinX1);
//    assertEquals(0, penguinY1);
    try {
      ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
      playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
      FishState nextState2 = nextState1.placeInitPenguin(0, 0, playerInfoBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: There is already one penguin on the target tile.", e.getMessage());
    }
  }

  @Test
  public void placePenguinFailed1() {
    try {
      gameState1.placeInitPenguin(100, 100, playerInfoRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }

  @Test
  public void placePenguinFailed2() {
    try {
      gameState1.placeInitPenguin(-1, -10, playerInfoRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }


  @Test
  public void placePenguinFailed3() {
    try {
      gameState1.placeInitPenguin(-1, 10, playerInfoRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }

  @Test
  public void placePenguinFailed4() {
    try {
      gameState1.placeInitPenguin(1, -10, playerInfoRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }

  @Test
  public void placePenguinFailed5() {
    try {
      gameState1.placeInitPenguin(0, 0, playerInfoBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Not your turn.", e.getMessage());
    }
  }



  @Test
  public void makeMovementSucceed() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    int currentPlayerNum = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(currentPlayerNum);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerInfoRed);
    assertEquals(0, nextState4.getPenguinsOnBoard().get(0).getXPos());
    assertEquals(2, nextState4.getPenguinsOnBoard().get(0).getYPos());
  }

  @Test
  public void makeMovementSucceed1() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    int currentPlayerNum = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(currentPlayerNum);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerInfoRed);
    assertEquals(0, nextState4.getPenguinsOnBoard().get(0).getXPos());
    assertEquals(2, nextState4.getPenguinsOnBoard().get(0).getYPos());

    currentPlayerNum = nextState4.getCurrentPlayerIndex();
    Penguin penguinBlack = nextState4.getPenguinsOnBoard().get(1);
    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(currentPlayerNum);
    FishState nextState5 = nextState4.makeMovement(0, 5, penguinBlack, playerInfoBlack);
    assertEquals(0, nextState5.getPenguinsOnBoard().get(1).getXPos());
    assertEquals(5, nextState5.getPenguinsOnBoard().get(1).getYPos());
  }

  @Test
  public void makeMovementSucceed3() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(currentPlayerIndex);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerInfoRed);
    assertEquals(0, nextState4.getPenguinsOnBoard().get(0).getXPos());
    assertEquals(2, nextState4.getPenguinsOnBoard().get(0).getYPos());

    currentPlayerIndex = nextState4.getCurrentPlayerIndex();
    Penguin penguinBlack = nextState4.getPenguinsOnBoard().get(1);
    ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
    playerInfoBlack = players4.get(currentPlayerIndex);
    FishState nextState5 = nextState4.makeMovement(0, 5, penguinBlack, playerInfoBlack);
    assertEquals(0, nextState5.getPenguinsOnBoard().get(1).getXPos());
    assertEquals(5, nextState5.getPenguinsOnBoard().get(1).getYPos());

    currentPlayerIndex = nextState5.getCurrentPlayerIndex();
    Penguin penguinWhite = nextState5.getPenguinsOnBoard().get(2);
    ArrayList<PlayerInfo> players5 = nextState5.getAllPlayerInfos();
    playerInfoWhite = players5.get(currentPlayerIndex);
    FishState nextState6 = nextState5.makeMovement(2, 0, penguinWhite, playerInfoWhite);
    assertEquals(2, nextState6.getPenguinsOnBoard().get(2).getXPos());
    assertEquals(0, nextState6.getPenguinsOnBoard().get(2).getYPos());

  }

  @Test
  public void makeMovementFailed() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(currentPlayerIndex);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerInfoRed);

    try {
      currentPlayerIndex = nextState4.getCurrentPlayerIndex();
      Penguin penguinWhite = nextState4.getPenguinsOnBoard().get(2);
      ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
      playerInfoWhite = players4.get(2);
      FishState nextState6 = nextState4.makeMovement(2, 0, penguinWhite, playerInfoWhite);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Not your turn.", e.getMessage());
    }
  }

  @Test
  public void makeMovementFailed1() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    try {
      int currentPlayerNum = nextState3.getCurrentPlayerIndex();
      Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
      ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
      playerInfoRed = players3.get(currentPlayerNum);
      FishState nextState4 = nextState3.makeMovement(0, -2, penguinRed, playerInfoRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }

  @Test
  public void makeMovementFailed2() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    try {
      int currentPlayerNum = nextState3.getCurrentPlayerIndex();
      Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
      ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
      playerInfoRed = players3.get(currentPlayerNum);
      FishState nextState4 = nextState3.makeMovement(1, 0, penguinRed, playerInfoRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Invalid position to move to.", e.getMessage());
    }
  }

  @Test
  public void makeMovementFailed3() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    int currentPlayerNum = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(currentPlayerNum);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerInfoRed);

    try {
      currentPlayerNum = nextState4.getCurrentPlayerIndex();
      Penguin penguinBlack = nextState4.getPenguinsOnBoard().get(1);
      ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
      playerInfoBlack = players4.get(currentPlayerNum);
      FishState nextState5 = nextState4.makeMovement(0, 2, penguinBlack, playerInfoBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Invalid position to move to.", e.getMessage());
    }

  }

  @Test
  public void makeMovementFailed4() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(currentPlayerIndex);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerInfoRed);

    try {
      currentPlayerIndex = nextState4.getCurrentPlayerIndex();
      Penguin penguinWhite = nextState4.getPenguinsOnBoard().get(2);
      ArrayList<PlayerInfo> players4 = nextState4.getAllPlayerInfos();
      playerInfoBlack = players4.get(currentPlayerIndex);
      FishState nextState5 = nextState4.makeMovement(2, 0, penguinWhite, playerInfoBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Not the owner of the penguin.", e.getMessage());
    }

  }

//  @Test
//  public void gameNotOver() {
//    gameState2.placeInitPenguin(0, 0, playerInfoRed);
//    boolean isGameOver = gameState2.isGameOver();
//    assertEquals(false, isGameOver);
//  }

  @Test
  public void gameNotOver1() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerInfoRed);
    int penguinX1 = nextState1.getPenguinsOnBoard().get(0).getXPos();
    int penguinY1 = nextState1.getPenguinsOnBoard().get(0).getYPos();
    assertEquals(0, penguinX1);
    assertEquals(0, penguinY1);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

//    gameState2.placeInitPenguin(0, 0, playerRed);
//    gameState2.placeInitPenguin(0, 1, playerBlack);
    boolean isGameOver = nextState2.isGameOver();
    assertEquals(false, isGameOver);
  }

//  @Test
//  public void gameNotOver2() {
//    boolean isGameOver = gameState2.isGameOver();
//    assertEquals(false, isGameOver);
//  }

  @Test
  public void gameOver() {


    FishState nextState1 = gameState2.placeInitPenguin(0, 0, playerInfoRed);


    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    int currentPlayerIndex = nextState2.getCurrentPlayerIndex();
    Penguin penguinRed = nextState2.getPenguinsOnBoard().get(0);
    ArrayList<PlayerInfo> players3 = nextState2.getAllPlayerInfos();
    playerInfoRed = players3.get(currentPlayerIndex);
    FishState nextState3 = nextState2.makeMovement(0, 2, penguinRed, playerInfoRed);

    boolean isGameOver = nextState3.isGameOver();
    assertEquals(true, isGameOver);

  }

  @Test
  public void addTotalFishNum() {

//    System.out.println(gameState2.getPlayersSortedByAgeAscend().get(0).getPenguinColor());
//    System.out.println(gameState2.getPlayersSortedByAgeAscend().get(0).getTotalFish());
    int totalFishNum = gameState2.getAllPlayerInfos().get(0).getTotalFish();
    assertEquals(0, totalFishNum);

    FishState nextState1 = gameState2.placeInitPenguin(0, 0, playerInfoRed);
    totalFishNum += nextState1.getBoard().get(0).get(0).getFishNum();
    int currentTotalFish = nextState1.getAllPlayerInfos().get(0).getTotalFish();
    assertEquals(currentTotalFish, totalFishNum);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    int currentPlayerIndex = nextState2.getCurrentPlayerIndex();
    Penguin penguinRed = nextState2.getPenguinsOnBoard().get(0);
    ArrayList<PlayerInfo> players3 = nextState2.getAllPlayerInfos();
    playerInfoRed = players3.get(currentPlayerIndex);
    FishState nextState3 = nextState2.makeMovement(0, 2, penguinRed, playerInfoRed);

    totalFishNum += nextState3.getBoard().get(2).get(0).getFishNum();
    currentTotalFish = nextState3.getAllPlayerInfos().get(0).getTotalFish();
    assertEquals(currentTotalFish, totalFishNum);


  }


  @Test
  public void removeOnePlayer(){
    FishState twoPlayersLeft = gameState1.removeCurrentPlayerInfo();
    int totalPlayerNum = twoPlayersLeft.getAllPlayerInfos().size();
    assertEquals(2, totalPlayerNum);
  }

  @Test
  public void removeTwoPlayers(){
    FishState twoPlayersLeft = gameState1.removeCurrentPlayerInfo();
    int totalPlayerNum = twoPlayersLeft.getAllPlayerInfos().size();
    assertEquals(2, totalPlayerNum);

    FishState onePlayerLeft = twoPlayersLeft.removeCurrentPlayerInfo();
    totalPlayerNum = onePlayerLeft.getAllPlayerInfos().size();
    assertEquals(1, totalPlayerNum);
  }

  @Test
  public void removeAllPlayers(){
    FishState twoPlayersLeft = gameState1.removeCurrentPlayerInfo();
    int totalPlayerNum = twoPlayersLeft.getAllPlayerInfos().size();
    assertEquals(2, totalPlayerNum);

    FishState onePlayerLeft = twoPlayersLeft.removeCurrentPlayerInfo();
    totalPlayerNum = onePlayerLeft.getAllPlayerInfos().size();
    assertEquals(1, totalPlayerNum);

    FishState zeroPlayerLeft = onePlayerLeft.removeCurrentPlayerInfo();
    totalPlayerNum = zeroPlayerLeft.getAllPlayerInfos().size();
    assertEquals(0, totalPlayerNum);
  }

  @Test
  public void removePlayerNotExist(){
    FishState twoPlayersLeft = gameState1.removeCurrentPlayerInfo();
    int totalPlayerNum = twoPlayersLeft.getAllPlayerInfos().size();
    assertEquals(2, totalPlayerNum);

    FishState onePlayerLeft = twoPlayersLeft.removeCurrentPlayerInfo();
    totalPlayerNum = onePlayerLeft.getAllPlayerInfos().size();
    assertEquals(1, totalPlayerNum);

    FishState zeroPlayerLeft = onePlayerLeft.removeCurrentPlayerInfo();
    totalPlayerNum = zeroPlayerLeft.getAllPlayerInfos().size();
    assertEquals(0, totalPlayerNum);

    try {
      FishState invalid = onePlayerLeft.removeCurrentPlayerInfo();
    } catch (IllegalArgumentException e){
      assertEquals("Error: no player with the color.", e.getMessage());
    }
  }


  @Test
  public void getRedPenguins(){
    FishState nextState = gameState1.placeInitPenguin(0, 0, playerInfoRed);
    ArrayList<Penguin> redPenguins = nextState.getPenguins(PenguinColor.red);
    assertEquals(1, redPenguins.size());
  }

  @Test
  public void getRedPenguins2(){
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    ArrayList<Penguin> redPenguins = nextState4.getPenguins(PenguinColor.red);
    assertEquals(2, redPenguins.size());
  }

  @Test
  public void getBrownPenguins(){
    ArrayList<Penguin> brownPenguins = gameState1.getPenguins(PenguinColor.brown);
    assertEquals(0, brownPenguins.size());
  }

  @Test
  public void getRedPlayerInfo() {
    PlayerInfo playerInfo = gameState1.getPlayerInfo(PenguinColor.red);
    assertEquals(playerInfoRed, playerInfo);
  }

  @Test
  public void getBlackPlayerInfo() {
    PlayerInfo playerInfo = gameState1.getPlayerInfo(PenguinColor.black);
    assertEquals(playerInfoBlack, playerInfo);
  }

  @Test
  public void getWhitePlayerInfo() {
    PlayerInfo playerInfo = gameState1.getPlayerInfo(PenguinColor.white);
    assertEquals(playerInfoWhite, playerInfo);
  }

  @Test
  public void getBrownPlayerInfo() {
    try {
      PlayerInfo playerInfo = gameState1.getPlayerInfo(PenguinColor.brown);
    } catch (IllegalArgumentException e){
      assertEquals("Error: no player with the color.", e.getMessage());
    }
  }

  @Test
  public void getRedPlayerGain0(){
    int gain = gameState1.getPlayerScore(PenguinColor.red);
    assertEquals(0, gain);
  }

  @Test
  public void getRedPlayerGain1(){
    int gain = gameState1.getPlayerScore(PenguinColor.red);
    assertEquals(0, gain);

    FishState nextState = gameState1.placeInitPenguin(0, 0, playerInfoRed);
    gain = nextState.getPlayerScore(PenguinColor.red);
    assertEquals(1, gain);
  }

  @Test
  public void getRedPlayerGain2(){
    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerInfoRed);

    ArrayList<PlayerInfo> playerInfos = nextState1.getAllPlayerInfos();
    playerInfoBlack = playerInfos.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerInfoBlack);

    ArrayList<PlayerInfo> players2 = nextState2.getAllPlayerInfos();
    playerInfoWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerInfoWhite);

    ArrayList<PlayerInfo> players3 = nextState3.getAllPlayerInfos();
    playerInfoRed = players3.get(nextState3.getCurrentPlayerIndex());
    FishState nextState4 = nextState3.placeInitPenguin(0, 0, playerInfoRed);

    int gain = nextState4.getPlayerScore(PenguinColor.red);
    assertEquals(2, gain);
  }

  @Test
  public void getBrownPlayerGainInvalid(){
    try {
      int gain = gameState1.getPlayerScore(PenguinColor.brown);
    }catch (IllegalArgumentException e){
      assertEquals("Error: No player found with the given color.", e.getMessage());
    }


  }



}
