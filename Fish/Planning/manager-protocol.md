### Tournament Manager Protocol

The tournament manager will receive an ArrayList of PlayerInfo from sign ups and then from there, the tournament manager will create Players for each PlayerInfo. PlayerInfo is the data for creating a Player class, in order to construct a PlayerInfo you’ll just need an age. Other values like fishScore will be set to 0 and the PenguinColor will be set by the referee later.

After constructing the ArrayList of Players, the tournament manager will then create an ArrayList of Referees from the ArrayList of Players. Each referee will represent a game within the tournament. The referee for each game will assign a PenguinColor for each player in their own game.  

The tournament manager will supervise the whole game, if a whole round has finished in which all the referees within the tournament has set their game phase to over, the tournament manager will have the ability to get statistics of all the games as a class called GameReport. The GameReport will have the necessary ArrayList of winning players to construct new referees for the next round of the tournament. The tournament will continue to create new rounds until there is only one player standing or the size of the winning players is equal to 1. Before each new rounds, the score of winning players will be set to zero.

Through out the round, the tournament manager has the ability to update the local ArrayList of GameObservers. The tournament manager will send out strings of on-going actions that are happening through out a certain game. They’ll also have the ability to add or remove game observers through out the tournament. 
