Design Task

Game State:
Players and referees will need to access and manipulate game states, its extremely important especially for referees 
in order to rule check each move and for players to determine the strategic plan that they’re going to create to win the tournament. 
In order to represent the game states that allows players and referees to do their functions appropriately, 
we’ll represent the board as JSON file that wraps a 2D array of fish tiles. Each fishtile will have their specific parameters like
a boolean value that determines if its empty, a x position, a y position and a number that represents the number of fish in that
specific tile. Then we'll also have another JSON file that has a list of all the players within the tournament that is only visible
and updatable by the referee. The list will contain 6 players that has the age of player, the color of their penguins, the number of
fish that they've taken. We'll continously add up all the fish that each player has taken and determine which of them are the winner. 

Java : ArrayList<ArrayList<fishTile>> -> JSON : [[fishTile1], [fishTile2], [fishTile3]]
Java : Map<PlayerID, PlayerData> -> JSON : {PlayerID : [data1, data2, data3]}

External Interface:
In order to implement this tournament a standard external interface would be used by each player to parse the JSON game state
on their local machine and a standard method that determines if each of the player's movement are possible based on the referee. Each
move that they've taken will then update the JSON game state file and will contiously update until there is a clear winner. 
