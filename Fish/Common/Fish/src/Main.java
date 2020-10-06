import java.util.ArrayList;
import java.util.Scanner;
import models.FishModel;

public class Main {

  private static FishModel fishModel;

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    createFishModel(scanner);
    emptyTilesFromFishModel(scanner);

  }

  /**
   *
   * @param scanner
   */
  public static void createFishModel(Scanner scanner){
    String boardCreateInstruction = "To create a initial board, please enter four arguments: \n"
        + "1. Width - Positive Integer, \n"
        + "2. Height - Positive Integer, \n"
        + "3. Maximum number of fish on a tile - Positive Integer, \n"
        + "4. Is number of fishes randomly distributed - \"random\" or \"nonrandom\". \n"
        + "Input your arguments below: ";
    System.out.println(boardCreateInstruction);

    ArrayList<String> argsList = new ArrayList<String>();
    //initial board creation
    while (scanner.hasNext()){
      argsList.add(scanner.next());
      if(argsList.size() == 4){

        String widthStr = argsList.get(0);
        String heightStr = argsList.get(1);
        String maxFishNumStr = argsList.get(2);
        String isRandomizedStr = argsList.get(3);

        if(isPosInt(widthStr) && isPosInt(heightStr) && isPosInt(maxFishNumStr)){
          int width = Integer.parseInt(widthStr);
          int height = Integer.parseInt(heightStr);
          int maxFishNum = Integer.parseInt(maxFishNumStr);
          boolean isRandomized;

          String random = "random";
          String nonRandom = "nonrandom";
          if(isRandomizedStr.equals(random)) {
            isRandomized = true;
          } else if (isRandomizedStr.equals(nonRandom)){
            isRandomized = false;
          } else {
            System.out.println("Error: Forth argument should be random or nonrandom.");
            argsList = new ArrayList<String>();
            continue;
          }
          fishModel = new FishModel(width, height, maxFishNum, isRandomized);
//          System.out.println(fishBoard.getBoard().get(0).get(1).getFishNum());
          break;
        } else {
          argsList = new ArrayList<String>();
          System.out.println("Error: First three arguments should be positive integers.");

        }
      }
    }
  }

  /**
   *
   * @param scanner
   */
  public static void emptyTilesFromFishModel(Scanner scanner){
    System.out.println("To empty a tile, "
        + "enter x and y positions (Positive Integers) of the tile; To run the GUI, enter \"-run\":");
    ArrayList<String> argsList = new ArrayList<String>();
    boolean isScanning = true;

    while (scanner.hasNext()){
      argsList.add(scanner.next());

      for(String arg: argsList){
        if(arg.equals("-run")){
          isScanning = false;
          System.out.println("running");
          break;
        }
      }

      if(!isScanning) {
        break;
      } else if(argsList.size() == 2){
        String xPosStr = argsList.get(0);
        String yPosStr = argsList.get(1);

        if(isPosInt(xPosStr) && isPosInt(yPosStr)){
          int xPos = Integer.parseInt(xPosStr) - 1;
          int yPos = Integer.parseInt(yPosStr) - 1;
          argsList = new ArrayList<String>();
          fishModel.emptyTile(xPos, yPos);

        } else {
          argsList = new ArrayList<String>();
          System.out.println("Error: x and y positions should be two positive integers.");
        }
      }
    }
  }

  /** isPosInt determines whether a string of input is an integer that is larger than 0.
   *
   * @param s a string input from the main class or from the user.
   * @return a boolean value that determine if the string is a valid argument for main.
   **/
  public static boolean isPosInt(String s) {
    int i;
    try {
      i = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return i > 0;
  }
}
