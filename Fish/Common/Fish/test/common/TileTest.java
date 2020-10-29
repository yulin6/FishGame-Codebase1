package common;

import static org.junit.Assert.*;

import common.models.Tile;
import org.junit.Before;
import org.junit.Test;

/**
 * a class for testing the Tile.
 */
public class TileTest {

  private Tile tile;

  @Before
  public void init() {
    tile = new Tile(4);
  }

  @Test
  public void isEmptyTrue() {
    tile.setToHole();
    boolean isEmpty = tile.isHole();
    assertEquals(true, isEmpty);
  }

  @Test
  public void isEmptyFalse() {
    boolean isEmpty = tile.isHole();
    assertEquals(false, isEmpty);
  }

  @Test
  public void setEmpty() {
    tile.setToHole();
    boolean isEmpty = tile.isHole();
    assertEquals(true, isEmpty);
  }

  @Test
  public void getFishNum() {
    int fishNum = tile.getFishNum();
    assertEquals(4, fishNum);
  }
}
