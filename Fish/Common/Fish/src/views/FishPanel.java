package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JPanel;
import models.FishTile;

public class FishPanel extends JPanel {

  private ArrayList<ArrayList<FishTile>> board;
//  private ArrayList<Polygon> polygons = new ArrayList<Polygon>();
  private int maxFishNum;


  int size = 50;

  public FishPanel() {
    super();
  }


  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int counter = 0;

    for (int i = 0; i < board.size(); ++i) {
      ArrayList<FishTile> row = board.get(i);
      for (int j = 0; j < row.size(); ++j) {
        Polygon polygon = new Polygon();
        FishTile fishTile = board.get(i).get(j);
        boolean isEmpty = fishTile.isEmpty();

        if (isEmpty) {
          g2d.setColor(Color.gray);
        } else {
          g2d.setColor(Color.orange);
        }

        int mostLeftX;
        int mostLeftY;
        if (i % 2 == 0) {
          mostLeftX = 4 * j * size;
          mostLeftY = i * size + size;
        } else {
          mostLeftX = 4 * j * size + 2 * size;
          mostLeftY = i * size + size;
        }
        polygon.addPoint(mostLeftX, mostLeftY);
        polygon.addPoint(mostLeftX + size, mostLeftY + size);
        polygon.addPoint(mostLeftX + size * 2, mostLeftY + size);
        polygon.addPoint(mostLeftX + size * 3, mostLeftY);
        polygon.addPoint(mostLeftX + size * 2, mostLeftY - size);
        polygon.addPoint(mostLeftX + size, mostLeftY - size);

        g2d.fillPolygon(polygon);
        g2d.setColor(Color.white);
        g2d.drawPolygon(polygon);
//        polygons.add(polygon);

      }
    }


  }

  public void addDataToPanel(ArrayList<ArrayList<FishTile>> board, int maxFishNum) {
    this.board = board;
    this.maxFishNum = maxFishNum;
  }
}
