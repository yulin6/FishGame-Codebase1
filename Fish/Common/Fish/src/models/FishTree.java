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

  }

  public void generateTree(){

  }



}
