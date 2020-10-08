*Dadiel Hantoro & Yu Lin*
#- Project Milestones
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


## 2 — The Game Pieces
In the first assignment we designed and implemented data representations of tiles and boards plus visual representations of fish and penguins. The board representaion now has the following pieces of functionality:
* creating a board that has holes in specific places and is set up with a minimum number of 1-fish tiles;
* creating a board that has the same number of fish on every tile and has no holes
* determining the positions reachable via straight lines from a given position
* removing a tile from a board; and
* rendering a the tiles graphically.

#### GUI & Running in Console
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

#### Test
JUnit tests are under directory:
    
    Fish/Common/Fish/test 

To run the tests, you can do the following steps:
1. Clone the project & set up the SDK in your favorite.
2. Run conmmand: $ mvn clean install
3. Locate the test files where you can run single tests or the entire classes of tests.


# - TAHBPL
### Assignment 2: xyes
The xyes is a shell-executable command-line program that echoes the command line arguments to standard output. The program has three different test cases:
If the user does not specify any arguments with the jar file associated with the xyes program, the default function of the program would be to print out “hello world”.
If the user specifies a limit by typing “-limit” before a command, the program will print out 20 lines of the argument.
If the user does not specify a limit before a command, the program will continuously print out the argument and if there are no arguments it will print out “hello world” infinitely.
(Assignment Link: https://www.ccs.neu.edu/home/matthias/4500-f20/B.html)

#### Folder & File Purposes:
    /B : containing the executable program xyes and a folder other.
    /B/xyes : the main executable program.
    /B/other : containing all auxiliary files for xyes project.
    /B/other/out : contains the artifacts of the program and has the jar file, that allows users to use the program in their command prompt/terminal.
    /B/other/src/xyes : containing the source code of xyes.
    /B/other/src/xyes/Main.java : the source code of xyes, written in Java.
    /B/other/test/xyes : containing the test script for xyes.
    /B/other/src/xyes/MainTest.java : the test script of xyes, written in Java with JUnit.
    
#### Roadmap:
    | annetta
    |  README.md
    |  B
    |  | xyes
    |  | Other/xyes
    |  |  | .idea
    |  |  | out
    |  |  | xyes.iml
    |  |  | src/xyes
    |  |  |      | Main.java
    |  |  | test/xyes
    |  |  |      | MainTest.java



### Assignment 3: xjson
The xjson is a program that consumes a sequence of well-formed JSON values from STDIN and delivers JSON to STDOUT. The output of the sequence is a JSON object that has two values a “count” and a “seq”, the count is the number of JSON values read and the second field is the JSON list of all JSON values read in order. The second one is a JSON list whose first element is the count of JSON values read and the remainder is the sequence of JSON values read in reverse order. The main method scans the values that are typed in by the user, the isBracesOrBracketCompleted determines whether the values that are typed are proper JSON objects. The addSpaceForQuotes is for specific instances when there is a string two quotation mark that is inserted into the STDIN. The xjsonTest is the test for the main, the first tests the quote on strings with no quotation marks, then the couple of tests after that tests whether a string is inside a map of JSON values to indicate if a JSON object is complete or not. Then the next couple of tests were used to determine whether printing nothing, numbers, strings and JSON objects were outputted JSON objects explained above. 

#### Roadmap:
    |  C
    |  | xjson
    |  | Other/xjson
    |  |  | .idea
    |  |  | out
    |  |  | xjson.iml
    |  |  | src/xjson
    |  |  |      | Main.java
    |  |  | test/xjson
    |  |  |      | MainTest.java

### Assignment 4: xgui
The xgui is a program that consumes an string from the command line with an integer value that creates a panel containing a hexagon shape. The integer is used to determine the size of the hexagon shape in the panel, which in turn shapes the panel size as well. The main class has a main method that consumes an argument which is a string that contains an integer value, it then calls the isPosInt method to determine whether the input is a valid, if not the main method will print an error message to the command line. If the inputs are valid, the integer value is sent to the HexagonPanel class in which the paintComponent is called to make the actual drawing of the shape in the panel. In order to close the program, the user are able to do this by clicking anywhere within the panel and this is done through the addMouseListenerToPanel method that listens to mouse clicks. The MainTest class has tests for isPosInt to determine wether the user point is valid point and the other tests after that are test methods to check the messages that comes out from inputing invalid commands. 

#### Roadmap:
    |  D
    |  | xgui
    |  | Other/xgui
    |  |  | .idea
    |  |  | out
    |  |  | xgui.iml
    |  |  | src/xgui
    |  |  |      | Main.java
    |  |  |      | HexagonPanel.java
    |  |  | test/xgui
    |  |  |      | MainTest.java
    
### Assignment 5: xtcp
The xgui is a program that consumes a sequence of well-formed JSON values from the input side of TCP connection and deliver JSON to the output side of a TCP connection after the input side is closed. The argument that the program takes is a server port number and if the user does not inpt a server port number, a default port number of 4567 is used instead. The netcat program is then used to send the JSON information that is then transimitted via TCP to the computer with the IP address. The program works through inputting a server port number through the main method that is then filtered by the isVlidPortNum method to determine its validity. After the validity of the server port number is checked, the serverOperations method connects with the server and collects the DataInputStream that is then turned to JsonArray objcet through the use of the generateJsonArray method. serverOperations also calls the generateOutputs method to populated the DataOutputStream with the populated JsonArray from the DataInputStream which then outputs DataOutputStream to the console.  There are tree integration tests that are located in the xtcp directory Test/. 

#### Roadmap:
    |  E
    |  | xtcp
    |  | assessment.pdf
    |  | experience.pdf
    |  | Other/xtcp
    |  |  | .idea
    |  |  | out
    |  |  | pom.xml
    |  |  | xtcp.iml
    |  |  | src/xtcp
    |  |  |      | XtcpServer.java
    |  |  | test/xtcp
    |  |  |      | 1-in.json
    |  |  |      | 1-out.json
    |  |  |      | 2-in.json
    |  |  |      | 2-out.json
    |  |  |      | 3-in.json
    |  |  |      | 3-out.json
