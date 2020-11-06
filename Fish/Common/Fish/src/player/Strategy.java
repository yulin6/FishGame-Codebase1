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
     *                                  of penguin, or no more penguins can be placed.
     */
    public Position nextZigZagPlacement(FishState state) throws IllegalArgumentException {
        if (state.areAllPenguinsPlaced()) {
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

    /**
     * findMinimaxAction takes in a tree node, a int n, and a maximizing player color, the tree node will be used
     * for traversing through all the possible states, the n turn number will represents the number of turns that
     * the player want to look ahead, and the maximizing player color is used for distinguishing the maximizing player.
     * The child node with the maximum gain will be found by findMinimaxGainNode method, once it is found, it will
     * use the findActionBetweenNodes method to produce the action that will minimax the player gain, the action
     * will either be a MovePenguinAction or a SkipTurnAction when there is no more possible move.
     *
     * @param currentTreeNode       the FishTreeNode that will be used for traversing through all the possible states.
     * @param nTurn                 n turn number will represents the number of turns that the player want to look ahead.
     * @param maximizingPlayerColor the maximizing player color is used for distinguishing the maximizing player.
     * @return an action that minimax the player gain.
     * @throws IllegalArgumentException when the input tree node is null or other exception from helper methods.
     */
    public IAction findMinimaxAction(FishTreeNode currentTreeNode, int nTurn, PenguinColor maximizingPlayerColor) throws IllegalArgumentException {

        if (currentTreeNode != null) {
            AbstractMap.SimpleEntry<FishTreeNode, Integer> minimaxGainNode = findMinimaxGainNode(currentTreeNode, nTurn, maximizingPlayerColor);
            FishTreeNode childNode = minimaxGainNode.getKey();
            if (childNode != null) {
                return findActionBetweenNodes(currentTreeNode, childNode);
            } else {
                return new SkipTurnAction();
            }
        } else throw new IllegalArgumentException("Error: Input tree node cannot be null");


    }


    /**
     * The helper method of findMinimaxAction traverse through the given FishTreeNode recursively to a map that contains
     * a maximum value which is the max gain, and its key stands for the next tree node that minimax the player gain.
     *
     * @param currentTreeNode       the FishTreeNode that will be used for traversing through all the possible states.
     * @param nTurn                 n turn number will represents the number of turns that the player want to look ahead.
     * @param maximizingPlayerColor the maximizing player color is used for distinguishing the maximizing player.
     * @return a map with a key stands for the next tree node that minimax the player gain, and the the value is the max gain.
     * @throws IllegalArgumentException when the input tree node is null or other exception from helper methods.
     */
    private AbstractMap.SimpleEntry<FishTreeNode, Integer> findMinimaxGainNode(FishTreeNode currentTreeNode, int nTurn, PenguinColor maximizingPlayerColor) throws IllegalArgumentException {
        FishState currentState = currentTreeNode.getCurrentState();
        ArrayList<PlayerInfo> allPlayersInfo = currentState.getAllPlayerInfos();

        int currentPlayerIndex = currentState.getCurrentPlayerIndex();
        PlayerInfo currentPlayerInfo = allPlayersInfo.get(currentPlayerIndex);
        PenguinColor currentPlayerColor = currentPlayerInfo.getPenguinColor();

        if (nTurn < 0) {
            throw new IllegalArgumentException("Error: N turn cannot be less than 0.");
        } else if (nTurn == 0 || currentTreeNode.getDirectReachableStates().isEmpty()) {
            return new AbstractMap.SimpleEntry<>(null, currentTreeNode.getCurrentState().getPlayerScore(maximizingPlayerColor));
        } else if (currentPlayerColor.equals(maximizingPlayerColor)) {
            AbstractMap.SimpleEntry<FishTreeNode, Integer> max = new AbstractMap.SimpleEntry<>(null, -1);
            currentTreeNode.generateChildNodes();
            for (FishTreeNode childNode : currentTreeNode.getChildNodes()) {
                if (max.getValue() < findMinimaxGainNode(childNode, nTurn - 1, maximizingPlayerColor).getValue()) {
                    max = new AbstractMap.SimpleEntry<>(childNode, findMinimaxGainNode(childNode, nTurn - 1, maximizingPlayerColor).getValue());
                } else if (max.getValue() == findMinimaxGainNode(childNode, nTurn - 1, maximizingPlayerColor).getValue()) {
                    MovePenguinAction action1 = findActionBetweenNodes(currentTreeNode, childNode);
                    MovePenguinAction action2 = findActionBetweenNodes(currentTreeNode, max.getKey());
                    MovePenguinAction theAction = tieBreaker(action1, action2);

                    FishState newState = currentTreeNode.applyActionToState(currentTreeNode, theAction);
                    FishTreeNode newNode = new FishTreeNode(currentTreeNode, newState);
                    max = new AbstractMap.SimpleEntry<>(newNode, max.getValue());
                }
            }
            return max;
        } else {
            AbstractMap.SimpleEntry<FishTreeNode, Integer> min = new AbstractMap.SimpleEntry<>(null, Integer.MAX_VALUE);
            currentTreeNode.generateChildNodes();
            for (FishTreeNode childNode : currentTreeNode.getChildNodes()) {
                if (min.getValue() > findMinimaxGainNode(childNode, nTurn, maximizingPlayerColor).getValue()) {
                    min = new AbstractMap.SimpleEntry<>(childNode, findMinimaxGainNode(childNode, nTurn, maximizingPlayerColor).getValue());
                }
            }
            return min;
        }
    }

    /**
     * the helper method finds the move action between two tree nodes by comparing each penguins on board and finds
     * the penguin that moved. Based on the positions before and after the move, constructs a MovePenguinAction.
     *
     * @param currentTreeNode the current FishTreeNode
     * @param childNode the child FishTreeNode
     * @return a MovePenguinAction for moving a penguin.
     * @throws IllegalArgumentException when there is no possible action between two node.
     */
    private MovePenguinAction findActionBetweenNodes(FishTreeNode currentTreeNode, FishTreeNode childNode) throws IllegalArgumentException {

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

    /**
     * the helper method works as a tie breaker, it compares the row and column number of penguins starting positions
     * in both MovePenguinActions, and returns the one that has lower row and column number.
     *
     * @param action1 the first action
     * @param action2 the second action
     * @return a MovePenguinAction that has lower row and column number of penguins starting positions.
     */
    private MovePenguinAction tieBreaker(MovePenguinAction action1, MovePenguinAction action2) {
        ArrayList<MovePenguinAction> actions = new ArrayList<>();
        actions.add(action1);
        actions.add(action2);
        Comparator<MovePenguinAction> compForLowerPenguin = Comparator.comparing(MovePenguinAction::getStartY).thenComparing(MovePenguinAction::getStartX);
        actions.sort(compForLowerPenguin);
        return actions.get(0);
    }


}
