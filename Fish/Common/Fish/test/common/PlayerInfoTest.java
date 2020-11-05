package common;

import common.models.PenguinColor;
import common.models.PlayerInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerInfoTest {

  PlayerInfo playerInfo;
  PenguinColor penguinColor;
  int age;

  @Before
  public void init(){
    age = 12;
    penguinColor = PenguinColor.red;
    playerInfo = new PlayerInfo(age, penguinColor);
  }

  @Test
  public void getAge() {
    assertEquals(12, playerInfo.getAge());
  }

  @Test
  public void setAge() {
    playerInfo.setAge(44);
    assertEquals(44, playerInfo.getAge());
  }

  @Test
  public void getTotalFish() {
    assertEquals(0, playerInfo.getTotalFish());
  }

  @Test
  public void addTotalFish() {
    playerInfo.addTotalFish(5);
    assertEquals(5, playerInfo.getTotalFish());
  }

  @Test
  public void getPenguinColor() {
    assertEquals(PenguinColor.red, playerInfo.getPenguinColor());
  }

  @Test
  public void setPenguinColor() {
    playerInfo.setPenguinColor(PenguinColor.brown);
    assertEquals(PenguinColor.brown, playerInfo.getPenguinColor());
  }
}
