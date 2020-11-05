package common.actions;

import java.util.ArrayList;

import common.models.FishModel;
import common.models.FishState;
import common.models.PenguinColor;
import common.models.PlayerInfo;
import org.junit.Before;

public class MovePenguinActionTest {

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

//
//  @Test
//  public void moveFailed() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);
//
//    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(currentPlayerIndex);
//    MovePenguinAction moveAction = new MovePenguinAction(2, 4, penguinRed, playerRed);
//
//    try {
//      moveAction.performAction(nextState2);
//    } catch (IllegalArgumentException e){
//      assertEquals("Error: Not your turn.", e.getMessage());
//    }
//  }
//
//  @Test
//  public void moveFailed1() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);
//
//    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(currentPlayerIndex);
//    MovePenguinAction moveAction = new MovePenguinAction(-1, 4, penguinRed, playerRed);
//
//    try {
//      moveAction.performAction(nextState3);
//    } catch (IllegalArgumentException e){
//      assertEquals("Error: Target position is out of board.", e.getMessage());
//    }
//  }
//
//  @Test
//  public void moveFailed2() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);
//
//    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(currentPlayerIndex);
//    MovePenguinAction moveAction = new MovePenguinAction(1, 1, penguinRed, playerRed);
//
//    try {
//      moveAction.performAction(nextState3);
//    } catch (IllegalArgumentException e){
//      assertEquals("Error: Invalid position to move to.", e.getMessage());
//    }
//  }
//
//  @Test
//  public void moveFailed3() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(2, 4, playerWhite);
//
//    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(currentPlayerIndex);
//    MovePenguinAction moveAction = new MovePenguinAction(2, 4, penguinRed, playerRed);
//
//    try {
//      moveAction.performAction(nextState3);
//    } catch (IllegalArgumentException e){
//      assertEquals("Error: Invalid position to move to.", e.getMessage());
//    }
//  }
//
//  @Test
//  public void moveFailed4() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(2, 4, playerWhite);
//
//    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
//    Penguin penguinRed = new Penguin(PenguinColor.red);
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(currentPlayerIndex);
//    MovePenguinAction moveAction = new MovePenguinAction(2, 4, penguinRed, playerRed);
//
//    try {
//      moveAction.performAction(nextState3);
//    } catch (IllegalArgumentException e){
//      assertEquals("Error: The penguin is not on the board.", e.getMessage());
//    }
//  }
//
//  @Test
//  public void moveFailed5() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(2, 4, playerWhite);
//
//    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
//    Penguin penguinWhite = nextState3.getPenguinsOnBoard().get(2);
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(currentPlayerIndex);
//    MovePenguinAction moveAction = new MovePenguinAction(2, 4, penguinWhite, playerRed);
//
//    try {
//      moveAction.performAction(nextState3);
//    } catch (IllegalArgumentException e){
//      assertEquals("Error: Not the owner of the penguin.", e.getMessage());
//    }
//  }
//
//  @Test
//  public void moveSucceed() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);
//
//    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(currentPlayerIndex);
//    MovePenguinAction moveAction = new MovePenguinAction(2, 4, penguinRed, playerRed);
//
//    assertEquals(2, penguinRed.getXPos());
//    assertEquals(2, penguinRed.getYPos());
//    FishState nextState4 = moveAction.performAction(nextState3);
//    penguinRed = nextState4.getPenguinsOnBoard().get(0);
//    assertEquals(2, penguinRed.getXPos());
//    assertEquals(4, penguinRed.getYPos());
//  }
//
//  @Test
//  public void moveSucceed1() {
//    FishState nextState1 = gameState1.placeInitPenguin(2, 2, playerRed);
//
//    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
//    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
//    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
//
//    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
//    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
//    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);
//
//    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
//    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
//    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
//    playerRed = players3.get(currentPlayerIndex);
//    MovePenguinAction moveAction = new MovePenguinAction(2, 4, penguinRed, playerRed);
//
//    assertEquals(2, penguinRed.getXPos());
//    assertEquals(2, penguinRed.getYPos());
//    FishState nextState4 = moveAction.performAction(nextState3);
//    penguinRed = nextState4.getPenguinsOnBoard().get(0);
//    assertEquals(2, penguinRed.getXPos());
//    assertEquals(4, penguinRed.getYPos());
//
//    currentPlayerIndex = nextState4.getCurrentPlayerIndex();
//    Penguin penguinBlack = nextState4.getPenguinsOnBoard().get(1);
//    ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
//    playerBlack = players4.get(currentPlayerIndex);
//    MovePenguinAction moveAction1 = new MovePenguinAction(1, 3, penguinBlack, playerBlack);
//
//
//    assertEquals(0, penguinBlack.getXPos());
//    assertEquals(1, penguinBlack.getYPos());
//    FishState nextState5 = moveAction1.performAction(nextState4);
//    penguinBlack = nextState5.getPenguinsOnBoard().get(1);
//    assertEquals(1, penguinBlack.getXPos());
//    assertEquals(3, penguinBlack.getYPos());
//  }
}
