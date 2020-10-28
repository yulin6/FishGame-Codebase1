package common.models;

/**
 * Class of Player, which contains how a PlayerX is constructed.
 **/
public class Player {


  private long id;
  private int age;
  private int totalFish;
  private PenguinColor penguinColor;
//  private ArrayList<Position> penguinsPositions;
//  private ArrayList<Penguin> penguins;

  /**
   * Constructor of the Player class that takes in the age of PlayerX.
   *
   * @param age the age of the playing PlayerX.
   * @param penguinColor the color of their penguins color.
   **/
  public Player(int age, PenguinColor penguinColor){
    this.id = System.nanoTime();
    this.age = age;
    this.totalFish = 0;
    this.penguinColor = penguinColor;
//    penguins = new ArrayList<Penguin>();
  }

  //A empty constructor that's needed for Kryo library
  private Player(){ }


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



//  public ArrayList<Position> getPenguinsPositions() {
//    return penguinsPositions;
//  }
//
//  public void setPenguinsPositions(ArrayList<Position> penguinsPositions) {
//    this.penguinsPositions = penguinsPositions;
//  }


}
