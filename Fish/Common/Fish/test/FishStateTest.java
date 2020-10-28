import static org.junit.Assert.*;

import java.util.ArrayList;
import common.models.FishState;
import common.models.FishModel;
import common.models.Penguin;
import common.models.PenguinColor;
import common.models.Player;
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
  private Player playerRed;
  private Player playerBlack;
  private Player playerWhite;
  private ArrayList<Player> twoPlayers;
  private ArrayList<Player> threePlayers;
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
  }

  @Test
  public void placePenguinSucceed() {
    FishState nextState = gameState1.placeInitPenguin(0, 0, playerRed);
    int penguinX = nextState.getPenguinsOnBoard().get(0).getXPos();
    int penguinY = nextState.getPenguinsOnBoard().get(0).getYPos();
    assertEquals(0, penguinX);
    assertEquals(0, penguinY);
  }
//
  @Test
  public void placePenguinSucceed1() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);
    int penguinX1 = nextState1.getPenguinsOnBoard().get(0).getXPos();
    int penguinY1 = nextState1.getPenguinsOnBoard().get(0).getYPos();
    assertEquals(0, penguinX1);
    assertEquals(0, penguinY1);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
    int penguinX2 = nextState2.getPenguinsOnBoard().get(1).getXPos();
    int penguinY2 = nextState2.getPenguinsOnBoard().get(1).getYPos();
    assertEquals(0, penguinX2);
    assertEquals(1, penguinY2);
  }

  @Test//(expected = IllegalArgumentException.class)
  public void placePenguinFailed() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);
//    int penguinX1 = nextState1.getPenguinsOnBoard().get(0).getXPos();
//    int penguinY1 = nextState1.getPenguinsOnBoard().get(0).getYPos();
////    assertEquals(0, penguinX1);
//    assertEquals(0, penguinY1);
    try {
      ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
      playerBlack = players.get(nextState1.getCurrentPlayerIndex());
      FishState nextState2 = nextState1.placeInitPenguin(0, 0, playerBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: There is already one penguin on the target tile.", e.getMessage());
    }
  }

  @Test
  public void placePenguinFailed1() {
    try {
      gameState1.placeInitPenguin(100, 100, playerRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }

  @Test
  public void placePenguinFailed2() {
    try {
      gameState1.placeInitPenguin(-1, -10, playerRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }


  @Test
  public void placePenguinFailed3() {
    try {
      gameState1.placeInitPenguin(-1, 10, playerRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }

  @Test
  public void placePenguinFailed4() {
    try {
      gameState1.placeInitPenguin(1, -10, playerRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }

  @Test
  public void placePenguinFailed5() {
    try {
      gameState1.placeInitPenguin(0, 0, playerBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Not your turn.", e.getMessage());
    }
  }



  @Test
  public void makeMovementSucceed() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);

    int currentPlayerNum = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
    playerRed = players3.get(currentPlayerNum);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerRed);
    assertEquals(0, nextState4.getPenguinsOnBoard().get(0).getXPos());
    assertEquals(2, nextState4.getPenguinsOnBoard().get(0).getYPos());
  }

  @Test
  public void makeMovementSucceed1() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);

    int currentPlayerNum = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
    playerRed = players3.get(currentPlayerNum);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerRed);
    assertEquals(0, nextState4.getPenguinsOnBoard().get(0).getXPos());
    assertEquals(2, nextState4.getPenguinsOnBoard().get(0).getYPos());

    currentPlayerNum = nextState4.getCurrentPlayerIndex();
    Penguin penguinBlack = nextState4.getPenguinsOnBoard().get(1);
    ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
    playerBlack = players4.get(currentPlayerNum);
    FishState nextState5 = nextState4.makeMovement(0, 5, penguinBlack, playerBlack);
    assertEquals(0, nextState5.getPenguinsOnBoard().get(1).getXPos());
    assertEquals(5, nextState5.getPenguinsOnBoard().get(1).getYPos());
  }

  @Test
  public void makeMovementSucceed3() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);

    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
    playerRed = players3.get(currentPlayerIndex);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerRed);
    assertEquals(0, nextState4.getPenguinsOnBoard().get(0).getXPos());
    assertEquals(2, nextState4.getPenguinsOnBoard().get(0).getYPos());

    currentPlayerIndex = nextState4.getCurrentPlayerIndex();
    Penguin penguinBlack = nextState4.getPenguinsOnBoard().get(1);
    ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
    playerBlack = players4.get(currentPlayerIndex);
    FishState nextState5 = nextState4.makeMovement(0, 5, penguinBlack, playerBlack);
    assertEquals(0, nextState5.getPenguinsOnBoard().get(1).getXPos());
    assertEquals(5, nextState5.getPenguinsOnBoard().get(1).getYPos());

    currentPlayerIndex = nextState5.getCurrentPlayerIndex();
    Penguin penguinWhite = nextState5.getPenguinsOnBoard().get(2);
    ArrayList<Player> players5 = nextState5.getPlayersSortedByAgeAscend();
    playerWhite = players5.get(currentPlayerIndex);
    FishState nextState6 = nextState5.makeMovement(2, 0, penguinWhite, playerWhite);
    assertEquals(2, nextState6.getPenguinsOnBoard().get(2).getXPos());
    assertEquals(0, nextState6.getPenguinsOnBoard().get(2).getYPos());

  }

  @Test
  public void makeMovementFailed() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);

    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
    playerRed = players3.get(currentPlayerIndex);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerRed);

    try {
      currentPlayerIndex = nextState4.getCurrentPlayerIndex();
      Penguin penguinWhite = nextState4.getPenguinsOnBoard().get(2);
      ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
      playerWhite = players4.get(2);
      FishState nextState6 = nextState4.makeMovement(2, 0, penguinWhite, playerWhite);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Not your turn.", e.getMessage());
    }
  }

  @Test
  public void makeMovementFailed1() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);

    try {
      int currentPlayerNum = nextState3.getCurrentPlayerIndex();
      Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
      ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
      playerRed = players3.get(currentPlayerNum);
      FishState nextState4 = nextState3.makeMovement(0, -2, penguinRed, playerRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }

  @Test
  public void makeMovementFailed2() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);

    try {
      int currentPlayerNum = nextState3.getCurrentPlayerIndex();
      Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
      ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
      playerRed = players3.get(currentPlayerNum);
      FishState nextState4 = nextState3.makeMovement(1, 0, penguinRed, playerRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Invalid position to move to.", e.getMessage());
    }
  }

  @Test
  public void makeMovementFailed3() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);

    int currentPlayerNum = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
    playerRed = players3.get(currentPlayerNum);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerRed);

    try {
      currentPlayerNum = nextState4.getCurrentPlayerIndex();
      Penguin penguinBlack = nextState4.getPenguinsOnBoard().get(1);
      ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
      playerBlack = players4.get(currentPlayerNum);
      FishState nextState5 = nextState4.makeMovement(0, 2, penguinBlack, playerBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Invalid position to move to.", e.getMessage());
    }

  }

  @Test
  public void makeMovementFailed4() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    ArrayList<Player> players2 = nextState2.getPlayersSortedByAgeAscend();
    playerWhite = players2.get(nextState2.getCurrentPlayerIndex());
    FishState nextState3 = nextState2.placeInitPenguin(1, 1, playerWhite);

    int currentPlayerIndex = nextState3.getCurrentPlayerIndex();
    Penguin penguinRed = nextState3.getPenguinsOnBoard().get(0);
    ArrayList<Player> players3 = nextState3.getPlayersSortedByAgeAscend();
    playerRed = players3.get(currentPlayerIndex);
    FishState nextState4 = nextState3.makeMovement(0, 2, penguinRed, playerRed);

    try {
      currentPlayerIndex = nextState4.getCurrentPlayerIndex();
      Penguin penguinWhite = nextState4.getPenguinsOnBoard().get(2);
      ArrayList<Player> players4 = nextState4.getPlayersSortedByAgeAscend();
      playerBlack = players4.get(currentPlayerIndex);
      FishState nextState5 = nextState4.makeMovement(2, 0, penguinWhite, playerBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Not the owner of the penguin.", e.getMessage());
    }

  }

  @Test
  public void gameNotOver() {
    gameState2.placeInitPenguin(0, 0, playerRed);
    boolean isGameOver = gameState2.isGameOver();
    assertEquals(false, isGameOver);
  }

  @Test
  public void gameNotOver1() {
    FishState nextState1 = gameState1.placeInitPenguin(0, 0, playerRed);
    int penguinX1 = nextState1.getPenguinsOnBoard().get(0).getXPos();
    int penguinY1 = nextState1.getPenguinsOnBoard().get(0).getYPos();
    assertEquals(0, penguinX1);
    assertEquals(0, penguinY1);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

//    gameState2.placeInitPenguin(0, 0, playerRed);
//    gameState2.placeInitPenguin(0, 1, playerBlack);
    boolean isGameOver = nextState2.isGameOver();
    assertEquals(false, isGameOver);
  }

  @Test
  public void gameNotOver2() {
    boolean isGameOver = gameState2.isGameOver();
    assertEquals(false, isGameOver);
  }

  @Test
  public void gameOver() {


    FishState nextState1 = gameState2.placeInitPenguin(0, 0, playerRed);


    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    int currentPlayerIndex = nextState2.getCurrentPlayerIndex();
    Penguin penguinRed = nextState2.getPenguinsOnBoard().get(0);
    ArrayList<Player> players3 = nextState2.getPlayersSortedByAgeAscend();
    playerRed = players3.get(currentPlayerIndex);
    FishState nextState3 = nextState2.makeMovement(0, 2, penguinRed, playerRed);

    boolean isGameOver = nextState3.isGameOver();
    assertEquals(true, isGameOver);

  }

  @Test
  public void addTotalFishNum() {

//    System.out.println(gameState2.getPlayersSortedByAgeAscend().get(0).getPenguinColor());
//    System.out.println(gameState2.getPlayersSortedByAgeAscend().get(0).getTotalFish());
    int totalFishNum = gameState2.getPlayersSortedByAgeAscend().get(0).getTotalFish();
    assertEquals(0, totalFishNum);

    FishState nextState1 = gameState2.placeInitPenguin(0, 0, playerRed);
    totalFishNum += nextState1.getBoard().get(0).get(0).getFishNum();
    int currentTotalFish = nextState1.getPlayersSortedByAgeAscend().get(0).getTotalFish();
    assertEquals(currentTotalFish, totalFishNum);

    ArrayList<Player> players = nextState1.getPlayersSortedByAgeAscend();
    playerBlack = players.get(nextState1.getCurrentPlayerIndex());
    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);

    int currentPlayerIndex = nextState2.getCurrentPlayerIndex();
    Penguin penguinRed = nextState2.getPenguinsOnBoard().get(0);
    ArrayList<Player> players3 = nextState2.getPlayersSortedByAgeAscend();
    playerRed = players3.get(currentPlayerIndex);
    FishState nextState3 = nextState2.makeMovement(0, 2, penguinRed, playerRed);

    totalFishNum += nextState3.getBoard().get(2).get(0).getFishNum();
    currentTotalFish = nextState3.getPlayersSortedByAgeAscend().get(0).getTotalFish();
    assertEquals(currentTotalFish, totalFishNum);


  }

//  @Test
//  public void isPenguinOwner(){
//    gameState2.placeInitPenguin(0, 0, playerRed);
//    gameState2.placeInitPenguin(0, 1, playerBlack);
//
//    Penguin penguinBlack = gameState2.getPenguinsOnBoard().get(1);
//    assertEquals(true, gameState2.isPenguinOwner(penguinBlack, playerBlack));
//  }
//
//  @Test
//  public void isPenguinOwner1(){
//    gameState2.placeInitPenguin(0, 0, playerRed);
//    gameState2.placeInitPenguin(0, 1, playerBlack);
//
//    Penguin penguinRed = gameState2.getPenguinsOnBoard().get(0);
//    assertEquals(true, gameState2.isPenguinOwner(penguinRed, playerRed));
//  }
//
//  @Test
//  public void notPenguinOwner(){
//    gameState2.placeInitPenguin(0, 0, playerRed);
//    gameState2.placeInitPenguin(0, 1, playerBlack);
//
//    Penguin penguinBlack = gameState2.getPenguinsOnBoard().get(1);
//    assertEquals(false, gameState2.isPenguinOwner(penguinBlack, playerRed));
//  }
//
//  @Test
//  public void notPenguinOwner2(){
//    gameState2.placeInitPenguin(0, 0, playerRed);
//    gameState2.placeInitPenguin(0, 1, playerBlack);
//
//    Penguin penguinRed = gameState2.getPenguinsOnBoard().get(0);
//    assertEquals(false, gameState2.isPenguinOwner(penguinRed, playerWhite));
//  }

}
