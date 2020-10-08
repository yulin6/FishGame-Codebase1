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
    red = PenguinColor.red;
    white = PenguinColor.white;
    brown = PenguinColor.brown;
    black = PenguinColor.black;
    penguin = new Penguin(1, red);
  }

  @Test
  public void getColor() {
    assertEquals(PenguinColor.red, penguin.getColor());
  }

  @Test
  public void setColor() {
    assertEquals(PenguinColor.red, penguin.getColor());
    penguin.setColor(PenguinColor.white);
    assertEquals(PenguinColor.white, penguin.getColor());
    penguin.setColor(PenguinColor.brown);
    assertEquals(PenguinColor.brown, penguin.getColor());
    penguin.setColor(PenguinColor.black);
    assertEquals(PenguinColor.black, penguin.getColor());
  }
}
