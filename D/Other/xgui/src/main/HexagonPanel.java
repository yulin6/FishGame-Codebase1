package main;

import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JPanel;

/** A HexagonPanel that extends JPanel that contains a Polygon shape and a
 * final int that determines the size of the panel.
 **/
public class HexagonPanel extends JPanel {
  private Polygon polygon = new Polygon();
  private final int size;

  /**
   * The hexagonpanel that is given an int to create a JPanel.
   *
   * @param size the size of the JPanel.
   * */
  public HexagonPanel(int size) {
    this.size = size;
  }

  /**
   * It draws the hexagon based on the given graphics.
   *
   * @param g the graphic used to design interface
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

  /** gets the polygon shape.
   * @return the polygon shape.
   **/
  public Polygon getPolygon(){
    return this.polygon;
  }

}
