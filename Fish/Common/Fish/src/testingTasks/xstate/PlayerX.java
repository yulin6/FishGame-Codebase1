package testingTasks.xstate;

import java.util.ArrayList;
import common.models.PenguinColor;

public class PlayerX {



   PenguinColor color;
   int score;
   ArrayList<ArrayList<Integer>> places;


   public PlayerX(int score, PenguinColor color) {
      this.score = score;
      this.color = color;
      this.places = new ArrayList<>();
   }

   public PenguinColor getColor() {
      return color;
   }

   public void setColor(PenguinColor color) {
      this.color = color;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public ArrayList<ArrayList<Integer>> getPlaces() {
      return places;
   }


   public void addPlace(ArrayList<Integer> place) {
      this.places.add(place);
   }
}
