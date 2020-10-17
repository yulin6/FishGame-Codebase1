## Self-Evaluation Form for Milestone 3

Under each of the following elements below, indicate below where your
TAs can find:

- the data description of states, including an interpretation:
   - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/6afeff173cbd6631f486a87e94fdf55b0990d4d0/Fish/Common/Fish/src/models/FishGameState.java#L6-L37>
   - This is the purpose statement and the constructor of our FishGameState class, which shows how a game state is constructed and what variables it has.

- a signature/purpose statement of functionality that creates states 
   - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/6afeff173cbd6631f486a87e94fdf55b0990d4d0/Fish/Common/Fish/src/models/FishGameState.java#L23-L37>
   - This is the constructor of our FishGameState class, it takes in a model, containing the game board, and a list of players for constructing a game state.

- unit tests for functionality of taking a turn 
   - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/6afeff173cbd6631f486a87e94fdf55b0990d4d0/Fish/Common/Fish/test/FishGameStateTest.java#L222-L237>
   - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/6afeff173cbd6631f486a87e94fdf55b0990d4d0/Fish/Common/Fish/test/FishGameStateTest.java#L135-L142>
   - Since the functionality of taking a turn is not required in the assignment page, so we set our isPlayerTurn() to be private, which is only being used for the contract checking of placing penguin and making movements. Therefore, the only unit tests of the functionality are the tests above.

- unit tests for functionality of placing an avatar 
   - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/6afeff173cbd6631f486a87e94fdf55b0990d4d0/Fish/Common/Fish/test/FishGameStateTest.java#L60-L142>

- unit tests for functionality of final-state test
   - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/6afeff173cbd6631f486a87e94fdf55b0990d4d0/Fish/Common/Fish/test/FishGameStateTest.java#L303-L333>
   - These are the methods for checking if the game is over, which means no penguin can move.

The ideal feedback is a GitHub perma-link to the range of lines in specific
file or a collection of files for each of the above bullet points.

  WARNING: all such links must point to your commit "6afeff173cbd6631f486a87e94fdf55b0990d4d0".
  Any bad links will result in a zero score for this self-evaluation.
  Here is an example link:
    <https://github.ccs.neu.edu/CS4500-F20/annetta/tree/6afeff173cbd6631f486a87e94fdf55b0990d4d0/Fish>

A lesser alternative is to specify paths to files and, if files are
longer than a laptop screen, positions within files are appropriate
responses.

In either case you may wish to, beneath each snippet of code you
indicate, add a line or two of commentary that explains how you think
the specified code snippets answers the request.

## Partnership Eval 

Select ONE of the following choices by deleting the other two options.


B) My partner and I contributed not *exactly* equally, but *roughly*
   equally to this assignment.


If you chose C, please give some further explanation below describing
the state of your partnership and whether and how you have been or are
addressing this disparity. Describe the overall trajectory of your
partnership from the beginning until now. Be honest with your answer
here, and with each other. Even if it's uncomfortable reading this
together right now.

If you chose one of the other two options, you should feel free to
also add some explanation if you wish. 
