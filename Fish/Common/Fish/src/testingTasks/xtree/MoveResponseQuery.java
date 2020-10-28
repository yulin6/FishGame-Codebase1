package testingTasks.xtree;

import java.util.ArrayList;
import testingTasks.xstate.FishStateX;

public class MoveResponseQuery {

  FishStateX state;
  ArrayList<Integer> from;
  ArrayList<Integer> to;


  public MoveResponseQuery(FishStateX state, ArrayList<Integer> from,
      ArrayList<Integer> to) {
    this.state = state;
    this.from = from;
    this.to = to;
  }

}
