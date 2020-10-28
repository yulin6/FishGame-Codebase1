package common.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import common.models.Tile;
import common.models.Penguin;
import common.models.PenguinColor;

/**
 * the panel class that will be used for displaying the view of all tiles from the game.
 */
public class TilesPanel extends JPanel {

  private ArrayList<ArrayList<Tile>> board;
  //  private ArrayList<Polygon> polygons = new ArrayList<Polygon>();
  private Image fishImage;
  int tileSize = 60;

  /**
   * Constructor of the TilePanel, which set up the fish image to a proper size.
   */
  public TilesPanel() {
    super();
    int scaledWidth = 40;
    int scaledHeight = 80;
    try {
      //SCALE_FAST:
      // Choose an image-scaling algorithm that gives higher priority to scaling speed than smoothness of the scaled image.
      fishImage = ImageIO.read(new File("fish.png"))
          .getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_FAST);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (int i = 0; i < board.size(); ++i) {
      ArrayList<Tile> row = board.get(i);
      for (int j = 0; j < row.size(); ++j) {

        Polygon polygon = new Polygon();
        Tile tile = board.get(i).get(j);
        boolean isEmpty = tile.isHole();
        int fishNum = tile.getFishNum();
        Penguin penguin = tile.getPenguin();

        if (isEmpty) {
          g2d.setColor(Color.gray);
        } else {
          g2d.setColor(Color.orange);
        }

        int mostLeftX;
        int mostLeftY;
        if (i % 2 == 0) {
          mostLeftX = 4 * j * tileSize;
          mostLeftY = i * tileSize + tileSize;
        } else {
          mostLeftX = 4 * j * tileSize + 2 * tileSize;
          mostLeftY = i * tileSize + tileSize;
        }

        polygon.addPoint(mostLeftX, mostLeftY);
        polygon.addPoint(mostLeftX + tileSize, mostLeftY + tileSize);
        polygon.addPoint(mostLeftX + tileSize * 2, mostLeftY + tileSize);
        polygon.addPoint(mostLeftX + tileSize * 3, mostLeftY);
        polygon.addPoint(mostLeftX + tileSize * 2, mostLeftY - tileSize);
        polygon.addPoint(mostLeftX + tileSize, mostLeftY - tileSize);

        g2d.fillPolygon(polygon);
        g2d.setColor(Color.white);
        g2d.drawPolygon(polygon);
        if (!isEmpty) {
          if (penguin == null) {
          for (int k = 0; k < fishNum; ++k) {

              int xPos = mostLeftX + k * 18 + 35;
              int yPos = mostLeftY - 35;
              //ImageObserver:
              //An asynchronous update interface for receiving notifications about Image information
              // as the Image is constructed.
              g2d.drawImage(fishImage, xPos, yPos, null);
            }
          } else {
            PenguinColor penguinColor = penguin.getColor();
            PenguinColor red = PenguinColor.red;
            PenguinColor white = PenguinColor.white;
            PenguinColor brown = PenguinColor.brown;
            PenguinColor black = PenguinColor.black;

            if(penguinColor.equals(red)) g2d.setColor(Color.red);
            else if (penguinColor.equals(white)) g2d.setColor(Color.white);
            else if (penguinColor.equals(brown)) g2d.setColor(new Color(102,76,40));
            else if (penguinColor.equals(black)) g2d.setColor(Color.black);

            int xPos = mostLeftX + 55;
            int yPos = mostLeftY - 35;
            int width = 70;
            int height = 70;
            g2d.fillOval(xPos, yPos, width, height);
          }
        }


//        polygons.add(polygon);

      }
    }
  }

  /**
   * Add all tiles from outside to the panel, which will be used for generating view.
   *
   * @param board 2d ArrayList of fish tiles.
   */
  public void addBoard(ArrayList<ArrayList<Tile>> board) {
    this.board = board;
  }
}
