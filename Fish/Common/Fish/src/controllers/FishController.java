package controllers;

import java.util.ArrayList;
import models.FishModel;
import models.FishTile;
import views.FishView;

public class FishController {

  private FishModel model;
  private FishView view;
  private ArrayList<ArrayList<FishTile>> board;


  public FishController(FishModel model, FishView view){
    this.model = model;
    this.view = view;
  }

  public void generateView(){
    board = model.getBoardCopy();
    int maxFishNum = model.getMaxFishNum();
    view.addBoardAndMaxFishNum(board, maxFishNum);
    view.makeVisible();
  }
}
