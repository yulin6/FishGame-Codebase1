package xstate;

import java.util.ArrayList;

public class FishStateX {

  ArrayList<PlayerX> players;
  ArrayList<ArrayList<Integer>> board;


  public FishStateX(ArrayList<PlayerX> players, ArrayList<ArrayList<Integer>> board) {
    this.players = players;
    this.board = board;
  }

}
