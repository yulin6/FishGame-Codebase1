package common.controllers;

import java.util.ArrayList;
import common.models.FishModel;
import common.models.Tile;
import common.views.FishView;

/**
 * Controller class for handling the model and view of the fish game.
 */
public class FishController {

  private FishModel model;
  private FishView view;
  private ArrayList<ArrayList<Tile>> board;


  /**
   * Constructor of the FishController class, takes in a model and a view.
   *
   * @param model FishModel
   * @param view FishView
   */
  public FishController(FishModel model, FishView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Getting a copy of the board from the model, and use it  for generating view.
   */
  public void generateView() {
    board = model.getBoard();
    view.addBoardToPanel(board);
    view.makeVisible();
  }
}
