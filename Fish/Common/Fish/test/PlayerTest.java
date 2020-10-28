import common.models.PenguinColor;
import common.models.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

  Player player;
  PenguinColor penguinColor;
  int age;

  @Before
  public void init(){
    age = 12;
    penguinColor = PenguinColor.red;
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
    assertEquals(PenguinColor.red, player.getPenguinColor());
  }

  @Test
  public void setPenguinColor() {
    player.setPenguinColor(PenguinColor.brown);
    assertEquals(PenguinColor.brown, player.getPenguinColor());
  }
}
