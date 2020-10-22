## Player Protocol:

In order for a player to move around in the tournament, players will have to communicate with the referee or the game logic in a round robin style based on their age. The movement will consist of requests that are communicated with the referee and players. The referee will send a request to the player to ask about the movement that they want to make, the player will then send a request to the referee through using these methods:

##### To move a penguin

    String sendMovementOfPenguin(String movement);
Input:

    {“starting pont”: [1, 2], “move”: [1 , 3]}
Output:

Current FishState (see an example later in the doc) or Error Message


##### To place a penguin

    String sendPlacementOfPenguin(String place);

Input: 

    {“place”: [1 , 2]}
    
Output:

    Current FishState or Error Message

Since the players do not have access to the actual classes and will only be able to communicate through the TCP server, they’ll need to provide movements in JSON formatted string. The referee will then output a JSON formatted string as a result of their movement through the TCP server. 

#####They will check their turn based on the method:

    String isMyTurn();

Output:

    True/False

##### To know the score that they’ve gained through out the game, they will use:

    String myScore();

Output:

    {“Score”: 1}

##### To know the future possible moves that they could take, they would invoke the:

    String generateTree(String currentState);

Output:

    {“current state”: FishState, 
       “parent node”: Tree: , 
     “list of nodes”: [{“current state”: FishState2, 
                          “parent node”: tree, 
                        “list of nodes”:[]}]}
##### Then to know the current game state, they would then use the:

    String getCopyOfCurrentFishState();
Output:

    {    "players": [{"color":"red",          
                     "score" :11,         
                     "places":[[ 2,  1], [1, 1] ] },       
                     {"color":black",     
                     "score" :12,          
                     "places":[[3, 1], [3, 0 ]]},       
                     {"color":"white",          
                      "score": 5,          
                      "places":[[1 , 0 ],[0  , 2]]}],   
            "board":[[0, 1, 1 ], [1, 5, 1], [3, 1, 1], [5, 1, 1]]
}
Through utilizing all of these methods shown above, they’ll be able to communicate with the referee and play the game. 
