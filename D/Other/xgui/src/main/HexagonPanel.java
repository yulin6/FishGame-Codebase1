package main;

import static java.lang.System.exit;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/** A HexagonPanel that extends JPanel that contains a Polygon shape and an
 *  int that determines the size of the panel.
 **/
public class HexagonPanel extends JPanel {

  Polygon polygon = new Polygon();
  int size;

  //an mouseListener that will be added to the current HexagonPanel.
  MouseListener mouseListener = new MouseListener() {
    //If a mouse click event happened  inside of the hexagon,
    // terminate the program. Otherwise, print missed.
    @Override
    public void mouseClicked(MouseEvent e) {
      if (polygon.contains(e.getX(), e.getY())) {
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
  };

  /**
   * Constructor that constructs the HexagonPanel class by defining the size
   * and adding mouseListener.
   *
   * @param size the size of the JPanel.
   * */
  public HexagonPanel(int size) {
    this.size = size;
    addMouseListener(mouseListener);
  }

  /**
   * an overridden method that will be called automatically for
   * drawing the defined polygon to the panel.
   *
   * @param g a Graphics object
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    polygon.addPoint(0, size);
    polygon.addPoint(size, 2 * size);
    polygon.addPoint(2 * size, 2 * size);
    polygon.addPoint(3 * size, size);
    polygon.addPoint(2 * size, 0);
    polygon.addPoint(size, 0);

    g.drawPolygon(polygon);
  }
}
