package admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.models.actions.IAction;
import common.models.actions.PlacePenguinAction;
import common.models.FishModel;
import common.models.FishState;
import common.models.PenguinColor;
import common.models.PlayerInfo;
import player.IPlayer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.*;

public class Referee {

    private final ArrayList<IPlayer> originalPlayers;
    private FishModel currentModel;
    private FishState currentState;
    private GamePhase gamePhase;
    private GameReport gameReport;

    public Referee(ArrayList<IPlayer> players) {
        players.sort(Comparator.comparingInt(p -> p.getInfoCopy().getAge()));
        this.originalPlayers = setUpPlayerColors(players);
        this.gamePhase = GamePhase.setup;
        this.gameReport = new GameReport();
    }

    private ArrayList<IPlayer> setUpPlayerColors(ArrayList<IPlayer> players) throws IllegalArgumentException{
        if (players.size() < 2 || players.size() > 4) {
            throw new IllegalArgumentException("Error: Invalid number of players for constructing a game.");
        }
        ArrayList<PenguinColor> colors = new ArrayList<>();
        colors.add(PenguinColor.red);
        colors.add(PenguinColor.black);
        colors.add(PenguinColor.white);
        colors.add(PenguinColor.brown);

        for (int i = 0; i < players.size(); ++i) {
            IPlayer player = players.get(i);
            PlayerInfo playerInfo = player.getInfoCopy();
            Gson gson = new Gson();
            Type infoType = new TypeToken<PlayerInfo>() {}.getType();
            PlayerInfo infoCopy = gson.fromJson(gson.toJson(playerInfo), infoType);

            infoCopy.setPenguinColor(colors.get(i));
            player.setInfoCopy(infoCopy);
        }
        return players;
    }


    public void setUpModel(int width, int height, int minOneFishNumOrFishNumOnTiles, boolean isRandomized) {
        if (gamePhase.equals(GamePhase.setup)) {
            this.currentModel = new FishModel(width, height, minOneFishNumOrFishNumOnTiles, isRandomized);
        }
    }

    public void createHole(int xPos, int yPos) {
        if (gamePhase.equals(GamePhase.setup) && currentModel != null) {
            currentModel.createHole(xPos, yPos);
        }
    }

    public void setUpInitialState() {
        if (gamePhase.equals(GamePhase.setup) && currentModel != null) {
            ArrayList<PlayerInfo> playerInfos = new ArrayList<>();
            for (IPlayer player : originalPlayers) {
                PlayerInfo playerInfo = player.getInfoCopy();
                playerInfos.add(playerInfo);
            }
            currentState = new FishState(currentModel, playerInfos);
            gamePhase = GamePhase.placing;
        }
    }

    public GameReport startGameTillTheEnd() {
        askPlayersToPlacePenguin();
        askPlayersToMovePenguin();
        return gameReport;
    }


    private void askPlayersToPlacePenguin() {
        while (gamePhase.equals(GamePhase.placing)) {

            executeActionFromPlayer(gamePhase);

//            if(gamePhase.equals(GamePhase.over)){
//                break;
//            }
            if (currentState.areAllPenguinsPlaced()) {
                gamePhase = GamePhase.moving;
            }
        }
        findWinnerIfGameOver();
    }

    private void askPlayersToMovePenguin(){
        while (gamePhase.equals(GamePhase.moving)){

            executeActionFromPlayer(gamePhase);
            findWinnerIfGameOver();
        }
    }

    private void findWinnerIfGameOver(){
        if(currentState.isGameOver()){
            gamePhase = GamePhase.over;

            ArrayList<PlayerInfo> playerInfos = currentState.getAllPlayerInfos();
            if(!playerInfos.isEmpty()) {
                Comparator<PlayerInfo> comparator = Comparator.comparing(PlayerInfo::getTotalFish);
                int maxFish = Collections.max(playerInfos, comparator).getTotalFish();

                for (PlayerInfo playerInfo : playerInfos) {
                    IPlayer player = findPlayerByInfo(playerInfo);
                    if (player.getInfoCopy().getTotalFish() == maxFish) {
                        gameReport.addWinningPlayer(player);
                    } else {
                        gameReport.addFailingPlayer(player);
                    }
                }
            }
        }
    }

    private void executeActionFromPlayer(GamePhase gamePhase){
        int currentPlayerIndex = currentState.getCurrentPlayerIndex();
        PlayerInfo playerInfo = currentState.getAllPlayerInfos().get(currentPlayerIndex);
        IPlayer player = findPlayerByInfo(playerInfo);


        final Runnable performAction = new Thread(() -> {
            if(gamePhase.equals(GamePhase.placing)) {
                FishState stateCopy = createStateCopy(currentState);
                PlacePenguinAction action = player.getPlacePenguinAction(stateCopy);
                currentState = action.performAction(currentState);
                currentModel = currentState.getFishModel();
                updatePlayerInfoCopy(player);
            } else if(gamePhase.equals(GamePhase.moving)){
                FishState stateCopy = createStateCopy(currentState);
                IAction action = player.getMovePenguinAction(stateCopy);
                currentState = action.performAction(currentState);
                currentModel = currentState.getFishModel();
                updatePlayerInfoCopy(player);
            }
        });

        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future future = executor.submit(performAction);
        executor.shutdown(); // This does not cancel the already-scheduled task.

        try {
            future.get(15, TimeUnit.SECONDS);
        } catch (InterruptedException  | TimeoutException e) {

            currentState = currentState.removeCurrentPlayerInfo();
            gameReport.addFailingPlayer(player);

        } catch (ExecutionException e) {

            currentState = currentState.removeCurrentPlayerInfo();
            gameReport.addCheatingPlayer(player);

        }
    }

    private FishState createStateCopy(FishState fishState){
        Gson gson = new Gson();
        Type stateType = new TypeToken<FishState>() {}.getType();
        FishState stateCopy = gson.fromJson(gson.toJson(fishState), stateType);
        return stateCopy;
    }

    private void updatePlayerInfoCopy(IPlayer player){
        PenguinColor penguinColor = player.getInfoCopy().getPenguinColor();
        PlayerInfo playerInfo = currentState.getPlayerInfo(penguinColor);

        Gson gson = new Gson();
        Type infoType = new TypeToken<PlayerInfo>() {}.getType();
        PlayerInfo infoCopy = gson.fromJson(gson.toJson(playerInfo), infoType);

        player.setInfoCopy(infoCopy);
    }

    private IPlayer findPlayerByInfo(PlayerInfo info) {
        PenguinColor penguinColor = info.getPenguinColor();
        for (IPlayer player : originalPlayers) {
            if (player.getInfoCopy().getPenguinColor().equals(penguinColor)) {
                return player;
            }
        }
        throw new IllegalArgumentException("Error: no player found with the info");
    }


    public ArrayList<IPlayer> getOriginalPlayers() {
        return originalPlayers;
    }


    public FishModel getCurrentModel() {
        return currentModel;
    }

    public FishState getCurrentState() {
        return currentState;
    }


    public GamePhase getGamePhase() {
        return gamePhase;
    }


    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    public GameReport getGameReport() {
        return gameReport;
    }


//    public void performAction(IPlayer player, IAction action) {
//        if (gamePhase) {
//            try {
//                currentState = action.performAction(currentState);
//            } catch (IllegalArgumentException e) {
//
//            }
//            //todo update each players' stateCopy
//            //todo update the player's infoCopy (score, etc.)
//        }
//    }


}
