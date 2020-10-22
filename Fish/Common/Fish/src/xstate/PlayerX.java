package xstate;

import java.util.ArrayList;
import models.PenguinColor;
import models.Position;

public class PlayerX {

   PenguinColor color;
   int score;
   ArrayList<ArrayList<Integer>> places;


   public PlayerX(int score, PenguinColor color) {
      this.score = score;
      this.color = color;
      this.places = new ArrayList<>();
   }


   public void addPlace(ArrayList<Integer> place) {
      this.places.add(place);
   }
}
