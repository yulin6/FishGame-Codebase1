package common.views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import common.models.Tile;

/**
 * The main view class extends JFrame, will work as a frame for displaying the game.
 */
public class FishView extends JFrame {

  private TilesPanel tilesPanel;

  /**
   * The constructor of the FishView class, which will set up the size of the frame, and add a tile
   * panel to it.
   */
  public FishView() {
    super();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(0, 0, screenSize.width, screenSize.height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    tilesPanel = new TilesPanel();
    this.add(tilesPanel);

//    JScrollPane scrollPane = new JScrollPane(fishPanel);
//    this.add(scrollPane);
  }

  /**
   * Repaint the current JFrame.
   */
  public void refresh() {
    this.repaint();
  }

  /**
   * Pops up the window showing the this view.
   */
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Add 2d ArrayList of fish tiles to the tile panel, which will be used for drawing.
   *
   * @param board 2d ArrayList of fish tiles.
   */
  public void addBoardToPanel(ArrayList<ArrayList<Tile>> board) {
    this.tilesPanel.addBoard(board);
  }

}
