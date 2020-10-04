package views;

import static java.lang.System.exit;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class FishPanel extends JPanel {

  Polygon polygon = new Polygon();

  int size;

  public FishPanel(int size) {
    this.size = size;
  }


  public void paintComponent(Graphics g) {
    super.paintComponent(g);


//    polygon.addPoint(0, size);
//    polygon.addPoint(size, 2 * size);
//    polygon.addPoint(2 * size, 2 * size);
//    polygon.addPoint(3 * size, size);
//    polygon.addPoint(2 * size, 0);
//    polygon.addPoint(size, 0);

    g.drawPolygon(polygon);
  }
}
