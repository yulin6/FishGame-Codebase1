*Dadiel Hantoro & Yu Lin*
# - Fish 
## General
### Roadmap:
    | Fish
    | Planning
    |   | milestons.pdf
    |   | system.pdf
    |   | self-1.md
    |   | game-state.md
    |   | self-2.md
    |   | games.md
    |   | self-3.md
    |   | player-protocol.md
    | Common
    |   | state.java 
    |   | player-interface.java   
    |   | game-tree.java 
    |   | Fish
    |   |   | Fish.iml
    |   |   | Fish.png   
    |   |   | Pom.xml 
    |   |   | target/
    |   |   | src
    |   |   |   | Main.java  
    |   |   |   | xboard.java 
    |   |   |   | META-INF
    |   |   |   | controllers
    |   |   |   |   | FishController.java
    |   |   |   | models
    |   |   |   |   | Actions
    |   |   |   |   |   | IAction.java 
    |   |   |   |   |   | MovePenguinAction.java    
    |   |   |   |   | FishGameTreeNode.java
    |   |   |   |   | FishGameState.java
    |   |   |   |   | FishModel.java 
    |   |   |   |   | Tile.java
    |   |   |   |   | Player.java 
    |   |   |   |   | Penguin.java 
    |   |   |   |   | Position.java 
    |   |   |   |   | PenguinColor.java
    |   |   |   | views
    |   |   |   |   | FishView.java 
    |   |   |   |   | TilesPanel.java   
    |   |   | test
    |   |   |   | FishControllerMock.java 
    |   |   |   | FishControllerMockTest.java 
    |   |   |   | FishGameStateTest.java 
    |   |   |   | FishTreeNodeTest.java 
    |   |   |   | PositionTest.java
    |   |   |   | FishModelTest.java
    |   |   |   | TileTest.java 
    |   |   |   | PlayerTest.java
    |   |   |   | PenguinTest.java 

### Test

#### xtest:

Location:

    ./Fish/xtest

To run the xtest in console:

    ./xtest

#### JUnit tests directory:

    Fish/Common/Fish/test/

#### Testing with Maven:
To install Maven:

    mvn clean install

To run entire test suite:

    mvn test

To run single/specific test(s):

    mvn -Dtest=TestClassName test

#### Testing in a IDE:
1. Clone the project & set up the SDK in your favorite IDE.
2. Run conmmand: $ mvn clean install
3. Locate the test files where you can run single tests or the entire classes of tests.

#### Test Result:
Tests run: 62, Failures: 0, Errors: 0, Skipped: 0

## Milestones
### 2 — The Game Pieces
In the first assignment we designed and implemented data representations of tiles and boards plus visual representations of fish and penguins (penguins are represent as circles). The board representaion now has the following pieces of functionality:
* creating a board that has holes in specific places and is set up with a minimum number of 1-fish tiles;
* creating a board that has the same number of fish on every tile and has no holes
* determining the positions reachable via straight lines from a given position
* removing a tile from a board; and
* rendering a the tiles graphically.

#### Board Indexing
x position represents the column number, y position represents the row number on the fishboard. Refer to the graph below:

![Board Indexing](./Other/boardIndexing.png)

#### GUI & Running in Console
Main method can take in the inputs for generating the model, the valid sequence of inputs are: 
1. Width - Positive integer.
2. Height - Positive integer larger than 1.
3. MinOneFishNumOrFishNumOnTiles (stands different thing depends on the fourth argument):
    -  Minimum number of one-fish tiles - Positive integer range from 0 to Width * Height. (Random Mode)
    - Number of fishes on each tiles - Positive integer range from 1 to 5. (Nonrandom Mode).
4. RandomModeOrNonRandom:
    - Random mode will have a minimum number of one-fish tiles, fish number on other tiles are randomly distributed from 2 to 5 - "random"
    - Nonrandom mode will have each tiles containing the same fish number. - "nonrandom".

Then it opens for user to input the tiles that they want to empty, valid sequence of inputs are"
1. x position of the tile - Natural Number
2. y position of the tile - Natural Number

When finished emptying the tiles, input the following command to show the GUI:

    -run

GUI is generated through our Model/View/Controller (MVC) design pattern, which the controller takes care of the work between model and view, the model handles the logic, and the view draws the GUI components.

### 3 - The Game State
A game state represents the current state of a game: the state of the board, the current placements of the penguins, knowledge about the players, and the order in which they play.

A game state representation may be used by the referee component to perform turns and check rule-conforming behavior. A player component can use it for planning purposes.

The game-state representation come with the following pieces of functionality:

- creating a state for a certain number of players;

- place an avatar on behalf of a player;

- move an existing avatar from one spot to another on behalf of the player;

- determine whether any player can move an avatar; and

- rendering the state graphically.

#### Implementations

    FishGameState

The FishGameState class stores the FishModel, which is a game board class of the fish game. Board is an ArrayList of ArrayList of Tile that is taken from the FishModel. playersSortedByAgeAscend is the sorted array of Player by age. penguinsOnBoard is an empty ArrayList of Penguin that will be populated in the FishGameState. currentPlayerNum is the current index of the player, which starts from 0. totalPlayerNum is the total number of players that will be playing in this tournament. To consturct the class, it takes in a FishModel and a ArrayList of Players.

    Player

Player representation of the user who is playing the fish game, which has 3 parameters: age, totalFish, penguinColor. Age is used for determine the order of round robin player turns. TotalFish represents the total number of fishes the player have got in a game. PenguinColor differentiate each player within each game, and their avatars. 

    Penguin
A penguin's visual representation can be shown now as circles with different colors based on their PenguinColor.


#### Test Harness

The harness consumes its JSON input from STDIN and produces its results to STDOUT. The tests are formulated as pairs of files: \<n>-in.json, the input, and \<n>-out.json, the expected result, for an integer \<n> ranged from 1 to 3.

Input: 

    Board-Posn is

      { "position" : Position,

        "board" : Board}
Its inputs is an object with two fields:

    Board is a JSON array of JSON arrays where each element is

    either 0 or a number between 1 and 5.

      The size of the board may not exceed a total of 25 tiles.

    *INTERPRETATION* A 0 denotes a hole in the board configuration. All other

    numbers specify the number of fish displayed on the tile.

    

    Position is a JSON array that contains two natural numbers:

      [board-row,board-column].

    INTERPRETATION The position uses the computer graphics coordinate system

      meaning the Y axis points downwards. The position refers to a tile with at least one fish on it.

Its expected outputs: 
    
    the number of tiles on the board that can be reached from the specified position.

Executable Location:

    annetta/3/xboard

Tests Files Location:

    annetta/3/Tests/<n>-in.json, <n>-out.json

##### Running example:
In console:

    cat ./Tests/1-in.json | ./xboard

Output:

    2


### 4 - The Game Tree

A game tree data representation, including the following operations. A game tree represents an entire game, starting from some state. For each state it connects to all legal successor states. Each transition corresponds to a legal action of the player whose turn it is in this state.

The game representation should come with these pieces of functionality:

-   creating a complete tree for a state to which players will not add any more penguins;

- a query facility that for a given tree node and action A either signals that A is illegal or returns the state that would result from taking action A;

- a query facility that for a given tree node S and function applies this function to all states directly reachable from S.

#### Implementations

    FishTreeNode

FishTreeNode is a representation of a tree of FishState and FishTreeNodes. The parentNode is a node its parent node of the current node, if it is the start of the tree, the parentNode will be null. CurrentState is the current fishState of a certain game at certain point in time. DirectReachableStates is the states that are directly reachable from the current FishState. childNodes is an ArrayList\<FishTreeNode>, which are the directly connected tree nodes, and it will only be generated when the method by calling generateChildNodes().

    IAction

IAction is an interface that allows users the ability to apply a certain action to a certain FishState using performAction() method. Other actions can be further implemented in the future, e.g. removePlayeAction(FishState state)

    MovePenguinAction

MovePenguinAction is a class that represent a movement on the Fish game that implements on the IAction interface. In order to move penguins and modify fish states, there are 4 parameters that needs to be inserted to move a penguin. A targetX representing the column of the fish board, a targetY representing the row of the fish board, a penguin and a player.

    Position

Position class that represent a position on the board, the x represent the column and the y represent the row on the Fish board.

#### Test Harness
he harness consumes its JSON input from STDIN and produces its results to STDOUT. The tests are formulated as pairs of files: \<n>-in.json, the input, and \<n>-out.json, the expected result, for an integer \<n> ranged from 1 to 3.

Input:

    State:

      { "players" : Player*,

        "board" : Board }



    Player* is

      [Player, ..., Player]

    INTERPRETATION The array lists all players and specifies the order

    in which they take turns.



    Player is

      { "color" : Color,

        "score" : Natural,

        "places" : [Position, ..., Position] }

    INTERPRETATION The color identifies a player's penguins on the board,

    the score represents how many fish the player has collected so far,

    and the last field shows where the player's penguins are located.

    CONSTRAINT All penguins must occupy distinct tiles on the board.



    Color is
    
        One of "red", "black", "white", "brown"


Output:

    Its expected output is the effect of a silly player strategy of taking a turn 
    That is, it is the State that results from moving the first player’s first 
    penguin one step either North, NorthEast, SouthEast, South, SoutheWest, or 
    Northwest (in this order) from its current position—if possible. Otherwise the 
    expected output is False. The order in which a JSON array specifies a player's 
    penguin positions remains the same.

Executable Location:

    annetta/4/xstate

Tests Files Location:

    annetta/4/Tests/<n>-in.json, <n>-out.json

##### Running example:
In console:

    cat ./Tests/1-in.json | ./xstate

Output:

    {"players":[
        {"color":"red","score":11,"places":[[2,0],[0,1]]},
        {"color":"black","score":6,"places":[[1,0],[1,1]]},
        {"color":"white","score":2,"places":[[2,1],[2,2]]}],
    "board":[[0,1,1],[1,5,1],[5,1,1],[5,1,1]]}
