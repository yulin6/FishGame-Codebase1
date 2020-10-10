import controllers.FishController;
import java.util.ArrayList;
import java.util.Scanner;
import models.FishModel;
import views.FishView;

/**
 * The main class of the fish game.
 */
public class Main {

  private static FishModel fishModel;

  /**
   * Entry point of the program.
   *
   * @param args string of input arguments.
   */
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    createFishModel(scanner);
    emptyTilesAndSetUpGui(scanner);

  }

  /**
   * Accept four inputs from user: width, height, maxFishNum, isRandomized boolean, and use them to
   * generate a model if they are valid inputs.
   *
   * @param scanner a scanner.
   */
  public static void createFishModel(Scanner scanner) {
    String boardCreateInstruction = "To create a initial board, please enter four arguments: \n"
        + "1. Width - Positive integer, \n"
        + "2. Height - Positive integer larger than 1, \n"
        + "3. Maximum number of fish on a tile - Positive integer range from 1 to 5, \n"
        + "4. Is number of fishes randomly distributed - \"random\" or \"nonrandom\". \n"
        + "Input your arguments below: ";
    System.out.println(boardCreateInstruction);

    ArrayList<String> argsList = new ArrayList<String>();
    //initial board creation
    while (scanner.hasNext()) {
      argsList.add(scanner.next());
      if (argsList.size() == 4) {

        String widthStr = argsList.get(0);
        String heightStr = argsList.get(1);
        String maxFishNumStr = argsList.get(2);
        String isRandomizedStr = argsList.get(3);

        if (isNaturalNum(widthStr) && isNaturalNum(heightStr) && isNaturalNum(maxFishNumStr)) {
          int width = Integer.parseInt(widthStr);
          int height = Integer.parseInt(heightStr);
          int maxFishNum = Integer.parseInt(maxFishNumStr);
          boolean isRandomized;

//          if (width < 1 || height < 2 || maxFishNum < 1 || maxFishNum > 5) {
//            System.out.println("Error: One or more of the first three arguments are invalid.");
//            argsList = new ArrayList<String>();
//            continue;
//          }

          String random = "random";
          String nonRandom = "nonrandom";
          if (isRandomizedStr.equals(random)) {
            isRandomized = true;
          } else if (isRandomizedStr.equals(nonRandom)) {
            isRandomized = false;
          } else {
            System.out.println("Error: Forth argument should be random or nonrandom.");
            argsList = new ArrayList<String>();
            continue;
          }
          fishModel = new FishModel(width, height, maxFishNum, isRandomized);
          break;
        } else {
          argsList = new ArrayList<String>();
          System.out.println("Error: First three arguments should be natural numbers.");

        }
      }
    }
  }

  /**
   * Accepting pair of inputs from users for emptying the tiles. If the user is done emptying the
   * tiles, they can type int -run to pop up the GUI.
   *
   * @param scanner a scanner.
   */
  public static void emptyTilesAndSetUpGui(Scanner scanner) {
    System.out.println("To empty a tile, "
        + "enter x and y positions (Natural Numbers) of the tile; To run the GUI, enter \"-run\":");
    ArrayList<String> argsList = new ArrayList<String>();
    boolean isGuiReady = false;

    while (scanner.hasNext()) {
      argsList.add(scanner.next());

      for (String arg : argsList) {
        if (arg.equals("-run")) {
          isGuiReady = true;
          generateView();
          break;
        }
      }

      if (isGuiReady) {
        break;
      } else if (argsList.size() == 2) {
        String xPosStr = argsList.get(0);
        String yPosStr = argsList.get(1);

        if (isNaturalNum(xPosStr) && isNaturalNum(yPosStr)) {
          int xPos = Integer.parseInt(xPosStr);
          int yPos = Integer.parseInt(yPosStr);
          argsList = new ArrayList<String>();
          fishModel.emptyTile(xPos, yPos);

        } else {
          argsList = new ArrayList<String>();
          System.out.println("Error: x and y positions should be two natural numbers.");
        }
      }
    }
  }

  /**
   * Creates a view and uses the view and existing model to create a controller, and the controller
   * will call its generateView() for generating the GUI.
   */
  public static void generateView() {
    FishView fishView = new FishView();
    FishController fishController = new FishController(fishModel, fishView);
    fishController.generateView();
    System.out.println("GUI is running.");
  }

  /**
   * isPosInt determines whether a string of input is an integer that is larger than 0.
   *
   * @param s a string input from the main class or from the user.
   * @return a boolean value that determine if the string is a valid argument for main.
   **/
  public static boolean isNaturalNum(String s) {
    int i;
    try {
      i = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return i >= 0;
  }
}
