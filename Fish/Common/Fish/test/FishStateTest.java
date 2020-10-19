import static org.junit.Assert.*;

import java.util.ArrayList;
import models.FishState;
import models.FishModel;
import models.Penguin;
import models.PenguinColor;
import models.Player;
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
    playerRed = new Player(11, PenguinColor.RED);
    playerBlack = new Player(12, PenguinColor.BLACK);
    playerWhite = new Player(13, PenguinColor.WHITE);
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
    gameState1.placeInitPenguin(0, 0, playerRed);
    int penguinX = gameState1.getPenguinsOnBoard().get(0).getXPos();
    int penguinY = gameState1.getPenguinsOnBoard().get(0).getYPos();
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

    FishState nextState2 = nextState1.placeInitPenguin(0, 1, playerBlack);
    int penguinX2 = nextState2.getPenguinsOnBoard().get(1).getXPos();
    int penguinY2 = nextState2.getPenguinsOnBoard().get(1).getYPos();
    assertEquals(0, penguinX2);
    assertEquals(1, penguinY2);
  }

  @Test//(expected = IllegalArgumentException.class)
  public void placePenguinFailed() {
    gameState1.placeInitPenguin(0, 0, playerRed);
    int penguinX1 = gameState1.getPenguinsOnBoard().get(0).getXPos();
    int penguinY1 = gameState1.getPenguinsOnBoard().get(0).getYPos();
    assertEquals(0, penguinX1);
    assertEquals(0, penguinY1);
    try {
      gameState1.placeInitPenguin(0, 0, playerBlack);
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
    gameState1.placeInitPenguin(0, 0, playerRed);
    gameState1.placeInitPenguin(0, 1, playerBlack);
    gameState1.placeInitPenguin(1, 1, playerWhite);

    Penguin penguinRed = gameState1.getPenguinsOnBoard().get(0);
    int penguinRedX = penguinRed.getXPos();
    int penguinRedY = penguinRed.getYPos();
    assertEquals(0, penguinRedX);
    assertEquals(0, penguinRedY);

    gameState1.makeMovement(0, 2, penguinRed, playerRed);
    assertEquals(0, gameState1.getPenguinsOnBoard().get(0).getXPos());
    assertEquals(2, gameState1.getPenguinsOnBoard().get(0).getYPos());
  }

  @Test
  public void makeMovementSucceed1() {
    gameState1.placeInitPenguin(0, 0, playerRed);
    gameState1.placeInitPenguin(0, 1, playerBlack);
    gameState1.placeInitPenguin(1, 1, playerWhite);

    Penguin penguinRed = gameState1.getPenguinsOnBoard().get(0);
    int penguinRedX = penguinRed.getXPos();
    int penguinRedY = penguinRed.getYPos();
    assertEquals(0, penguinRedX);
    assertEquals(0, penguinRedY);
    gameState1.makeMovement(0, 2, penguinRed, playerRed);
    assertEquals(0, gameState1.getPenguinsOnBoard().get(0).getXPos());
    assertEquals(2, gameState1.getPenguinsOnBoard().get(0).getYPos());

    Penguin penguinBlack = gameState1.getPenguinsOnBoard().get(1);
    int penguinBlackX = penguinBlack.getXPos();
    int penguinBlackY = penguinBlack.getYPos();
    assertEquals(0, penguinBlackX);
    assertEquals(1, penguinBlackY);
    gameState1.makeMovement(0, 5, penguinBlack, playerBlack);
    assertEquals(0, gameState1.getPenguinsOnBoard().get(1).getXPos());
    assertEquals(5, gameState1.getPenguinsOnBoard().get(1).getYPos());
  }

  @Test
  public void makeMovementSucceed3() {
    gameState1.placeInitPenguin(0, 0, playerRed);
    gameState1.placeInitPenguin(0, 1, playerBlack);
    gameState1.placeInitPenguin(1, 1, playerWhite);

    Penguin penguinRed = gameState1.getPenguinsOnBoard().get(0);
    int penguinRedX = penguinRed.getXPos();
    int penguinRedY = penguinRed.getYPos();
    assertEquals(0, penguinRedX);
    assertEquals(0, penguinRedY);
    gameState1.makeMovement(0, 2, penguinRed, playerRed);
    assertEquals(0, gameState1.getPenguinsOnBoard().get(0).getXPos());
    assertEquals(2, gameState1.getPenguinsOnBoard().get(0).getYPos());

    Penguin penguinBlack = gameState1.getPenguinsOnBoard().get(1);
    int penguinBlackX = penguinBlack.getXPos();
    int penguinBlackY = penguinBlack.getYPos();
    assertEquals(0, penguinBlackX);
    assertEquals(1, penguinBlackY);
    gameState1.makeMovement(0, 5, penguinBlack, playerBlack);
    assertEquals(0, gameState1.getPenguinsOnBoard().get(1).getXPos());
    assertEquals(5, gameState1.getPenguinsOnBoard().get(1).getYPos());

    Penguin penguinWhite = gameState1.getPenguinsOnBoard().get(2);
    int penguinWhiteX = penguinWhite.getXPos();
    int penguinWhiteY = penguinWhite.getYPos();
    assertEquals(1, penguinWhiteX);
    assertEquals(1, penguinWhiteY);
    gameState1.makeMovement(2, 0, penguinWhite, playerWhite);
    assertEquals(2, gameState1.getPenguinsOnBoard().get(2).getXPos());
    assertEquals(0, gameState1.getPenguinsOnBoard().get(2).getYPos());
  }

  @Test
  public void makeMovementFailed() {
    gameState1.placeInitPenguin(0, 0, playerRed);
    gameState1.placeInitPenguin(0, 1, playerBlack);
    gameState1.placeInitPenguin(1, 1, playerWhite);

    Penguin penguinRed = gameState1.getPenguinsOnBoard().get(0);
    gameState1.makeMovement(0, 2, penguinRed, playerRed);

    try {
      Penguin penguinWhite = gameState1.getPenguinsOnBoard().get(2);
      gameState1.makeMovement(2, 0, penguinWhite, playerWhite);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Not your turn.", e.getMessage());
    }
  }

  @Test
  public void makeMovementFailed1() {
    gameState1.placeInitPenguin(0, 0, playerRed);
    gameState1.placeInitPenguin(0, 1, playerBlack);
    gameState1.placeInitPenguin(1, 1, playerWhite);

    try {
      Penguin penguinRed = gameState1.getPenguinsOnBoard().get(0);
      gameState1.makeMovement(0, -2, penguinRed, playerRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Target position is out of board.", e.getMessage());
    }
  }

  @Test
  public void makeMovementFailed2() {
    gameState1.placeInitPenguin(0, 0, playerRed);
    gameState1.placeInitPenguin(0, 1, playerBlack);
    gameState1.placeInitPenguin(1, 1, playerWhite);

    try {
      Penguin penguinRed = gameState1.getPenguinsOnBoard().get(0);
      gameState1.makeMovement(1, 0, penguinRed, playerRed);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Invalid position to move to.", e.getMessage());
    }
  }

  @Test
  public void makeMovementFailed3() {
    gameState1.placeInitPenguin(0, 0, playerRed);
    gameState1.placeInitPenguin(0, 1, playerBlack);
    gameState1.placeInitPenguin(1, 1, playerWhite);

    Penguin penguinRed = gameState1.getPenguinsOnBoard().get(0);
    gameState1.makeMovement(0, 2, penguinRed, playerRed);

    try {
      Penguin penguinBlack = gameState1.getPenguinsOnBoard().get(1);
      gameState1.makeMovement(0, 2, penguinBlack, playerBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Invalid position to move to.", e.getMessage());
    }

  }

  @Test
  public void makeMovementFailed4() {
    gameState1.placeInitPenguin(0, 0, playerRed);
    gameState1.placeInitPenguin(0, 1, playerBlack);
    gameState1.placeInitPenguin(1, 1, playerWhite);

    Penguin penguinRed = gameState1.getPenguinsOnBoard().get(0);
    gameState1.makeMovement(0, 2, penguinRed, playerRed);

    try {
      Penguin penguinBlack = gameState1.getPenguinsOnBoard().get(1);
      gameState1.makeMovement(0, 2, penguinRed, playerBlack);
    } catch (IllegalArgumentException e) {
      assertEquals("Error: not the owner of the penguin.", e.getMessage());
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
    gameState2.placeInitPenguin(0, 0, playerRed);
    gameState2.placeInitPenguin(0, 1, playerBlack);
    boolean isGameOver = gameState2.isGameOver();
    assertEquals(false, isGameOver);
  }

  @Test
  public void gameNotOver2() {
    boolean isGameOver = gameState2.isGameOver();
    assertEquals(false, isGameOver);
  }

  @Test
  public void gameOver() {
    gameState2.placeInitPenguin(0, 0, playerRed);
    gameState2.placeInitPenguin(0, 1, playerBlack);
//    gameState2.placeInitPenguin(1, 1, penguinWhite1);
    Penguin penguinRed = gameState2.getPenguinsOnBoard().get(0);
    gameState2.makeMovement(0, 2, penguinRed, playerRed);
    boolean isGameOver = gameState2.isGameOver();
    assertEquals(true, isGameOver);
  }

  @Test
  public void isPenguinOwner(){
    gameState2.placeInitPenguin(0, 0, playerRed);
    gameState2.placeInitPenguin(0, 1, playerBlack);

    Penguin penguinBlack = gameState2.getPenguinsOnBoard().get(1);
    assertEquals(true, gameState2.isPenguinOwner(penguinBlack, playerBlack));
  }

  @Test
  public void isPenguinOwner1(){
    gameState2.placeInitPenguin(0, 0, playerRed);
    gameState2.placeInitPenguin(0, 1, playerBlack);

    Penguin penguinRed = gameState2.getPenguinsOnBoard().get(0);
    assertEquals(true, gameState2.isPenguinOwner(penguinRed, playerRed));
  }

  @Test
  public void notPenguinOwner(){
    gameState2.placeInitPenguin(0, 0, playerRed);
    gameState2.placeInitPenguin(0, 1, playerBlack);

    Penguin penguinBlack = gameState2.getPenguinsOnBoard().get(1);
    assertEquals(false, gameState2.isPenguinOwner(penguinBlack, playerRed));
  }

  @Test
  public void notPenguinOwner2(){
    gameState2.placeInitPenguin(0, 0, playerRed);
    gameState2.placeInitPenguin(0, 1, playerBlack);

    Penguin penguinRed = gameState2.getPenguinsOnBoard().get(0);
    assertEquals(false, gameState2.isPenguinOwner(penguinRed, playerWhite));
  }

}
