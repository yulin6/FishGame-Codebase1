package player;

import common.models.*;
import common.models.actions.IAction;
import common.models.actions.MiniMaxAction;
import common.models.actions.MovePenguinAction;
import common.models.actions.SkipTurnAction;

import java.util.*;

/**
 * A strategy class is a strategy to do two functionality, first is for the penguin placement
 * and second is to pick an action for a player. The penguin placement is done through doing a
 * zig-zag search on the board and returning a position of the penguin. The second functionality,
 * uses a minimax optimization in order to pick the best action to move their penguin
 * after placing them on the board.
 */
public class Strategy {

    /**
     * A method that outputs a position to place a penguin following a zig-zag pattern
     * through out the board. The search goes from the left to right in each row and moves
     * down to the next row when one is filled up.
     *
     * @param state the state of current game.
     * @return a position to place a penguin following a zig-zag search.
     * @throws IllegalArgumentException if the board is not large enough for the number
     *                                  of penguin.
     */
    public Position nextZigZagPlacement(FishState state) throws IllegalArgumentException {
        if(state.areAllPenguinsPlaced()){
            throw new IllegalArgumentException("Error: no more penguins can be placed");
        }

        ArrayList<ArrayList<Tile>> board = state.getBoard();
        for (int i = 0; i < board.size(); ++i) {
            for (int j = 0; j < board.get(i).size(); ++j) {
                Tile tile = board.get(i).get(j);
                if (!tile.isHole() && tile.getPenguin() == null) {
                    Position place = new Position(j, i);
                    return place;
                }
            }
        }
        throw new IllegalArgumentException("Error: Referee did not set up a large enough board.");
    }

    public IAction findMinimaxAction(FishTreeNode currentTreeNode, int nTurn, PenguinColor maximizingPlayerColor) throws IllegalArgumentException{

        if(currentTreeNode != null) {
            AbstractMap.SimpleEntry<FishTreeNode, Integer> minimaxGainNode = findMinimaxGainNode(currentTreeNode, nTurn, maximizingPlayerColor);
            FishTreeNode childNode = minimaxGainNode.getKey();
            if(childNode != null) {
                return findActionBetweenNodes(currentTreeNode, childNode);
            } else {
                return new SkipTurnAction();
                //invalid
//                return new MovePenguinAction(-1, -1, -1, -1);
            }
        } else throw new IllegalArgumentException("Error: Input tree node cannot be null");


    }



    private AbstractMap.SimpleEntry<FishTreeNode, Integer> findMinimaxGainNode(FishTreeNode currentTreeNode, int nTurn, PenguinColor maximizingPlayerColor) throws IllegalArgumentException{
        FishState currentState = currentTreeNode.getCurrentState();
        ArrayList<PlayerInfo> allPlayersInfo = currentState.getAllPlayerInfos();

        int currentPlayerIndex = currentState.getCurrentPlayerIndex();
        PlayerInfo currentPlayerInfo = allPlayersInfo.get(currentPlayerIndex);
        PenguinColor currentPlayerColor = currentPlayerInfo.getPenguinColor();

        if (nTurn < 0) {
            throw new IllegalArgumentException("Error: N turn cannot be less than 0.");
        } else if (nTurn == 0 || currentTreeNode.getDirectReachableStates().isEmpty()) {
            return new AbstractMap.SimpleEntry<>(null, currentTreeNode.getCurrentState().getPlayerGain(maximizingPlayerColor));
        } else if (currentPlayerColor.equals(maximizingPlayerColor)){
            AbstractMap.SimpleEntry<FishTreeNode, Integer> max = new AbstractMap.SimpleEntry<>(null, -1);
            currentTreeNode.generateChildNodes();
            for(FishTreeNode childNode : currentTreeNode.getChildNodes()){
                if(max.getValue() < findMinimaxGainNode(childNode, nTurn - 1, maximizingPlayerColor).getValue()){
                    max = new AbstractMap.SimpleEntry<>(childNode, findMinimaxGainNode(childNode, nTurn - 1, maximizingPlayerColor).getValue());
                } else if (max.getValue() == findMinimaxGainNode(childNode, nTurn - 1, maximizingPlayerColor).getValue()){
                    MovePenguinAction action1 = findActionBetweenNodes(currentTreeNode, childNode);
                    MovePenguinAction action2 = findActionBetweenNodes(currentTreeNode, max.getKey());
                    MovePenguinAction theAction = tieBreaker(action1, action2);

                    FishState newState = currentTreeNode.applyActionToState(currentTreeNode, theAction);
                    FishTreeNode newNode = new FishTreeNode(currentTreeNode, newState);
                    max = new AbstractMap.SimpleEntry<>(newNode, max.getValue());
                }
            }
//            System.out.println("max: " + max.getValue());
            return max;
        } else {
            AbstractMap.SimpleEntry<FishTreeNode, Integer> min = new AbstractMap.SimpleEntry<>(null, Integer.MAX_VALUE);
            currentTreeNode.generateChildNodes();
            for(FishTreeNode childNode : currentTreeNode.getChildNodes()){
                if (min.getValue() > findMinimaxGainNode(childNode, nTurn, maximizingPlayerColor).getValue()){
                    min = new AbstractMap.SimpleEntry<>(childNode, findMinimaxGainNode(childNode, nTurn, maximizingPlayerColor).getValue());
                }
            }
//            System.out.println("min: " + min.getValue());
            return min;
        }
    }

    private MovePenguinAction findActionBetweenNodes(FishTreeNode currentTreeNode, FishTreeNode childNode) throws IllegalArgumentException{

            FishState currentState = currentTreeNode.getCurrentState();
            FishState childState = childNode.getCurrentState();
            PlayerInfo currentPlayer = currentState.getAllPlayerInfos().get(currentState.getCurrentPlayerIndex());
            PenguinColor playerColor = currentPlayer.getPenguinColor();

            ArrayList<Penguin> currentPenguins = currentState.getPenguins(playerColor);
            ArrayList<Penguin> childPenguins = childState.getPenguins(playerColor);

            for (int i = 0; i < currentPenguins.size(); ++i) {
                Penguin currentPenguin = currentPenguins.get(i);
                Penguin childPenguin = childPenguins.get(i);
                if (currentPenguin.getXPos() != childPenguin.getXPos() || currentPenguin.getYPos() != childPenguin.getYPos()) {
                    return new MovePenguinAction(currentPenguin.getXPos(), currentPenguin.getYPos(), childPenguin.getXPos(), childPenguin.getYPos());
                }
            }
            throw new IllegalArgumentException("Error: no possible action between two nodes");

    }

    private MovePenguinAction tieBreaker(MovePenguinAction action1, MovePenguinAction action2){
        ArrayList<MovePenguinAction> actions = new ArrayList<>();
        actions.add(action1);
        actions.add(action2);
        Comparator<MovePenguinAction> compForLowerPenguin =  Comparator.comparing(MovePenguinAction::getStartY).thenComparing(MovePenguinAction::getStartX);
        actions.sort(compForLowerPenguin);
        return actions.get(0);
    }


    //*************************************************************************//

    //***** ALL CODE BELOW ARE DEPRECATED, WRONG WAY TO FIND MINIMAX GAIN *****//

    //*************************************************************************//



    MiniMaxAction miniMaxAction = new MiniMaxAction();

    /**
     * A method that recursively goes through the currentTreeNode and picks and action that realizes
     * the best gain after looking ahead a specific nTurns for a certain maximizing player. The method
     * assumes that all opponents pick one of the moves that minimizes the maximizing player's
     * gain. When it is the maximizing player's turn, it will pick & execute the move action that can maximize the gain
     * and add the action to a MiniMaxAction. When they are minimizing players' turns, it will pick & execute the
     * move action that can minimize the maximizing player's gain, which is their common tile with the largest fish
     * number. Every time when there is a execution on an action, a recursive call will happen on the newly generated
     * tree. Until the nTurns goes to zero, it will out put the MiniMaxAction, which contains an arrayList of
     * MovePenguinActions.
     *
     * @param currentTreeNode       is the current representation of a tree of FishState and FishTreeNode.
     * @param nTurns                is the number of turns the maximizing players wants to make.
     * @param maximizingPlayerColor is the color associated with the maximizing player.
     * @return a MiniMaxAction an IAction that contains an ArrayList of MovePenguinActions the strategy
     * chooses. Player can use the MiniMaxAction to view the list of action they can make to minimax their gain.
     */
    public MiniMaxAction minimaxGain(FishTreeNode currentTreeNode, int nTurns, PenguinColor maximizingPlayerColor) {
        FishState currentState = currentTreeNode.getCurrentState();
        ArrayList<PlayerInfo> allPlayerInfos = currentState.getAllPlayerInfos();

        int currentPlayerIndex = currentState.getCurrentPlayerIndex();
        PlayerInfo currentPlayerInfo = allPlayerInfos.get(currentPlayerIndex);
        PenguinColor currentPlayerColor = currentPlayerInfo.getPenguinColor();


        if (nTurns < 0) {
            throw new IllegalArgumentException("Error: N turn cannot be less than 0.");
        } else if (nTurns == 0 || currentTreeNode.getDirectReachableStates().isEmpty()) {
            return miniMaxAction;
        } else if (currentPlayerColor.equals(maximizingPlayerColor)) {
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


    /**
     * findPlayersPossibleMoves is a method that creates a Map of Tile and position based on the
     * current fish state and the penguin color of a certain player. The tile is the tiles that a penguin
     * can move to, a position is the position of the penguin. When there are multiple penguins can move to the same
     * tile, the method only stores one of them.
     *
     * @param currentState is the current state of the game.
     * @param playerColor  is the color of the player.
     * @return A map of Tile and Position, in which the tile is the tiles that a penguin can go and
     * the position is the position of the penguin.
     */
    private Map<Tile, Position> findPlayersPossibleMoves(FishState currentState, PenguinColor playerColor) {

        FishModel currentModel = currentState.getFishModel();
        ArrayList<Penguin> playersPenguins = currentState.getPenguins(playerColor);
        Map<Tile, Position> map = new HashMap<>();

        for (Penguin penguin : playersPenguins) {
            int xPos = penguin.getXPos();
            int yPos = penguin.getYPos();
            Position penguinPosition = new Position(xPos, yPos);
            ArrayList<Tile> possibleMoveTiles = currentModel.getPossibleMoves(xPos, yPos);
            for (Tile tile : possibleMoveTiles) {
                map.putIfAbsent(tile, penguinPosition);
            }
        }

        return map;
    }


    /**
     * findMaximizingMove is a function that finds a MovePenguinAction that maximizes the fishNum
     * of the maximizing player. It uses the currentState of the game as a way to find
     * the movement of penguin that maximizes its fishNum gathered by taking into account
     * the lowest row and lowest column number (the Tie Breaker) that it needs to move.
     *
     * @param currentState is the current state of the game.
     * @return A MovePenguinAction that maximizes the fishNum gathered, while minimizing the
     * amount of tiles it needs to move.
     */
    private MovePenguinAction findMaximizingMove(FishState currentState) {

        ArrayList<PlayerInfo> allPlayerInfos = currentState.getAllPlayerInfos();

        int currentPlayerIndex = currentState.getCurrentPlayerIndex();
        PlayerInfo currentPlayerInfo = allPlayerInfos.get(currentPlayerIndex);
        PenguinColor currentPlayerColor = currentPlayerInfo.getPenguinColor();

        ArrayList<Penguin> currentPlayerPenguins = currentState.getPenguins(currentPlayerColor);
        Comparator<Penguin> rowAndColumnComparator = Comparator.comparing(Penguin::getYPos).thenComparing(Penguin::getXPos);
        currentPlayerPenguins.sort(rowAndColumnComparator);
        FishModel currentModel = currentState.getFishModel();

        ArrayList<Tile> maxTiles = new ArrayList<>();
        Comparator<Tile> fishNumComparator = Comparator.comparing(Tile::getFishNum);
        for (Penguin penguin : currentPlayerPenguins) {
            int xPos = penguin.getXPos();
            int yPos = penguin.getYPos();

            ArrayList<Tile> possibleMoves = currentModel.getPossibleMoves(xPos, yPos);
            if (!possibleMoves.isEmpty()) {
                Tile maxTile = Collections.max(possibleMoves, fishNumComparator);
                maxTiles.add(maxTile);
            } else {
                //To keep the index of elements in maxTiles aligned with the index of currentPlayerPenguins.
                Tile dummyTile = new Tile(-1);
                maxTiles.add(dummyTile);
            }
        }
        Tile maxTile = Collections.max(maxTiles, fishNumComparator);
        Position maxTilePosition = currentModel.findTilePosition(maxTile);
        int tileIndexInList = maxTiles.indexOf(maxTile);
        Penguin penguinMax = currentPlayerPenguins.get(tileIndexInList);

        MovePenguinAction maximizingMove = new MovePenguinAction(penguinMax.getXPos(), penguinMax.getYPos(), maxTilePosition.getX(), maxTilePosition.getY());
        return maximizingMove;
    }


    /**
     * findMinimizingMove finds a MovePenguinAction movement that minimizes the maximizing player's
     * fishNum gain. It goes through the possible moves of the maximizing player and minimizing players
     * and determines common possible moves these players share, by sorting the common possible moves
     * we'll find the action that minimizes the maximizing player's move.
     *
     * @param currentState          is the current state of the game.
     * @param maximizingPlayerColor is the color of the maximizing player.
     * @param currentPlayerColor    is the color of the current minimizing player.
     * @return a MovePenguinAction that minimizes the maximizing player's fishNum gain.
     */
    private MovePenguinAction findMinimizingMove(FishState currentState, PenguinColor maximizingPlayerColor, PenguinColor currentPlayerColor) {
        FishModel currentModel = currentState.getFishModel();

        Map<Tile, Position> maxPlayerPossibleMoves = findPlayersPossibleMoves(currentState, maximizingPlayerColor);
        Map<Tile, Position> minPlayerPossibleMoves = findPlayersPossibleMoves(currentState, currentPlayerColor);
        Map<Tile, Position> commonPossibleMoveTiles = new HashMap<>();

        for (Map.Entry<Tile, Position> entry : minPlayerPossibleMoves.entrySet()) {
            Tile key = entry.getKey();
            if (maxPlayerPossibleMoves.containsKey(key)) {
                commonPossibleMoveTiles.put(entry.getKey(), entry.getValue());
            }
        }

        Comparator<Tile> fishNumComparator = Comparator.comparing(Tile::getFishNum);
        if (commonPossibleMoveTiles.isEmpty()) {
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


}
