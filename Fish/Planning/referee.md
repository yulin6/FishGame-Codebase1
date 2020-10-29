#### Tournament Manager:
The tournament manager will set up the initial parts of the game like the referee and the players. We think we could implement a tournament manager by creating a builder pattern. Moreover, we also believe that there will also be a “Game Component” which are the games in a tournament and each game would have referee and ArrayList of players.

#### Referee:
The referee built the initial game model from the information given from the tournament manager/Game component. The referee then constructs the model which contains the game board and creates the initial fish state before any penguins are placed on the board. The referee also has the ability to create holes on the board.

Before all the penguins are placed, the referee communicates with the players and are given actions to place penguins on the board and determines if each placement is a legal and valid.

Through out the game, the referee will continue to communicate with the players by getting each turn of player per round in which the players send out move actions and the referee determines if their movement is legal and valid. Each legal movement then modifies the actual state of the game and players are given a copy of the state of the game.

If a player makes an illegal move, a referee has the right to kick a player out of the game and remove a penguin on the board. The referee also has the ability to shut down the game if there are any problems during the game. 

The referee could determine if the game is over, after there are no other possible movement that any player can make. The referee will then report the outcome of the game through a new class called GameReport that contains the ending fish state, the arraylist of players that failed and cheat and also the winning player.

#### Referee Interface:

    /**
    * The referee interface.
     */
    public interface RefereeInterface {


    /** Gets the current player based on the current state of the game.
     *
     * @return The Player that should make a move on the game.
     */
     Player getTurnFromState();



    /** Executes an action that is given from the player, which will result into a new game state
     * where the penguin placement is different and the player's score is updated.
     *
     * @return If the action is  valid, then returns the fish state corresponding to the action given.
     * @throws IllegalArgumentException If the action is illegal.
     */
     FishState executePlayerAction(IAction playerAction) throws IllegalArgumentException;



    /**Determines if the current fish game is over based on the current game state.
     *
     * @return a boolean value that determines if the game is over.
     */
     boolean isGameOver();



    /** Removes a player and its penguins from the Game State.
     *
     * @param player that has made an illegal move.
     */
    void removePlayerAndPenguins(Player player);



    /** Changes the given position corresponding to a tile into a hole.
     *
     * @param positionOfTile the position of the tile.
     */
    void makeHole(Position positionOfTile);



    /** Creates a fish model and creates a board. Width and height are used for determine the
     * size of the game board. When isRandomized is true, it means we are in a RANDOM mode, the third input:
     * minOneFishNumOrFishNumOnTiles represent the minimum number of 1-fish tiles on the board. When
     * isRandomized is false, it means we are in a NONRANDOM mode, the third input:
     * minOneFishNumOrFishNumOnTiles represent the number of fishes that will be displayed on each
     * tiles. or the detail of the tiles indexing, please refer to the example
     * in the README.
     *
     * @param width the width of the game board.
     * @param height the height of the game board.
     * @param minOneFishNumOrFishNumOnTiles represents different things based on the mode, please
     * refer to the explanation above.
     * @param isRandomized is in RANDOM or NONRANDOM mode.
     * @return a FishModel based on the values given.
     */
     FishModel makeFishModel(int width, int height, int minOneFishNumOrFishNumOnTiles,
      boolean isRandomized);



    /** Creates a fish state based on the fish model and the ArrayList of players.
     *
     * @param fishModel is the current model that the tournament will be using.
     * @param players is the ArrayList of Player from the tournament manager.
     * @return a FishState based on the values given.
     */
    FishState makeFishState(FishModel fishModel, ArrayList<Player> players);



    /** Shuts down the game.
     *
     */
    void terminateGame();



     /** Creates a GameReport class of how the tournament went specifically the ending game state, the
      * arraylist of players that failed anc cheated and also the winning player.
     *
     * @param isGameOver a boolean value that determines if the game is over.
     * @return A GameReport class that has three fields, a Fish State, an ArrayList of failing
     * and cheating players, and winning a player.
     */
     GameReport gameOverReport(boolean isGameOver);

    }
