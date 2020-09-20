package main;


import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class HexagonPanel extends JPanel {
  private Polygon polygon = new Polygon();
  private final int size;

  public HexagonPanel(int size) {
    this.size = size;
  }

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

  public Polygon getPolygon(){
    return this.polygon;
  }

}
