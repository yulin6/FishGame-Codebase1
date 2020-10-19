import com.esotericsoftware.kryo.Kryo;
import controllers.FishController;
import java.util.ArrayList;
import java.util.Scanner;
import models.FishModel;
import models.FishState;
import models.FishTree;
import models.Penguin;
import models.PenguinColor;
import models.Player;
import models.Tile;
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

//    FishModel model = new FishModel(6, 6, 4, false);
//    Player playerRed = new Player(2, PenguinColor.RED);
//    ArrayList<Player> players = new ArrayList<Player>();
//    players.add(playerRed);
//    FishState state = new FishState(model, players);
//    state.placeInitPenguin(0,0,playerRed);
//    System.out.println(state.getPenguinsOnBoard().get(0));
//
//    FishTree tree = new FishTree(state);
//    int before = tree.getFishState().getPenguinsOnBoard().get(0).getYPos();
//    int after = tree.getStates().get(0).getFishState().getPenguinsOnBoard().get(0).getYPos();
//
//    System.out.println(before);
//    System.out.println(after);
//    Kryo kryo = new Kryo();
//    kryo.setRegistrationRequired(false);
//    FishModel modelCopy = kryo.copy(model);
//    boolean width = model.getBoard().get(0).get(0).isEmpty();
//    modelCopy.emptyTile(0, 0);
//    boolean widthCopy = modelCopy.getBoard().get(0).get(0).isEmpty();
//    System.out.println( width);
//    System.out.println( widthCopy);

    Scanner scanner = new Scanner(System.in);
    createFishModel(scanner);
    emptyTilesAndSetUpGui(scanner);

  }

  /**
   * Accept four inputs from user: width, height, MinOneFishNumOrFishNumOnTiles, isRandomized boolean,
   * and use them to generate a model if they are valid inputs.
   *
   * @param scanner a scanner.
   */
  public static void createFishModel(Scanner scanner) {
    String boardCreateInstruction = "To create a initial board, please enter four arguments: \n"
        + "1. Width - Positive integer. \n"
        + "2. Height - Positive integer larger than 1. \n"
        + "3. MinOneFishNumOrFishNumOnTiles (stands different thing depends on the fourth argument): \n"
        + "   Minimum number of one-fish tiles - Positive integer range from 0 to Width * Height. (Random Mode);\n"
        + "   Number of fishes on each tiles - Positive integer range from 1 to 5. (Nonrandom Mode).\n"
        + "4. RandomModeOrNonRandom: \n"
        + "   Random mode will have a minimum number of one-fish tiles, fish number on other tiles are randomly distributed from 2 to 5 - \"random\"; \n"
        + "   Nonrandom mode will have each tiles containing the same fish number. - \"nonrandom\". \n"
        + "Input your arguments below: ";
    System.out.println(boardCreateInstruction);

    ArrayList<String> argsList = new ArrayList<String>();
    //initial board creation
    while (scanner.hasNext()) {
      argsList.add(scanner.next());
      if (argsList.size() == 4) {

        String widthStr = argsList.get(0);
        String heightStr = argsList.get(1);
        String minOneFishNumOrFishNumOnTilesStr = argsList.get(2);
        String isRandomizedStr = argsList.get(3);

        if (isNaturalNum(widthStr) && isNaturalNum(heightStr) && isNaturalNum(minOneFishNumOrFishNumOnTilesStr)) {
          int width = Integer.parseInt(widthStr);
          int height = Integer.parseInt(heightStr);
          int minOneFishNumOrFishNumOnTiles = Integer.parseInt(minOneFishNumOrFishNumOnTilesStr);
          boolean isRandomized;
          int tilesNumOnBoard = width * height;

          String random = "random";
          String nonRandom = "nonrandom";
          if (isRandomizedStr.equals(random)) {
            isRandomized = true;
            if(minOneFishNumOrFishNumOnTiles < 0 || minOneFishNumOrFishNumOnTiles > tilesNumOnBoard){
              System.out.println("Error: Third argument is invalid. In random mode, "
                  + "the minimum number of one fish tiles should be greater than zero "
                  + "or less than or equal to the number of tiles on the board.");
              argsList = new ArrayList<String>();
              continue;
            }
          } else if (isRandomizedStr.equals(nonRandom)) {
            isRandomized = false;
            if(minOneFishNumOrFishNumOnTiles < 1 || minOneFishNumOrFishNumOnTiles > 5){
              System.out.println("Error: Third argument is invalid. In nonrandom mode, "
                  + "maximum fish number should be larger than 0 or less than 6");
              argsList = new ArrayList<String>();
              continue;
            }
          } else {
            System.out.println("Error: Forth argument should be random or nonrandom.");
            argsList = new ArrayList<String>();
            continue;
          }

          if (width < 1 || height < 2) {
            System.out.println("Error: One or both of the first two arguments are invalid.");
            argsList = new ArrayList<String>();
            continue;
          }

          fishModel = new FishModel(width, height, minOneFishNumOrFishNumOnTiles, isRandomized);
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

//          Penguin penguin = new Penguin(PenguinColor.BROWN);
//          Tile tile = fishModel.getBoard().get(yPos).get(xPos);
//          tile.setPenguin(penguin);
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
