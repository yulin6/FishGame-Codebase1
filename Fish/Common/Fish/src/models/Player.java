package models;

import java.util.ArrayList;

/**
 * Class of Player, which contains how a player is constructed.
 **/
public class Player {

  private int age;
  private int totalFish;
  private PenguinColor penguinColor;
  private ArrayList<Penguin> penguins;

  /**
   * Constructor of the Player class that takes in the age of player.
   *
   * @param age the age of the playing player.
   **/
  public Player(int age){
    this.age = age;
    totalFish = 0;
    penguins = new ArrayList<Penguin>();
  }

  /**
   * Gets the age of the player.
   *
   * @return the age of the player as an int.
   **/
  public int getAge() {
    return age;
  }

  /**
   * Sets the age of the player.
   **/
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets the total number of fish of a player.
   *
   * @return the total fish collected by a player as an int.
   **/
  public int getTotalFish() {
    return totalFish;
  }

  /**
   * Adds the fish gathered by a player.
   **/
  public void addTotalFish(int totalFish) {
    this.totalFish += totalFish;
  }

  /**
   * Gets the penguin color.
   *
   * @return the penguin color based on the PenguinColor enum.
   **/
  public PenguinColor getPenguinColor() {
    return penguinColor;
  }

  /**
   * Sets the penguin color.
   *
   **/
  public void setPenguinColor(PenguinColor penguinColor) {
    this.penguinColor = penguinColor;
  }

  /**
   * Gets the penguins controlled by a player.
   *
   * @return the ArrayList of penguins controlled by a player.
   **/
  public ArrayList<Penguin> getPenguins() {
    return penguins;
  }

  /**
   * Adds a penguin to the ArrayList of penguins controlled by a player.
   **/
  public void addPenguin(Penguin penguin) {
    this.penguins.add(penguin);
  }





}
