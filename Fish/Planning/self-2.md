## Self-Evaluation Form for Milestone 2

A fundamental guideline of Fundamentals I, II, and OOD is to design
methods and functions systematically, starting with a signature, a
clear purpose statement (possibly illustrated with examples), and
unit tests.

Under each of the following elements below, indicate below where your
TAs can find:

- the data description of tiles, including an interpretation:
    - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/a9b77648d31afcb0de664401b6d59446c52c65ae/Fish/Common/Fish/src/models/FishTile.java#L6> 
    - FishTile class is an interpretaion of the game board tile, it contains three parameters: 
        - isEmpty - a boolean that is used for checking if the tile is a hole or not
        - fishNum - an int that represents the number of the fishes on the tile
        - penguin - a Penguin class which can be null, representing no penguin on the tile, or if it is not null, then there is a penguin on the tile. We also have setters and getters for each parameter.

- the data description of boards, include an interpretation:
    - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/a9b77648d31afcb0de664401b6d59446c52c65ae/Fish/Common/Fish/src/models/FishModel.java#L11> 
    - Our board is a ArrayList of ArrayList of FishTiles in the FishModel, in which the inner ArrayList is a row, and each FishTiles in the same row represents a different colomn. You can picture that the origin of game board is located on top-left corner, positive x goes to the right, positive y goes to the bottom. Since we thought the entry point of our program should be in the main method, so we did some of the input checking of the FishModel constructor inside of Main class, things like width, height cannot be less than 0. - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/a9b77648d31afcb0de664401b6d59446c52c65ae/Fish/Common/Fish/src/Main.java#L33-L85>
    - We misinterpreted the assignment requirement, we thought a the fish number on each tiles should be larger than or equal to 1 fish. After the code walk, we realized that, instead, there should be a minimum number of fish tiles that has only 1 fish. Therefore, we missed a constructor for creating board with minimum 1-fish tiles.

- the functionality for removing a tile:
   - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/a9b77648d31afcb0de664401b6d59446c52c65ae/Fish/Common/Fish/src/models/FishModel.java#L57-L78>
  - purpose: The method will take in a x position and a y position (ints), which will be used for emptying the tile in the board, setting the isEmpty attribute of the tile to true. An empty tile represents a hole on the game board.
  
  - signature: 
    - @param xPos: x position (int) of the tile to be emptied.
    - @param yPos: y position (int) of the tile to be emptied.
  
  - unit tests: <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/a9b77648d31afcb0de664401b6d59446c52c65ae/Fish/Common/Fish/test/FishModelTest.java#L33-L152>

- the functiinality for reaching other tiles on the board:
    - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/a9b77648d31afcb0de664401b6d59446c52c65ae/Fish/Common/Fish/src/models/FishModel.java#L80-L212>
  - purpose: Takes in the x and y positions (ints) of starting tile and targeting tile, and determine if they are in a "straight line", which means the line connects the two tiles can only be crossing borders; and if they or any of the tiles in between them are empty, return false. Otherwise, return true.
  
  - signature: 
    - @param startX: x position (int) of the starting tile.
    - @param startY: y position (int) of the starting tile.
    - @param targetX: x position (int) of the targeting tile.
    - @param targetY: y position (int) of the targeting tile.
    - @return boolean that represents if the move attempt from the start tile to the target tile is valid or not.
  
  - unit tests:<https://github.ccs.neu.edu/CS4500-F20/annetta/blob/a9b77648d31afcb0de664401b6d59446c52c65ae/Fish/Common/Fish/test/FishModelTest.java#L154-L446>

The ideal feedback is a GitHub perma-link to the range of lines in specific
file or a collection of files for each of the above bullet points.

  WARNING: all such links must point to your commit "a9b77648d31afcb0de664401b6d59446c52c65ae".
  Any bad links will result in a zero score for this self-evaluation.
  Here is an example link:
    <https://github.ccs.neu.edu/CS4500-F20/annetta/tree/a9b77648d31afcb0de664401b6d59446c52c65ae/Fish>

A lesser alternative is to specify paths to files and, if files are
longer than a laptop screen, positions within files are appropriate
responses.

In either case you may wish to, beneath each snippet of code you
indicate, add a line or two of commentary that explains how you think
the specified code snippets answers the request.
