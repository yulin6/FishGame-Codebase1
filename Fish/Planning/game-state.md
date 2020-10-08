##Design Task

####Game State:
Players and referees will need to access and manipulate game states, its extremely important especially for referees in order to rule check each move and for players to determine the strategic plan that they’re going to create to win the tournament. 

In order to represent the game states that allows players and referees to do their functions appropriately, currently, we represent the game board as a 2D ArrayList of FishTile, stored in the FishModel, each fishtile will have their specific parameters like a boolean value that determines if its empty, a x position, a y position and a number that represents the number of fish in that specific tile. Players can use the getBoardCopy( ) method to get the current game state (game board) to make plans. A referee can use isValidMove( ) to check if a incoming move is valid, and they can use the emptyTile( ) to make certain tiles holes. 

In the future, we probably want to store the board as JSON file that wraps a 2D array of data from fish tiles for a better data persistency.

    Java: ArrayList<ArrayList<fishTile>> <--> JSON: {[[fishTileData], [fishTileData], [fishTileData], ...]}
Then we may also have another JSON file that has a list of all the players within the tournament that is only visible and updatable by the referee. The list will contain all players with the ages of the players, the colors of their penguins, the number of fish that they've taken. We'll continously add up all the fish that each player has taken and determine which of them are the winner. 

    Java: ArrayList<Map<PlayerID, PlayerData>> <--> JSON: [{PlayerID : [data1, data2, data3, ...]}, ...]

####External Interface:
In order to implement this tournament, a standard external interface would be used by each player to parse the JSON game state on their local machines, and a standard method will send each of the player's movement attempts back and be determined by the referee. Each succeed move that they've taken will then update the JSON game state file and will contiously update until there is a clear winner. The winner determination method will be created in the FishModel class, which will be called every time when there is an update to the game state.
