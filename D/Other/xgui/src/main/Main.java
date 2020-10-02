package main;

import static java.lang.System.*;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/** A main class for the xgui program.
 **/

public class Main {

  /**A main method that consumes a string with an integer value and creates
   * a JPanel with a hexagon shape inside. If a mouse click event happened
   * inside of the hexagon, terminate the program. Otherwise, print missed.
   *
   * @param args a string input from the linux command line.
   **/
  public static void main(String[] args) {
    if (args.length == 1) {
      if (isPosInt(args[0])) {
        frameCreation(args[0]);
      } else {
        System.out.println("Invalid input: " + args[0]
            + ". Please input a positive integer instead.");
      }
    } else {
      System.out.println("Invalid number of input arguments. "
          + "Please input one positive integer instead.");
    }
  }

  /**
   * creating a JFrame based on the inputting arg, the String arg is checked to be valid for
   * converting into a positive integer. Also, adding the hexagonPanel to the frame.
   *
   * @param arg String arg is checked to be valid for converting into a positive integer.
   */
  public static void frameCreation(String arg){
    int hexagonSize = Integer.parseInt(arg);
    String windowTitle = "XGUI";
    int windowWidth = hexagonSize * 3;
    int windowHeight = hexagonSize * 2 + 23;

    JFrame frame = new JFrame();
    frame.setTitle(windowTitle);
    frame.setSize(windowWidth, windowHeight);

    //Sets the operation that will happen by default when the user initiates a "close" on this frame.
    //EXIT_ON_CLOSE (defined in JFrame): Exit the application using the System exit method.
    //In short, kill the program when the window is closed.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel hexagonPanel = new HexagonPanel(hexagonSize);

    frame.add(hexagonPanel);
    frame.setVisible(true);
  }

  /** isPosInt determines whether a string of input is an integer that is more than 0.
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
