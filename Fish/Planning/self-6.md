## Self-Evaluation Form for Milestone 6

Indicate below where your TAs can find the following elements in your strategy and/or player-interface modules:

The implementation of the "steady state" phase of a board game
typically calls for several different pieces: playing a *complete
game*, the *start up* phase, playing one *round* of the game, playing a *turn*, 
each with different demands. The design recipe from the prerequisite courses call
for at least three pieces of functionality implemented as separate
functions or methods:

- the functionality for "place all penguins"
  - private void askPlayersToPlacePenguin( ): <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/admin/Referee.java#L155-L172>
  - public GameReport startGameTillTheEnd( ): <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/admin/Referee.java#L139-L152>
  - the askPlayersToPlacePenguin( ) is a private helper method of the startGameTillTheEnd( ).

- a unit test for the "place all penguins" funtionality 
  - Since it is a private helper method, we did not have seperate tests for it, but we have tests for startGameTillTheEnd( ), which tests for the askPlayersToPlacePenguin( ) indirectly.
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/test/admin/RefereeTest.java#L212-L304>

- the "loop till final game state"  function
  - public GameReport startGameTillTheEnd( ): <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/admin/Referee.java#L139-L152>

- this function must initialize the game tree for the players that survived the start-up phase
  - we only uses game tree in the below method, which exists in a house player and would be used for finding their minimax move. We did not use game tree in the referee since our FishState would handle the turn taking, so we thought computing a tree is not necessary.
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/player/HousePlayer.java#L39-L44>


- a unit test for the "loop till final game state"  function
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/test/admin/RefereeTest.java#L212-L304>

- the "one-round loop" function
  - we are not sure about what a one-round loop is.
  - our code use the startGameTillTheEnd( ) as the main entrance of starting a game, which it calls askPlayersToPlacePenguin( ) and askPlayersToMovePenguin( ) to ask players to place all penguins and then move penguins. These two methods calls executeActionFromPlayer( ) to receive and execute player's action in each turn. Turn taking is handled by our game state where every action including place penguin, move penguin or remove player will result into a new game state, which will be used for checking whether the game is over as well.
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/admin/Referee.java#L139-L262>


- a unit test for the "one-round loop" function
  - again, only the startGameTillTheEnd( ) is public.
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/test/admin/RefereeTest.java#L212-L304>


- the "one-turn" per player function
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/admin/Referee.java#L214-L262>


- a unit test for the "one-turn per player" function with a well-behaved player 
  - the executeActionFromPlayer method is private, but we have tests for well behaved players with startGameTillTheEnd( ) method.
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/test/admin/RefereeTest.java#L212-L246>

- a unit test for the "one-turn" function with a cheating player
  - the executeActionFromPlayer method is private, but we have tests for cheating players with startGameTillTheEnd( ) method.
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/test/admin/RefereeTest.java#L279-L304>


- a unit test for the "one-turn" function with an failing player 
  - the executeActionFromPlayer method is private, but we have tests for failing players with startGameTillTheEnd( ) method.
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/test/admin/RefereeTest.java#L248-L277>


- for documenting which abnormal conditions the referee addresses 
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/admin/Referee.java#L27-L29>
  - <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/admin/Referee.java#L214-L222>


- the place where the referee re-initializes the game tree when a player is kicked out for cheating and/or failing 
  - again, we did not use a game tree. We used game states instead since it can handle the turn takings, so whenever a player is removed from a game state, we make sure that it will return a new state where the player is removed so that the player will not be called for turning in the action that they want to perform.
  - the situations when a player will be removed: <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/admin/Referee.java#L249-L259>
  - removeCurrentPlayerInfo( ) : <https://github.ccs.neu.edu/CS4500-F20/annetta/blob/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish/Common/Fish/src/common/models/FishState.java#L522-L552>



**Please use GitHub perma-links to the range of lines in specific
file or a collection of files for each of the above bullet points.**

  WARNING: all perma-links must point to your commit "b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03".
  Any bad links will be penalized.
  Here is an example link:
    <https://github.ccs.neu.edu/CS4500-F20/annetta/tree/b9623dcd4d2c526cbb7f1388c5b5aedbeb232c03/Fish>

