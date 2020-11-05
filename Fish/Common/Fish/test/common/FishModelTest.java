package common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import common.models.FishModel;
import common.models.Tile;
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
  public void emptyTileValid1() {
    fishModel.createHole(0, 0);
    ArrayList<ArrayList<Tile>> boardCopy = fishModel.getBoard();
    boolean isEmpty = boardCopy.get(0).get(0).isHole();
    assertEquals(true, isEmpty);
  }

  @Test
  public void emptyTileValid2() {
    fishModel.createHole(2, 4);
    ArrayList<ArrayList<Tile>> boardCopy = fishModel.getBoard();
    boolean isEmpty = boardCopy.get(4).get(2).isHole();
    assertEquals(true, isEmpty);
  }

//  @Test
//  public void emptyTileInvalid1() {
//    /** Code Source of converting system.out.println to String:
//     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
//     */
//    // Create a stream to hold the output
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    PrintStream ps = new PrintStream(baos);
//    // IMPORTANT: Save the old System.out!
//    PrintStream old = System.out;
//    // Tell Java to use your special stream
//    System.setOut(ps);
//    // Print
//    fishModel.emptyTile(-1, -1);
//    // Put things back
//    System.out.flush();
//    System.setOut(old);
//
//    assertEquals("Error: Invalid position of a tile.\n", baos.toString());
//  }
//
//  @Test
//  public void emptyTileInvalid2() {
//    /** Code Source of converting system.out.println to String:
//     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
//     */
//    // Create a stream to hold the output
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    PrintStream ps = new PrintStream(baos);
//    // IMPORTANT: Save the old System.out!
//    PrintStream old = System.out;
//    // Tell Java to use your special stream
//    System.setOut(ps);
//    // Print
//    fishModel.emptyTile(1000, 1000);
//    // Put things back
//    System.out.flush();
//    System.setOut(old);
//
//    assertEquals("Error: Invalid position of a tile.\n", baos.toString());
//  }
//
//  @Test
//  public void emptyTileInvalid3() {
//    /** Code Source of converting system.out.println to String:
//     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
//     */
//    // Create a stream to hold the output
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    PrintStream ps = new PrintStream(baos);
//    // IMPORTANT: Save the old System.out!
//    PrintStream old = System.out;
//    // Tell Java to use your special stream
//    System.setOut(ps);
//    // Print
//    fishModel.emptyTile(-10, 1);
//    // Put things back
//    System.out.flush();
//    System.setOut(old);
//
//    assertEquals("Error: Invalid position of a tile.\n", baos.toString());
//  }
//
//  @Test
//  public void emptyTileInvalid4() {
//    /** Code Source of converting system.out.println to String:
//     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
//     */
//    // Create a stream to hold the output
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    PrintStream ps = new PrintStream(baos);
//    // IMPORTANT: Save the old System.out!
//    PrintStream old = System.out;
//    // Tell Java to use your special stream
//    System.setOut(ps);
//    // Print
//    fishModel.emptyTile(1, -10);
//    // Put things back
//    System.out.flush();
//    System.setOut(old);
//
//    assertEquals("Error: Invalid position of a tile.\n", baos.toString());
//  }
//
//  @Test
//  public void emptyTileInvalid5() {
//    /** Code Source of converting system.out.println to String:
//     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
//     */
//    // Create a stream to hold the output
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    PrintStream ps = new PrintStream(baos);
//    // IMPORTANT: Save the old System.out!
//    PrintStream old = System.out;
//    // Tell Java to use your special stream
//    System.setOut(ps);
//    // Print
//    emptyFishModel.emptyTile(1, 1);
//    // Put things back
//    System.out.flush();
//    System.setOut(old);
//
//    assertEquals("Error: Game board is empty.\n", baos.toString());
//  }

  @Test
  public void possibleMoves() {
//    fishModel.emptyTile(1, 5);
//    fishModel.emptyTile(1, 4);
//    fishModel.emptyTile(1, 2);
//    fishModel.emptyTile(2, 2);
//    fishModel.emptyTile(1, 1);
//    fishModel.emptyTile(3, 6);
    ArrayList<Tile> possibleMoves = fishModel.getPossibleMoves(1, 3);

    assertEquals(16, possibleMoves.size());
  }

  @Test
  public void getEmptyBoardCopy() {
    ArrayList<ArrayList<Tile>> emptyBoard = emptyFishModel.getBoard();
//    ArrayList<ArrayList<Tile>> newBoard = new ArrayList<ArrayList<Tile>>();
    assertEquals(null, emptyBoard);
  }

  @Test
  public void getBoardCopy() {
    ArrayList<ArrayList<Tile>> boardBefore = fishModel.getBoard();
    boolean tileOneIsEmptyBefore = boardBefore.get(0).get(0).isHole();
    assertEquals(false, tileOneIsEmptyBefore);
    fishModel.createHole(0, 0);
    ArrayList<ArrayList<Tile>> boardAfter = fishModel.getBoard();
    boolean tileOneIsEmptyAfter = boardAfter.get(0).get(0).isHole();
    assertEquals(true, tileOneIsEmptyAfter);
  }


}
