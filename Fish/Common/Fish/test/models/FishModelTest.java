package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FishModelTest {

  private FishModel fishModel;
  private int width1;
  private int height1;
  private int maxFishNum1;
  private boolean isRandom;

  @Before
  public void init() {
    width1 = 10;
    height1 = 10;
    maxFishNum1 = 5;
    isRandom = true;
    fishModel = new FishModel(width1, height1, maxFishNum1, isRandom);
  }

  @Test
  public void moveFromTopLeftValid(){
    boolean ans = fishModel.isPossibleMove(1, 3, 2, 4);
    assertEquals(true, ans);
  }

}
