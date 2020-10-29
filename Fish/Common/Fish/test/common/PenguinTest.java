package common;

import common.models.Penguin;
import common.models.PenguinColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    penguin = new Penguin(red);
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

  @Test
  public void setPos(){
    penguin.setXPos(0);
    penguin.setYPos(0);
    assertEquals(0, penguin.getXPos());
    assertEquals(0, penguin.getYPos());

    penguin.setXPos(2);
    penguin.setYPos(3);
    assertEquals(2, penguin.getXPos());
    assertEquals(3, penguin.getYPos());
  }
}
