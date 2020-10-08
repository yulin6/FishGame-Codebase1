package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * a class for testing the FishTile.
 */
public class FishTileTest {
  private FishTile fishTile;

  @Before
  public void init() {
    fishTile = new FishTile(4);
  }

  @Test
  public void isEmptyTrue() {
    fishTile.setEmpty();
    boolean isEmpty = fishTile.isEmpty();
    assertEquals(true, isEmpty);
  }

  @Test
  public void isEmptyFalse() {
    boolean isEmpty = fishTile.isEmpty();
    assertEquals(false, isEmpty);
  }

  @Test
  public void setEmpty() {
    fishTile.setEmpty();
    boolean isEmpty = fishTile.isEmpty();
    assertEquals(true, isEmpty);
  }

  @Test
  public void getFishNum() {
    int fishNum = fishTile.getFishNum();
    assertEquals(4, fishNum);
  }
}
