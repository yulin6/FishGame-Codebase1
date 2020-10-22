import static org.junit.Assert.*;

import models.Position;
import org.junit.Test;

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
