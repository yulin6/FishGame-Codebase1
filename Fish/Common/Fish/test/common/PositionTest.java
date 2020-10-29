package common;

import common.models.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * a test class for Position class
 */
public class PositionTest {

  @Test
  public void getXPos(){
    Position position = new Position(233, 2);
    assertEquals(233, position.getX());
  }

  @Test
  public void getYPos(){
    Position position = new Position(2, 233);
    assertEquals(233, position.getY());
  }
}
