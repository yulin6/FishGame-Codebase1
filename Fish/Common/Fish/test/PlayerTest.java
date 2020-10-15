import static org.junit.Assert.*;

import models.Penguin;
import models.PenguinColor;
import models.Player;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

  Player player;
  PenguinColor penguinColor;
  int age;

  @Before
  public void init(){
    age = 12;
    penguinColor = PenguinColor.RED;
    player = new Player(age, penguinColor);
  }

  @Test
  public void getAge() {
    assertEquals(12, player.getAge());
  }

  @Test
  public void setAge() {
    player.setAge(44);
    assertEquals(44, player.getAge());
  }

  @Test
  public void getTotalFish() {
    assertEquals(0, player.getTotalFish());
  }

  @Test
  public void addTotalFish() {
    player.addTotalFish(5);
    assertEquals(5, player.getTotalFish());
  }

  @Test
  public void getPenguinColor() {
    assertEquals(PenguinColor.RED, player.getPenguinColor());
  }

  @Test
  public void setPenguinColor() {
    player.setPenguinColor(PenguinColor.BROWN);
    assertEquals(PenguinColor.BROWN, player.getPenguinColor());
  }
}