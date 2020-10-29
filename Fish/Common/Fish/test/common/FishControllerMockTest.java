package common;

import common.models.FishModel;
import org.junit.Before;
import common.views.FishView;

/**
 * class for testing the FishControllerMock.
 */
public class FishControllerMockTest {

  private FishModel fishModel;
  private FishView fishView;
  private FishControllerMock fishControllerMock;
  private int width1;
  private int height1;
  private int maxFishNum1;
  private boolean isRandom;

  @Before
  public void init() {
    width1 = 4;
    height1 = 8;
    maxFishNum1 = 5;
    isRandom = true;
    fishModel = new FishModel(width1, height1, maxFishNum1, isRandom);
    fishView = new FishView();
    fishControllerMock = new FishControllerMock(fishModel, fishView);
  }

//  @Test
//  public void generateView() {
//    /** Code Source of converting system.out.println to String:
//     *  https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
//     */
//    // Create a stream to hold the output
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    PrintStream ps = new PrintStream(baos);
//    // IMPORTANT: Save the old System.out!
//    PrintStream old = System.out;
//    // Tell Java to use your special stream
//    System.setOut(ps);
//    // Print
//    fishControllerMock.generateView();
//    // Put things back
//    System.out.flush();
//    System.setOut(old);
//
//    assertEquals("View generated.\n", baos.toString());
//  }
}
