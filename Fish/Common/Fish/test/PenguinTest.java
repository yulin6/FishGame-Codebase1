import static org.junit.Assert.*;

import models.Penguin;
import models.PenguinColor;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for testing Penguin class
 */
public class PenguinTest {

  Penguin penguin;
  PenguinColor red;
  PenguinColor white;
  PenguinColor brown;
  PenguinColor black;

  @Before
  public void init(){
    red = PenguinColor.RED;
    white = PenguinColor.WHITE;
    brown = PenguinColor.BROWN;
    black = PenguinColor.BLACK;
    penguin = new Penguin(1, red);
  }

  @Test
  public void getColor() {
    assertEquals(PenguinColor.RED, penguin.getColor());
  }

  @Test
  public void setColor() {
    assertEquals(PenguinColor.RED, penguin.getColor());
    penguin.setColor(PenguinColor.WHITE);
    assertEquals(PenguinColor.WHITE, penguin.getColor());
    penguin.setColor(PenguinColor.BROWN);
    assertEquals(PenguinColor.BROWN, penguin.getColor());
    penguin.setColor(PenguinColor.BLACK);
    assertEquals(PenguinColor.BLACK, penguin.getColor());
  }
}
