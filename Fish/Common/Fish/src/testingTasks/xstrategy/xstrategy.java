package testingTasks.xstrategy;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import common.models.*;
import common.models.actions.IAction;
import common.models.actions.MovePenguinAction;
import common.models.actions.SkipTurnAction;
import player.Strategy;
import testingTasks.xstate.FishStateX;
import testingTasks.xstate.PlayerX;

import java.util.ArrayList;
import java.util.Scanner;

public class xstrategy {

    public static void main(String[] args) throws IllegalArgumentException {

        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        String jsonString = builder.toString();
        JsonArray jsonArray = new Gson().fromJson(jsonString, JsonArray.class);
        int depth = jsonArray.get(0).getAsInt();
        if(depth < 1 || depth > 2){
            throw new IllegalArgumentException("Error: Depth should either be 1 or 2.");
        }

        FishStateX stateX = new Gson().fromJson(jsonArray.get(1), FishStateX.class);
        ArrayList<PlayerX> playersXES = stateX.getPlayers();
        ArrayList<ArrayList<Integer>> boardX = stateX.getBoard();

        if (boardX.size() == 0) {
            throw new IllegalArgumentException("Error: no tiles on the board.");
        }

        int boardHeight = boardX.size();
        int boardWidth = boardX.get(0).size();
        int tilesNum = boardHeight * boardWidth;
        if (tilesNum > 25) {
            throw new IllegalArgumentException(
                    "Error: tiles number cannot be greater than 25 for the test.");
        }

        int maxFishNum = 4;
        boolean isRandom = false;
        FishModel fishModel = new FishModel(boardWidth, boardHeight, maxFishNum, isRandom);
        ArrayList<ArrayList<Tile>> board = fishModel.getBoard();
        for (int i = 0; i < boardX.size(); ++i) {
            for (int j = 0; j < boardX.get(i).size(); ++j) {
                Tile tile = board.get(i).get(j);
                int fishNum = boardX.get(i).get(j);
                if (fishNum == 0) {
                    tile.setToHole();
                } else {
                    tile.setFishNum(fishNum);
                }
            }
        }

        ArrayList<PlayerInfo> playerInfos = new ArrayList<>();
        for (PlayerX playerX : playersXES) {
            PlayerInfo playerInfo = new PlayerInfo(0, playerX.getColor());
            playerInfos.add(playerInfo);
        }

        FishState fishState = new FishState(fishModel, playerInfos);

        boolean isAllPenguinPlaced = false;
        int penguinIndex = 0;
        int roundForPenguinIndex = 0;
        while (!isAllPenguinPlaced) {
            for (int i = 0; i < playersXES.size(); ++i) {

                int currentPlayerIndex = fishState.getCurrentPlayerIndex();
                PlayerInfo currentPlayerInfo = fishState.getAllPlayerInfos().get(currentPlayerIndex);
                PlayerX playerX = playersXES.get(i);
                ArrayList<ArrayList<Integer>> places = playerX.getPlaces();

                ArrayList<Integer> place = places.get(penguinIndex);
                if (place.size() == 2) {
                    int xPos = place.get(1);
                    int yPos = place.get(0);

                    fishState = fishState.placeInitPenguin(xPos, yPos, currentPlayerInfo);

                    if (i == playersXES.size() - 1) {
                        ++penguinIndex;
                        if (roundForPenguinIndex != places.size() - 1) {
                            ++roundForPenguinIndex;
                        } else {
                            isAllPenguinPlaced = true;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Error: malformed position.");
                }
            }
        }

        FishTreeNode treeNode = new FishTreeNode(null, fishState);
        PlayerInfo nextPlayerInfo = fishState.getAllPlayerInfos().get(fishState.getCurrentPlayerIndex());
        PenguinColor maximizingPlayerColor = nextPlayerInfo.getPenguinColor();

        Strategy strategy = new Strategy();
        IAction action = strategy.findMinimaxAction(treeNode, depth, maximizingPlayerColor);

        if(action instanceof MovePenguinAction) {
//        MovePenguinAction movePenguinAction = strategy.findMinimaxAction(treeNode, depth, maximizingPlayerColor);

            MovePenguinAction movePenguinAction = (MovePenguinAction) action;
            int startX = movePenguinAction.getStartX();
            int startY = movePenguinAction.getStartY();
            int targetX = movePenguinAction.getTargetX();
            int targetY = movePenguinAction.getTargetY();


                ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
                ArrayList<Integer> from = new ArrayList<>();
                from.add(startY);
                from.add(startX);

                ArrayList<Integer> to = new ArrayList<>();
                to.add(targetY);
                to.add(targetX);

                ans.add(from);
                ans.add(to);

                String actionStr = new Gson().toJson(ans);
                System.out.println(actionStr);

        } else if(action instanceof SkipTurnAction){
            System.out.println("false");
        }



//        System.out.println(stateX.getPlayers().get(0).getColor());

    }

}
