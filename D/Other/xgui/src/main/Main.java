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

public class Main {


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

  private static boolean isPosInt(String s) {
    int i;
    try {
      i = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return i > 0;
  }

  public static void addMouseListenerToPanel(JPanel hexagonPanel) {
    final Polygon hexagon = ((HexagonPanel) hexagonPanel).getPolygon();
    hexagonPanel.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
      }

      @Override
      public void mousePressed(MouseEvent e) {
        if (hexagon.contains(e.getX(), e.getY())) {
          exit(0);
        }
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
