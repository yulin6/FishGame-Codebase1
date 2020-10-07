package views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import models.FishTile;

public class FishView extends JFrame {

  private FishPanel fishPanel;

  public FishView(){
    super();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(0,0,screenSize.width, screenSize.height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    fishPanel = new FishPanel();
    this.add(fishPanel);

//    JScrollPane scrollPane = new JScrollPane(fishPanel);
//    this.add(scrollPane);
  }

  public void refresh() {
    this.repaint();
  }

  public void makeVisible() {
    this.setVisible(true);
  }

  public void addBoardAndMaxFishNum(ArrayList<ArrayList<FishTile>> board, int maxFishNum) {
    this.fishPanel.addDataToPanel(board, maxFishNum);
  }

}
