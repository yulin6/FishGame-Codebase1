/**
 * The player interface is the API for a player component that has methods to communicate with the
 * game logic or the referee.
 **/
public interface PlayerInterface {

  /**
   * Sends the movement request by the player to the game logic or the referee and will return a
   * JSON formatted string of the FishState. If the movement is illegal, the referee will send an
   * error.
   *
   * @param jsonString is a movement that is formatted using as json values to determine the
   * movement that they want to make.
   * @return a JSON formatted string of the FishState
   */
  String sendMovementOfPenguin(String jsonString);

  /**
   * Sends the initial placement of the penguin request by the player to the game logic or the
   * referee and will return a JSON formatted string of the FishState. If the placement is illegal,
   * the referee will send an error.
   *
   * @param jsonString is the initial placement of their penguin using as json values to determine
   * the initial position of their penguin.
   * @return a JSON formatted string of the FishState
   */
  String sendPlacementOfPenguin(String jsonString);

  /**
   * Generates a FishTree based on the currentState as JSON formatted string and outputs the fish
   * tree in the form of a JSON string. FishTree is a tree that connects all the game states.
   *
   * @param currentState the current state of the fish board as a JSON formatted string.
   * @return a JSON formatted string of the FishTree
   */
  String generateTree(String currentState);

  /**
   * Requests the referee to ask if its my turn as a JSON formatted string.
   *
   * @return a JSON formatted boolean indecating whether is my turn
   */
  String isMyTurn();

  /**
   * Request the referee about my current score as JSON formatted string.
   *
   * @return a JSON formatted string of the my current score.
   */
  String getMyScore();


  /**
   * Gets the current copy of the FishState as JSON formatted String from the Game Logic or the
   * referee.
   *
   * @return a JSON formatted string of the FishState
   */
  String getCopyOfCurrentFishState();
}
