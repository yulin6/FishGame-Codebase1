package models;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * a class for testing the FishModel.
 */
public class FishModelTest {

  private FishModel fishModel;
  private FishModel emptyFishModel;
  private int width1;
  private int height1;
  private int maxFishNum1;
  private boolean isRandom;

  @Before
  public void init() {
    width1 = 4;
    height1 = 8;
    maxFishNum1 = 5;
    isRandom = true;
    fishModel = new FishModel(width1, height1, maxFishNum1, isRandom);
    emptyFishModel = new FishModel();
  }

  @Test
  public void emptyTileValid1(){
    fishModel.emptyTile(0,0);
    ArrayList<ArrayList<FishTile>> boardCopy = fishModel.getBoardCopy();
    boolean isEmpty = boardCopy.get(0).get(0).isEmpty();
    assertEquals(true, isEmpty);
  }

  @Test
  public void emptyTileValid2(){
    fishModel.emptyTile(2,4);
    ArrayList<ArrayList<FishTile>> boardCopy = fishModel.getBoardCopy();
    boolean isEmpty = boardCopy.get(4).get(2).isEmpty();
    assertEquals(true, isEmpty);
  }

  @Test
  public void emptyTileInvalid1(){
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
    fishModel.emptyTile(-1,-1);
    // Put things back
    System.out.flush();
    System.setOut(old);

    assertEquals("Error: Invalid position of a tile.\n", baos.toString());
  }

  @Test
  public void emptyTileInvalid2(){
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
    fishModel.emptyTile(1000,1000);
    // Put things back
    System.out.flush();
    System.setOut(old);

    assertEquals("Error: Invalid position of a tile.\n", baos.toString());
  }

  @Test
  public void emptyTileInvalid3(){
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
    fishModel.emptyTile(-10,1);
    // Put things back
    System.out.flush();
    System.setOut(old);

    assertEquals("Error: Invalid position of a tile.\n", baos.toString());
  }

  @Test
  public void emptyTileInvalid4(){
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
    fishModel.emptyTile(1,-10);
    // Put things back
    System.out.flush();
    System.setOut(old);

    assertEquals("Error: Invalid position of a tile.\n", baos.toString());
  }

  @Test
  public void emptyTileInvalid5(){
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
    emptyFishModel.emptyTile(1,1);
    // Put things back
    System.out.flush();
    System.setOut(old);

    assertEquals("Error: Game board is empty.\n", baos.toString());
  }

  @Test
  public void moveUpValid1(){
    boolean ans = fishModel.isValidMove(1, 4, 1, 2);
    assertEquals(true, ans);
  }

  @Test
  public void moveUpValid2(){
    boolean ans = fishModel.isValidMove(1, 4, 1, 0);
    assertEquals(true, ans);
  }

  @Test
  public void moveUpValid3(){
    boolean ans = fishModel.isValidMove(1, 5, 1, 3);
    assertEquals(true, ans);
  }

  @Test
  public void moveUpValid4(){
    boolean ans = fishModel.isValidMove(1, 5, 1, 1);
    assertEquals(true, ans);
  }

  @Test
  public void moveDownValid1(){
    boolean ans = fishModel.isValidMove(1, 2, 1, 4);
    assertEquals(true, ans);
  }

  @Test
  public void moveDownValid2(){
    boolean ans = fishModel.isValidMove(1, 2, 1, 6);
    assertEquals(true, ans);
  }

  @Test
  public void moveDownValid3(){
    boolean ans = fishModel.isValidMove(1, 3, 1, 5);
    assertEquals(true, ans);
  }

  @Test
  public void moveDownValid4(){
    boolean ans = fishModel.isValidMove(1, 3, 1, 7);
    assertEquals(true, ans);
  }

  @Test
  public void moveToTopLeftValid1(){
    boolean ans = fishModel.isValidMove(1, 2, 0, 0);
    assertEquals(true, ans);
  }

  @Test
  public void moveToTopLeftValid2(){
    boolean ans = fishModel.isValidMove(1, 2, 0, 1);
    assertEquals(true, ans);
  }

  @Test
  public void moveToBottomLeftValid1(){
    boolean ans = fishModel.isValidMove(1, 2, 0, 4);
    assertEquals(true, ans);
  }

  @Test
  public void moveToBottomLeftValid2(){
    boolean ans = fishModel.isValidMove(1, 2, 0, 3);
    assertEquals(true, ans);
  }

  @Test
  public void moveToBottomRightValid1(){
    boolean ans = fishModel.isValidMove(1, 2, 2, 4);
    assertEquals(true, ans);
  }

  @Test
  public void moveToBottomRightValid2(){
    boolean ans = fishModel.isValidMove(1, 2, 1, 3);
    assertEquals(true, ans);
  }

  @Test
  public void moveToTopRightValid1(){
    boolean ans = fishModel.isValidMove(1, 2, 2, 0);
    assertEquals(true, ans);
  }

  @Test
  public void moveToTopRightValid2(){
    boolean ans = fishModel.isValidMove(1, 2, 1, 1);
    assertEquals(true, ans);
  }

  @Test
  public void moveLeftInvalid(){
    boolean ans = fishModel.isValidMove(1, 3, 0, 3);
    assertEquals(false, ans);
  }

  @Test
  public void moveRightInvalid(){
    boolean ans = fishModel.isValidMove(1, 3, 2, 3);
    assertEquals(false, ans);
  }

  @Test
  public void moveToTopLeftInvalid1(){
    boolean ans = fishModel.isValidMove(1, 7, 1, 2);
    assertEquals(false, ans);
  }

  @Test
  public void moveToTopLeftInvalid2(){
    boolean ans = fishModel.isValidMove(1, 4, 0, 1);
    assertEquals(false, ans);
  }

  @Test
  public void moveToBottomLeftInvalid1(){
    boolean ans = fishModel.isValidMove(1, 3, 0, 7);
    assertEquals(false, ans);
  }

  @Test
  public void moveToBottomLeftInvalid2(){
    boolean ans = fishModel.isValidMove(1, 3, 0, 4);
    assertEquals(false, ans);
  }

  @Test
  public void moveToBottomRightInvalid1(){
    boolean ans = fishModel.isValidMove(2, 0, 3, 1);
    assertEquals(false, ans);
  }

  @Test
  public void moveToBottomRightInvalid2(){
    boolean ans = fishModel.isValidMove(2, 1, 3, 6);
    assertEquals(false, ans);
  }

  @Test
  public void moveToTopRightInvalid1(){
    boolean ans = fishModel.isValidMove(2, 4, 3, 0);
    assertEquals(false, ans);
  }

  @Test
  public void moveToTopRightInvalid2(){
    boolean ans = fishModel.isValidMove(2, 4, 3, 3);
    assertEquals(false, ans);
  }

  @Test
  public void moveUpWithEmptyInvalid1(){
    fishModel.emptyTile(0, 4);
    boolean ans = fishModel.isValidMove(0, 6, 0, 2);
    assertEquals(false, ans);
  }

  @Test
  public void moveUpWithEmptyInvalid2(){
    fishModel.emptyTile(0, 4);
    boolean ans = fishModel.isValidMove(0, 6, 0, 4);
    assertEquals(false, ans);
  }

  @Test
  public void moveUpWithEmptyInvalid3(){
    fishModel.emptyTile(0, 6);
    boolean ans = fishModel.isValidMove(0, 6, 0, 2);
    assertEquals(false, ans);
  }

  @Test
  public void moveDownWithEmptyInvalid1(){
    fishModel.emptyTile(0, 2);
    boolean ans = fishModel.isValidMove(0, 0, 0, 4);
    assertEquals(false, ans);
  }

  @Test
  public void moveDownWithEmptyInvalid2(){
    fishModel.emptyTile(0, 2);
    boolean ans = fishModel.isValidMove(0, 0, 0, 2);
    assertEquals(false, ans);
  }

  @Test
  public void moveDownWithEmptyInvalid3(){
    fishModel.emptyTile(0, 0);
    boolean ans = fishModel.isValidMove(0, 0, 0, 4);
    assertEquals(false, ans);
  }

  @Test
  public void moveTopLeftWithEmptyInvalid1(){
    fishModel.emptyTile(0, 1);
    boolean ans = fishModel.isValidMove(1, 3, 0, 0);
    assertEquals(false, ans);
  }

  @Test
  public void moveTopLeftWithEmptyInvalid2(){
    fishModel.emptyTile(0, 1);
    boolean ans = fishModel.isValidMove(1, 3, 0, 1);
    assertEquals(false, ans);
  }

  @Test
  public void moveTopLeftWithEmptyInvalid3(){
    fishModel.emptyTile(1, 3);
    boolean ans = fishModel.isValidMove(1, 3, 0, 1);
    assertEquals(false, ans);
  }

  @Test
  public void moveBottomLeftWithEmptyInvalid1(){
    fishModel.emptyTile(0, 3);
    boolean ans = fishModel.isValidMove(1, 2, 0, 4);
    assertEquals(false, ans);
  }

  @Test
  public void moveBottomLeftWithEmptyInvalid2(){
    fishModel.emptyTile(0, 4);
    boolean ans = fishModel.isValidMove(1, 2, 0, 4);
    assertEquals(false, ans);
  }

  @Test
  public void moveBottomLeftWithEmptyInvalid3(){
    fishModel.emptyTile(1, 2);
    boolean ans = fishModel.isValidMove(1, 2, 0, 4);
    assertEquals(false, ans);
  }

  @Test
  public void moveTopRightWithEmptyInvalid1(){
    fishModel.emptyTile(2, 1);
    boolean ans = fishModel.isValidMove(2, 2, 3, 0);
    assertEquals(false, ans);
  }

  @Test
  public void moveTopRightWithEmptyInvalid2(){
    fishModel.emptyTile(2, 2);
    boolean ans = fishModel.isValidMove(2, 2, 3, 0);
    assertEquals(false, ans);
  }

  @Test
  public void moveTopRightWithEmptyInvalid3(){
    fishModel.emptyTile(3, 0);
    boolean ans = fishModel.isValidMove(2, 2, 3, 0);
    assertEquals(false, ans);
  }

  @Test
  public void moveBottomRightWithEmptyInvalid1(){
    fishModel.emptyTile(3, 4);
    boolean ans = fishModel.isValidMove(2, 3, 3, 5);
    assertEquals(false, ans);
  }

  @Test
  public void moveBottomRightWithEmptyInvalid2(){
    fishModel.emptyTile(2, 3);
    boolean ans = fishModel.isValidMove(2, 3, 3, 5);
    assertEquals(false, ans);
  }

  @Test
  public void moveBottomRightWithEmptyInvalid3(){
    fishModel.emptyTile(3, 5);
    boolean ans = fishModel.isValidMove(2, 3, 3, 5);
    assertEquals(false, ans);
  }

  @Test
  public void moveToSelfInvalid(){
    boolean ans = fishModel.isValidMove(2, 3, 2, 3);
    assertEquals(false, ans);
  }

  @Test
  public void emptyBoardMoves(){
    boolean ans = emptyFishModel.isValidMove(2, 3, 3, 5);
    assertEquals(false, ans);
  }

  @Test
  public void getEmptyBoardCopy(){
    ArrayList<ArrayList<FishTile>> emptyBoard = emptyFishModel.getBoardCopy();
    ArrayList<ArrayList<FishTile>> newBoard = new ArrayList<ArrayList<FishTile>>();
    assertEquals(newBoard, emptyBoard);
  }

  @Test
  public void getBoardCopy(){
    ArrayList<ArrayList<FishTile>> boardBefore = fishModel.getBoardCopy();
    boolean tileOneIsEmptyBefore = boardBefore.get(0).get(0).isEmpty();
    assertEquals(false, tileOneIsEmptyBefore);
    fishModel.emptyTile(0, 0);
    ArrayList<ArrayList<FishTile>> boardAfter = fishModel.getBoardCopy();
    boolean tileOneIsEmptyAfter = boardAfter.get(0).get(0).isEmpty();
    assertEquals(true, tileOneIsEmptyAfter);
  }



}
