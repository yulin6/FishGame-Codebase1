package models;

import java.util.ArrayList;

public class Player {

  private int age;
  private int totalFish;
  private PenguinColor penguinColor;
  private ArrayList<Penguin> penguins;

  public Player(int age){
    this.age = age;
    totalFish = 0;
    penguins = new ArrayList<Penguin>();
  }


  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getTotalFish() {
    return totalFish;
  }

  public void addTotalFish(int totalFish) {
    this.totalFish += totalFish;
  }

  public PenguinColor getPenguinColor() {
    return penguinColor;
  }

  public void setPenguinColor(PenguinColor penguinColor) {
    this.penguinColor = penguinColor;
  }

  public ArrayList<Penguin> getPenguins() {
    return penguins;
  }

  public void addPenguin(Penguin penguin) {
    this.penguins.add(penguin);
  }





}
