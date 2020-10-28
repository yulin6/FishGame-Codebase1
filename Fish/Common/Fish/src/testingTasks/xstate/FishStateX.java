package testingTasks.xstate;

import java.util.ArrayList;

public class FishStateX {



  ArrayList<PlayerX> players;
  ArrayList<ArrayList<Integer>> board;


  public FishStateX(ArrayList<PlayerX> players, ArrayList<ArrayList<Integer>> board) {
    this.players = players;
    this.board = board;
  }

  public ArrayList<PlayerX> getPlayers() {
    return players;
  }

  public void setPlayers(ArrayList<PlayerX> players) {
    this.players = players;
  }

  public ArrayList<ArrayList<Integer>> getBoard() {
    return board;
  }

  public void setBoard(ArrayList<ArrayList<Integer>> board) {
    this.board = board;
  }
}
