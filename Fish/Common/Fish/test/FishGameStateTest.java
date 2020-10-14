import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import models.FishGameState;
import models.FishModel;
import models.Penguin;
import models.PenguinColor;
import models.Player;
import models.Tile;
import org.junit.Before;
import org.junit.Test;


/** FishGameTest for the FishGameState class.
 **/

public class FishGameStateTest {

  private int width1;
  private int height1;
  private int width2;
  private int height2;
  private int maxFishNum1;
  private boolean isRandom;
  private FishModel fourByEightFishModel;
  private FishModel oneByThreeFishModel;
  private Player player1;
  private Player player2;
  private Player player3;
  private Penguin penguinRed1;
  private Penguin penguinBlack1;
  private Penguin penguinWhite1;
  private ArrayList<Player> twoPlayers;
  private ArrayList<Player> threePlayers;
  private FishGameState gameState1;
  private FishGameState gameState2;


  @Before
  public void init(){

    width1 = 4;
    height1 = 8;
    maxFishNum1 = 1;
    isRandom = true;
    fourByEightFishModel = new FishModel(width1, height1, maxFishNum1, isRandom);
    width2 = 1;
    height2 = 3;
    oneByThreeFishModel = new FishModel(width2, height2, maxFishNum1, isRandom);
    player1 = new Player(11);
    player2 = new Player(12);
    player3 = new Player(13);
    penguinRed1 = new Penguin(player1, PenguinColor.RED);
    penguinBlack1 = new Penguin(player2, PenguinColor.BLACK);
    penguinWhite1 = new Penguin(player3, PenguinColor.WHITE);
    player1.addPenguin(penguinRed1);
    player2.addPenguin(penguinBlack1);
    player3.addPenguin(penguinWhite1);
    twoPlayers = new ArrayList<Player>();
    twoPlayers.add(player1);
    twoPlayers.add(player2);
    threePlayers = new ArrayList<Player>();
    threePlayers.add(player1);
    threePlayers.add(player2);
    threePlayers.add(player3);
    gameState1 = new FishGameState(fourByEightFishModel, threePlayers);
    gameState2 = new FishGameState(oneByThreeFishModel, twoPlayers);
  }

  @Test
  public void placePenguinSucceed(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    assertEquals(0, penguinRed1.getXPos());
    assertEquals(0, penguinRed1.getYPos());
  }

  @Test
  public void placePenguinSucceed1(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    assertEquals(0, penguinRed1.getXPos());
    assertEquals(0, penguinRed1.getYPos());

    gameState1.placeInitPenguin(0, 1, penguinBlack1);
    assertEquals(0, penguinBlack1.getXPos());
    assertEquals(1, penguinBlack1.getYPos());
  }

  @Test
  public void placePenguinFailed(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    assertEquals(0, penguinRed1.getXPos());
    assertEquals(0, penguinRed1.getYPos());

    /** Code Source of converting system.out.println to String:
     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
     */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    gameState1.placeInitPenguin(0, 0, penguinBlack1);
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("Error: There is already one penguin on the target tile.\n", baos.toString());
  }

  @Test
  public void placePenguinFailed1(){

    /** Code Source of converting system.out.println to String:
     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
     */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    gameState1.placeInitPenguin(100, 100, penguinRed1);
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("Error: Target position is out of board.\n", baos.toString());
  }

  @Test
  public void placePenguinFailed2(){

    /** Code Source of converting system.out.println to String:
     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
     */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    gameState1.placeInitPenguin(-1, -10, penguinRed1);
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("Error: Target position is out of board.\n", baos.toString());
  }

  @Test
  public void placePenguinFailed4(){

    /** Code Source of converting system.out.println to String:
     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
     */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    gameState1.placeInitPenguin(0, 0, penguinBlack1);
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("Error: Not your turn.\n", baos.toString());
  }

  @Test
  public void placePenguinFailed3(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);

    /** Code Source of converting system.out.println to String:
     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
     */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    gameState1.placeInitPenguin(0, 0, penguinBlack1);
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("Error: There is already one penguin on the target tile.\n", baos.toString());
  }

  @Test
  public void makeMovementSucceed(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    gameState1.placeInitPenguin(0, 1, penguinBlack1);
    gameState1.placeInitPenguin(1, 1, penguinWhite1);

    assertEquals(0, penguinRed1.getXPos());
    assertEquals(0, penguinRed1.getYPos());
    gameState1.makeMovement(0, 2, penguinRed1);
    assertEquals(0, penguinRed1.getXPos());
    assertEquals(2, penguinRed1.getYPos());
  }

  @Test
  public void makeMovementSucceed1(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    gameState1.placeInitPenguin(0, 1, penguinBlack1);
    gameState1.placeInitPenguin(1, 1, penguinWhite1);

    assertEquals(0, penguinRed1.getXPos());
    assertEquals(0, penguinRed1.getYPos());
    gameState1.makeMovement(0, 2, penguinRed1);
    assertEquals(0, penguinRed1.getXPos());
    assertEquals(2, penguinRed1.getYPos());

    assertEquals(0, penguinBlack1.getXPos());
    assertEquals(1, penguinBlack1.getYPos());
    gameState1.makeMovement(0, 5, penguinBlack1);
    assertEquals(0, penguinBlack1.getXPos());
    assertEquals(5, penguinBlack1.getYPos());
  }

  @Test
  public void makeMovementSucceed3(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    gameState1.placeInitPenguin(0, 1, penguinBlack1);
    gameState1.placeInitPenguin(1, 1, penguinWhite1);

    assertEquals(0, penguinRed1.getXPos());
    assertEquals(0, penguinRed1.getYPos());
    gameState1.makeMovement(0, 2, penguinRed1);
    assertEquals(0, penguinRed1.getXPos());
    assertEquals(2, penguinRed1.getYPos());

    assertEquals(0, penguinBlack1.getXPos());
    assertEquals(1, penguinBlack1.getYPos());
    gameState1.makeMovement(0, 5, penguinBlack1);
    assertEquals(0, penguinBlack1.getXPos());
    assertEquals(5, penguinBlack1.getYPos());

    assertEquals(1, penguinWhite1.getXPos());
    assertEquals(1, penguinWhite1.getYPos());
    gameState1.makeMovement(2, 0, penguinWhite1);
    assertEquals(2, penguinWhite1.getXPos());
    assertEquals(0, penguinWhite1.getYPos());
  }

  @Test
  public void makeMovementFailed(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    gameState1.placeInitPenguin(0, 1, penguinBlack1);
    gameState1.placeInitPenguin(1, 1, penguinWhite1);

    gameState1.makeMovement(0, 2, penguinRed1);

/** Code Source of converting system.out.println to String:
 *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
 */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    gameState1.makeMovement(1, 7, penguinWhite1);
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("Error: Not your turn.\n", baos.toString());
  }

  @Test
  public void makeMovementFailed1(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    gameState1.placeInitPenguin(0, 1, penguinBlack1);
    gameState1.placeInitPenguin(1, 1, penguinWhite1);

/** Code Source of converting system.out.println to String:
 *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
 */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    gameState1.makeMovement(0, -1, penguinRed1);
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("Error: Target position is out of board.\n", baos.toString());
  }

  @Test
  public void makeMovementFailed2(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    gameState1.placeInitPenguin(0, 1, penguinBlack1);
    gameState1.placeInitPenguin(1, 1, penguinWhite1);

/** Code Source of converting system.out.println to String:
 *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
 */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    gameState1.makeMovement(1,0, penguinRed1);
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("Error: Invalid position to move to.\n", baos.toString());
  }

  @Test
  public void makeMovementFailed3(){
    gameState1.placeInitPenguin(0, 0, penguinRed1);
    gameState1.placeInitPenguin(0, 1, penguinBlack1);
    gameState1.placeInitPenguin(1, 1, penguinWhite1);

    gameState1.makeMovement(0, 2, penguinRed1);
/** Code Source of converting system.out.println to String:
 *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
 */
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print
    gameState1.makeMovement(0,2, penguinBlack1);
    // Put things back
    System.out.flush();
    System.setOut(old);
    assertEquals("Error: Invalid position to move to.\n", baos.toString());
  }

  @Test
  public void gameNotOver(){
    gameState2.placeInitPenguin(0, 0, penguinRed1);
    boolean isGameOver = gameState2.isGameOver();
    assertEquals(false, isGameOver);
  }

  @Test
  public void gameNotOver1(){
    gameState2.placeInitPenguin(0, 0, penguinRed1);
    gameState2.placeInitPenguin(0, 1, penguinBlack1);
    boolean isGameOver = gameState2.isGameOver();
    assertEquals(false, isGameOver);
  }

  @Test
  public void gameNotOver2(){
    boolean isGameOver = gameState2.isGameOver();
    assertEquals(false, isGameOver);
  }

  @Test
  public void gameOver(){
    gameState2.placeInitPenguin(0, 0, penguinRed1);
    gameState2.placeInitPenguin(0, 1, penguinBlack1);
//    gameState2.placeInitPenguin(1, 1, penguinWhite1);
    gameState2.makeMovement(0,2,penguinRed1);
    boolean isGameOver = gameState2.isGameOver();
    assertEquals(true, isGameOver);
  }

}
