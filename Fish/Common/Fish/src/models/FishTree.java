package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FishTree  {



  private FishState fishState;
  private ArrayList<FishTree> states;

  public FishTree (FishState fishState){
    if (!fishState.areAllPenguinsPlaced()) {
      throw new IllegalArgumentException("Error: incomplete number of penguins for each player");
    }
    this.fishState = fishState;
    states = new ArrayList<FishTree>();
    generateTree();
  }

  public void generateTree(){


  }

  public FishState getFishState() {
    return fishState;
  }

  public ArrayList<FishTree> getStates() {
    return states;
  }



}
