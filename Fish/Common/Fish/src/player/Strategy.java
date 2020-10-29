package player;

import common.models.*;
import common.models.Actions.MiniMaxAction;
import common.models.Actions.MovePenguinAction;

import java.util.*;

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



    MiniMaxAction miniMaxAction = new MiniMaxAction();

    public MiniMaxAction minimaxGain(FishTreeNode currentTreeNode, int nTurns, PenguinColor maximizingPlayerColor) {
        FishState currentState = currentTreeNode.getCurrentState();
        ArrayList<Player> allPlayers = currentState.getPlayersSortedByAgeAscend();

        int currentPlayerIndex = currentState.getCurrentPlayerIndex();
        Player currentPlayer = allPlayers.get(currentPlayerIndex);
        PenguinColor currentPlayerColor = currentPlayer.getPenguinColor();


        if(nTurns < 0){
            throw new IllegalArgumentException("Error: N turn cannot be less than 0.");
        } else if (nTurns == 0 || currentTreeNode.getDirectReachableStates().isEmpty()){
            return miniMaxAction;
        } else if(currentPlayerColor.equals(maximizingPlayerColor)){
            MovePenguinAction maximizingMove = findMaximizingMove(currentState);
            miniMaxAction.addMovePenguinAction(maximizingMove);
            FishState maximizedState = currentTreeNode.applyActionToState(currentTreeNode, maximizingMove);
            FishTreeNode maximizedTreeNode = new FishTreeNode(currentTreeNode, maximizedState);
            //Recursive call
            return minimaxGain(maximizedTreeNode, nTurns - 1, maximizingPlayerColor);
        } else {
            MovePenguinAction movePenguinAction = findMinimizingMove(currentState, maximizingPlayerColor, currentPlayerColor);
            FishState minimizedState = currentTreeNode.applyActionToState(currentTreeNode, movePenguinAction);
            FishTreeNode minimizedTreeNode = new FishTreeNode(currentTreeNode, minimizedState);
            return minimaxGain(minimizedTreeNode, nTurns, maximizingPlayerColor);
        }

    }

    private MovePenguinAction findMinimizingMove(FishState currentState, PenguinColor maximizingPlayerColor, PenguinColor currentPlayerColor){
        FishModel currentModel = currentState.getFishModel();

        Map<Tile, Position> maxPlayerPossibleMoves = findPlayersPossibleMoves(currentState, maximizingPlayerColor);
        Map<Tile, Position> minPlayerPossibleMoves = findPlayersPossibleMoves(currentState, currentPlayerColor);
        Map<Tile, Position> commonPossibleMoveTiles = new HashMap<>();

        for (Map.Entry<Tile, Position> entry: minPlayerPossibleMoves.entrySet()){
            Tile key = entry.getKey();
            if(maxPlayerPossibleMoves.containsKey(key)){
                commonPossibleMoveTiles.put(entry.getKey(), entry.getValue());
            }
        }

        Comparator<Tile> fishNumComparator = Comparator.comparing(Tile::getFishNum);
        if(commonPossibleMoveTiles.isEmpty()){
            Tile maxTile = Collections.max(minPlayerPossibleMoves.keySet(), fishNumComparator);
            Position position = minPlayerPossibleMoves.get(maxTile);
            commonPossibleMoveTiles.put(maxTile, position);
        }

        Tile maxTile = Collections.max(commonPossibleMoveTiles.keySet(), fishNumComparator);
        Position maxFishTilePosition = currentModel.findTilePosition(maxTile);
        Position startPenguinPosition = minPlayerPossibleMoves.get(maxTile);
        MovePenguinAction movePenguinAction = new MovePenguinAction(startPenguinPosition.getX(), startPenguinPosition.getY(),
                maxFishTilePosition.getX(), maxFishTilePosition.getY());
        return movePenguinAction;
    }


    private Map<Tile, Position> findPlayersPossibleMoves(FishState currentState, PenguinColor playerColor){

        FishModel currentModel = currentState.getFishModel();
        ArrayList<Penguin> playersPenguins = currentState.getPlayerPenguins(playerColor);
        Map<Tile, Position> map = new HashMap<>();

        for(Penguin penguin: playersPenguins){
            int xPos = penguin.getXPos();
            int yPos = penguin.getYPos();
            Position penguinPosition = new Position(xPos, yPos);
            ArrayList<Tile> possibleMoveTiles = currentModel.getPossibleMoves(xPos, yPos);
            for (Tile tile: possibleMoveTiles){
                map.putIfAbsent(tile, penguinPosition);
            }
        }

        return map;
    }

    private Player findMaximizingPlayer(ArrayList<Player> allPlayers, PenguinColor maximizingPlayerColor){
        for(Player player: allPlayers){
            if(player.getPenguinColor().equals(maximizingPlayerColor)){
                return player;
            }
        }
        throw new IllegalArgumentException("Error: no player with the color.");
    }

    private MovePenguinAction findMaximizingMove(FishState currentState){

        ArrayList<Player> allPlayers = currentState.getPlayersSortedByAgeAscend();

        int currentPlayerIndex = currentState.getCurrentPlayerIndex();
        Player currentPlayer = allPlayers.get(currentPlayerIndex);
        PenguinColor currentPlayerColor = currentPlayer.getPenguinColor();

        ArrayList<Penguin> currentPlayerPenguins = currentState.getPlayerPenguins(currentPlayerColor);
        Comparator<Penguin> rowAndColumnComparator = Comparator.comparing(Penguin::getYPos).thenComparing(Penguin::getXPos);
        currentPlayerPenguins.sort(rowAndColumnComparator);
        FishModel currentModel = currentState.getFishModel();

        ArrayList<Tile> maxTilesUnsorted = new ArrayList<>();
        ArrayList<Tile> maxTilesSorted = new ArrayList<>();
        Comparator<Tile> fishNumComparator = Comparator.comparing(Tile::getFishNum);
        for (Penguin penguin: currentPlayerPenguins) {
            int xPos = penguin.getXPos();
            int yPos = penguin.getYPos();

            ArrayList<Tile> possibleMoves = currentModel.getPossibleMoves(xPos, yPos);
            Tile maxTile = Collections.max(possibleMoves, fishNumComparator);
            maxTilesUnsorted.add(maxTile);
            maxTilesSorted.add(maxTile);
        }
        maxTilesSorted.sort(fishNumComparator);
        Tile maxTile = maxTilesSorted.get(0);
        Position maxTilePosition = currentModel.findTilePosition(maxTile);
        int tileIndexInList = maxTilesUnsorted.indexOf(maxTile);
        Penguin penguinMax = currentPlayerPenguins.get(tileIndexInList);

        MovePenguinAction maximizingMove = new MovePenguinAction(penguinMax.getXPos(), penguinMax.getYPos(), maxTilePosition.getX(), maxTilePosition.getY());
        return maximizingMove;
    }


}
