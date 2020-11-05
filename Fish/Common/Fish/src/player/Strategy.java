package player;

import common.models.*;
import common.models.actions.IAction;
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
            return new AbstractMap.SimpleEntry<>(null, currentTreeNode.getCurrentState().getPlayerScore(maximizingPlayerColor));
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




}
