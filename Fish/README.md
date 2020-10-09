*Dadiel Hantoro & Yu Lin*
# - Fish 
## Roadmap:
    | Fish
    | Planning
    |   | milestons.pdf
    |   | system.pdf
    |   | self1.pdf
    |   | game-state.md
    | Common
    |   | Fish
    |   |   | Fish.iml
    |   |   | Fish.png   
    |   |   | Pom.xml 
    |   |   | target/
    |   |   | src
    |   |   |   |
    |   |   | Main.java    
    |   |   | controllers
    |   |   |   |
    |   |   | FishController.java
    |   |   | models
    |   |   |   |
    |   |   | FishModel.java 
    |   |   | FishTile.java
    |   |   | views
    |   |   |   |
    |   |   | FishView.java 
    |   |   | TilesPanel.java   
    |   |   | test
    |   |   |   |
    |   |   | FishControllerMock.java 
    |   |   | FishControllerMockTest.java 
    |   |   | FishModelTest.java 
    |   |   | FishTileTest.java 

## Milestones
### 2 — The Game Pieces
In the first assignment we designed and implemented data representations of tiles and boards plus visual representations of fish and penguins (penguins are represent as circles). The board representaion now has the following pieces of functionality:
* creating a board that has holes in specific places and is set up with a minimum number of 1-fish tiles;
* creating a board that has the same number of fish on every tile and has no holes
* determining the positions reachable via straight lines from a given position
* removing a tile from a board; and
* rendering a the tiles graphically.

### GUI & Running in Console
Main method can take in the inputs for generating the model, the valid sequence of inputs are: 
1. Width - Positive integer.
2. Height - Positive integer larger than 1.
3. Maximum number of fish on a tile - Positive integer range from 1 to 5.
4. Is number of fishes on each tiles randomly distributed from 1 to the third input number - "random" String or "nonrandom“ String.

Then it opens for user to input the tiles that they want to empty, valid sequence of inputs are"
1. x position of the tile - Natural Number
2. y position of the tile - Natural Number

When finished emptying the tiles, input the following command to show the GUI:

    -run

GUI is generated through our Model/View/Controller (MVC) design pattern, which the controller takes care of the work between model and view, the model handles the logic, and the view draws the GUI components.

### Test

#### JUnit tests directory:

    Fish/Common/Fish/test/

#### Testing with Maven:
To install Maven:

    mvn clean install

To run entire test suite:

    mvn test

To run single/specific test(s):

    mvn -Dtest=TestClassName test

#### Manual testing in a IDE:
1. Clone the project & set up the SDK in your favorite IDE.
2. Run conmmand: $ mvn clean install
3. Locate the test files where you can run single tests or the entire classes of tests.

#### Test Result:
Tests run: 62, Failures: 0, Errors: 0, Skipped: 0


