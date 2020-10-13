import static org.junit.Assert.*;

import models.Tile;
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
    tile.setEmpty();
    boolean isEmpty = tile.isEmpty();
    assertEquals(true, isEmpty);
  }

  @Test
  public void isEmptyFalse() {
    boolean isEmpty = tile.isEmpty();
    assertEquals(false, isEmpty);
  }

  @Test
  public void setEmpty() {
    tile.setEmpty();
    boolean isEmpty = tile.isEmpty();
    assertEquals(true, isEmpty);
  }

  @Test
  public void getFishNum() {
    int fishNum = tile.getFishNum();
    assertEquals(4, fishNum);
  }
}
