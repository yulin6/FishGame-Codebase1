package common.models;

/**
 * Class of PlayerInfo, which contains the information of a player.
 **/
public class PlayerInfo {


  private long id;
  private int age;
  private int totalFish;
  private PenguinColor penguinColor;


  /**
   * Constructor of the Player class that takes in the age of PlayerX.
   *
   * @param age the age of the playing PlayerX.
   * @param penguinColor the color of their penguins color.
   **/
  public PlayerInfo(int age, PenguinColor penguinColor){
    this.id = System.nanoTime();
    this.age = age;
    this.totalFish = 0;
    this.penguinColor = penguinColor;
  }

  public PlayerInfo(int age){
    this.id = System.nanoTime();
    this.age = age;
    this.totalFish = 0;
  }


  public long getId() {
    return id;
  }

  /**
   * Gets the age of the PlayerX.
   *
   * @return the age of the PlayerX as an int.
   **/
  public int getAge() {
    return age;
  }

  /**
   * Sets the age of the PlayerX.
   **/
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets the total number of fish of a PlayerX.
   *
   * @return the total fish collected by a PlayerX as an int.
   **/
  public int getTotalFish() {
    return totalFish;
  }

  /**
   * Adds the fish gathered by a PlayerX.
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



}
