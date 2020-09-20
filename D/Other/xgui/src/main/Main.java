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

/** A main class for the xgui program.
 **/

public class Main {

  /**A main method that consumes a string with an integer value and creates
   * a JPanel with a hexagon shape inside.
   * @param args a string input from the linux command line.
   **/
  public static void main(String[] args) {

    if (args.length == 1) {
      if (isPosInt(args[0])) {
        int size = Integer.parseInt(args[0]);

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.white);
        frame.setTitle("XGUI");
        frame.setSize(size * 3, size * 2 + 23);
        frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            exit(0);
          }
        });

        JPanel hexagonPanel = new HexagonPanel(size);
        addMouseListenerToPanel(hexagonPanel);

        frame.add(hexagonPanel);
        frame.setVisible(true);
      } else {
        System.out.println("Invalid input: " + args[0]
            + ". Please input a positive integer instead.");
      }
    } else {
      System.out.println("Invalid number of input arguments. "
          + "Please input one positive integer instead.");
    }
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

  /** addMouseListenerToPanel determines whether there is a mouseClicked event within the
   * JPanel.
   * @param hexagonPanel a panel generated from main.
   **/

  public static void addMouseListenerToPanel(JPanel hexagonPanel) {
    final Polygon hexagon = ((HexagonPanel) hexagonPanel).getPolygon();
    hexagonPanel.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (hexagon.contains(e.getX(), e.getY())) {
          exit(0);
        } else {
          System.out.println("Missed");
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }

    });
  }
}
