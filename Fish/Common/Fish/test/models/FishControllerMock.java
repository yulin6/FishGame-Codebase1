package models;

import java.util.ArrayList;
import views.FishView;

/**
 * a mock of FishController class, which will be used for testing.
 */
public class FishControllerMock {
  private FishModel model;
  private FishView view;
  private ArrayList<ArrayList<FishTile>> board;


  /**
   * Constructor of the FishController class, takes in a model and a view.
   *
   * @param model FishModel
   * @param view FishView
   */
  public FishControllerMock(FishModel model, FishView view){
    this.model = model;
    this.view = view;
  }

  /**
   * Getting a copy of the board from the model, and use it  for generating view.
   * print "View generated." in this mock class.
   */
  public void generateView(){
    board = model.getBoardCopy();
    view.addBoardToPanel(board);
    view.makeVisible();
    System.out.println("View generated.");
  }
}
