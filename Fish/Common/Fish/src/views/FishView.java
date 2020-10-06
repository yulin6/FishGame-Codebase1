package views;

import java.util.ArrayList;
import javax.swing.JFrame;
import models.FishModel;
import models.FishTile;

public class FishView extends JFrame {

  private FishPanel fishPanel;

  public FishView(FishModel fishModel){
    super();
    ArrayList<ArrayList<FishTile>> board = fishModel.getBoard();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    fishPanel = new FishPanel(board);
    this.add(fishPanel);
  }

  public void refresh() {
    this.repaint();
  }

  public void makeVisible() {
    this.setVisible(true);
  }

}
