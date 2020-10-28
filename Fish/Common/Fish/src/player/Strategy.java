package player;

import common.models.*;
import common.models.Actions.IAction;
import common.models.Actions.MovePenguinAction;

import java.util.ArrayList;
import java.util.Comparator;

public class Strategy {

    public Position nextZigZagPlacement(FishState state) throws IllegalArgumentException{
        ArrayList<ArrayList<Tile>> board = state.getBoard();
        for(int i = 0; i < board.size(); ++i) {
            for(int j = 0; j < board.get(i).size(); ++j){
                Tile tile = board.get(i).get(j);
                if(!tile.isHole() && tile.getPenguin() == null) {
                    Position place = new Position(j, i);
                    return place;
                }
            }
        }
        throw new IllegalArgumentException("Error: Referee did not set up a large enough board.");
    }




    public MovePenguinAction minimaxGain(FishTreeNode tree, int nTurns, PenguinColor maximizingPlayerColor) {

        FishState currentState = tree.getCurrentState();
        ArrayList<Player> allPlayers = currentState.getPlayersSortedByAgeAscend();

//        int allPlayersNumber = allPlayers.size();

        int currentPlayerIndex = currentState.getCurrentPlayerIndex();
        Player currentPlayer = allPlayers.get(currentPlayerIndex);

        ArrayList<Penguin> currentPlayerPenguins = currentState.getCurrentPlayerPenguins();
        Comparator<Penguin> comp = Comparator.comparing(Penguin::getYPos).thenComparing(Penguin::getXPos);
        currentPlayerPenguins.sort(comp);
        FishModel currentModel = currentState.getFishModel();


        if(nTurns == 1){
            for (Penguin penguin: currentPlayerPenguins){
                int xPos = penguin.getXPos();
                int yPos = penguin.getYPos();

                ArrayList<Tile> possibleMoves = currentModel.getPossibleMoves(xPos, yPos);


            }
        }




    }




}
