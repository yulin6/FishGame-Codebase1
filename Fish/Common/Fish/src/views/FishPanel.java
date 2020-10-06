package views;

import static java.lang.System.exit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import models.FishTile;

public class FishPanel extends JPanel {

  ArrayList<ArrayList<FishTile>> board;
  Polygon polygon = new Polygon();

//  int size;

  public FishPanel(ArrayList<ArrayList<FishTile>> board) {
    this.board = board;
  }


  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (int i = 0; i < board.size(); ++i) {
      ArrayList<FishTile> row = board.get(i);
      for (int j = 0; j < row.size(); ++j) {
        FishTile fishTile = board.get(i).get(j);
        boolean isEmpty = fishTile.isEmpty();
        if (isEmpty) {
          g2d.setColor(Color.gray);

//          polygon.addPoint(0, size);
//          polygon.addPoint(size, 2 * size);
//          polygon.addPoint(2 * size, 2 * size);
//          polygon.addPoint(3 * size, size);
//          polygon.addPoint(2 * size, 0);
//          polygon.addPoint(size, 0);
        }

      }
    }

//    polygon.addPoint(0, size);
//    polygon.addPoint(size, 2 * size);
//    polygon.addPoint(2 * size, 2 * size);
//    polygon.addPoint(3 * size, size);
//    polygon.addPoint(2 * size, 0);
//    polygon.addPoint(size, 0);

    g.drawPolygon(polygon);
  }
}
