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

/**
 * A referee class contains a list of IPlayers who will be player the game. Each referee handles a game from the
 * beginning till the end. There will be four phases of a game, which are setup, placing, moving and over. During
 * the setup phase, a referee will assign a different color for each players, and sort them by their age ascending.
 * Then the referee can call setUpModel method to set up the board and createHole method to create holes on the board.
 * Once the referee is done with the board setup, they can call setUpInitialState to create a initial game state,
 * and then the game phase will be changed to placing. Then, referee can call startGameTillTheEnd method for running
 * the entire game automatically until the end, and later the game phase will be changed to moving when no more penguins
 * should be placed, or it will be changed to over when there is no more possible move for any of the penguins, any
 * players who cheat or did not respond in 15 seconds will be removed from the game state and be recorded in the
 * GameReport. Finally, it will output the game report when the game is over.
 * <p>
 * <p>
 * Note: referee ensures that the PlayerInfo in each player will be a copy, same for FishStates that will be passed in.
 */
public class Referee {

    private GamePhase gamePhase;
    private final ArrayList<IPlayer> originalPlayers; //the players who participated in the game.
    private FishModel currentModel;
    private FishState currentState;
    private GameReport gameReport;

    /**
     * the constructor of the Referee class, which takes in a list of IPlayers, the order of players will be sorted by
     * age ascending, and players will also be assigned with a different color.
     *
     * @param players a ArrayList of IPlayer who will be in the same game.
     */
    public Referee(ArrayList<IPlayer> players) {
        this.gamePhase = GamePhase.setup;
        players.sort(Comparator.comparingInt(p -> p.getInfoCopy().getAge()));
        this.originalPlayers = setUpPlayerColors(players);
        this.gameReport = new GameReport();
    }

    /**
     * the private helper method of the constructor for assigning a different color for each player.
     *
     * @param players a ArrayList of IPlayer who will be in the same game.
     * @return the ArrayList of IPlayer which colors are assigned.
     * @throws IllegalArgumentException when the number of players is less than 2 or greater than 4.
     */
    private ArrayList<IPlayer> setUpPlayerColors(ArrayList<IPlayer> players) throws IllegalArgumentException {
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
            Type infoType = new TypeToken<PlayerInfo>() {
            }.getType();
            PlayerInfo infoCopy = gson.fromJson(gson.toJson(playerInfo), infoType);

            infoCopy.setPenguinColor(colors.get(i));
            player.setInfoCopy(infoCopy);
        }
        return players;
    }


    /**
     * When the game phase is setup, this method will be used for constructing a model which contains the game board.
     * <p>
     * Below is the purpose statement of FishModel constructor:
     * The main constructor of the fish model. Width and height are used for determine the size of the
     * game board. When isRandomized is true, it means we are in a RANDOM mode, the third input:
     * minOneFishNumOrFishNumOnTiles represent the minimum number of 1-fish tiles on the board. When
     * isRandomized is false, it means we are in a NONRANDOM mode, the third input:
     * minOneFishNumOrFishNumOnTiles represent the number of fishes that will be displayed on each
     * tiles.
     *
     * @param width                         the width of the game board.
     * @param height                        the height of the game board.
     * @param minOneFishNumOrFishNumOnTiles represents different things based on the mode, please
     *                                      refer to the explanation above.
     * @param isRandomized                  is in RANDOM or NONRANDOM mode.
     */
    public void setUpModel(int width, int height, int minOneFishNumOrFishNumOnTiles, boolean isRandomized) {
        if (gamePhase.equals(GamePhase.setup)) {
            this.currentModel = new FishModel(width, height, minOneFishNumOrFishNumOnTiles, isRandomized);
        }
    }

    /**
     * when the currentModel has created, calling this method with a x and a y position of tile position on the
     * current board, the tile will be set to a hole.
     *
     * @param xPos x position of tile position on the current board
     * @param yPos y position of tile position on the current board
     */
    public void createHole(int xPos, int yPos) {
        if (gamePhase.equals(GamePhase.setup) && currentModel != null) {
            currentModel.createHole(xPos, yPos);
        }
    }

    /**
     * when the currentModel has created, calling this method will set up the initial game state of the game, and
     * set the game phase to placing, which means it is ready for penguin placements.
     */
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

    /**
     * the main method of a referee, if the game phase is placing, calling this method will start the game
     * automatically till the end. Winning, cheating, failing players will be recorded in the gameReport and be
     * output when the game is over.
     *
     * @return a GameReport class which contains all the winning, cheating, failing players in the game.
     */
    public GameReport startGameTillTheEnd() {
        if (gamePhase.equals(GamePhase.placing)) {
            askPlayersToPlacePenguin();
            askPlayersToMovePenguin();
        }
        return gameReport;
    }


    /**
     * the private helper method would be called in startGameTillTheEnd() for asking players the positions to place
     * their penguins, and referee will place them if they are valid. Any player who cheat or did not respond in 15
     * seconds will be removed from the game state and recorded in the game report. When the placing phase is finished,
     * it will be changed to moving phase first, and if the game is over, the phase will be changed to over, and
     * the winning and failing players will be recorded in the gameReport.
     */
    private void askPlayersToPlacePenguin() {
        while (gamePhase.equals(GamePhase.placing)) {

            executeActionFromPlayer(gamePhase);

            if (currentState.areAllPenguinsPlaced()) {
                gamePhase = GamePhase.moving;
            }
        }
        findWinnerIfGameOver();
    }

    /**
     * the private helper method would be called in startGameTillTheEnd() for asking players the positions to move
     * their penguins, and referee will move them if they are valid. Any player who cheat or did not respond in 15
     * seconds will be removed from the game state and recorded in the game report. After each player turn, it will
     * check whether the game is over, if it is over, the phase will be changed to over, and  the winning and
     * failing players will be recorded in the gameReport.
     */
    private void askPlayersToMovePenguin() {
        while (gamePhase.equals(GamePhase.moving)) {

            executeActionFromPlayer(gamePhase);
            findWinnerIfGameOver();
        }
    }

    /**
     * the private helper method would be called for checking if the game is over, if it is over, the gamePhase will
     * be set to over, and  the winning and failing players will be recorded in the gameReport.
     */
    private void findWinnerIfGameOver() {
        if (currentState.isGameOver()) {
            gamePhase = GamePhase.over;

            ArrayList<PlayerInfo> playerInfos = currentState.getAllPlayerInfos();
            if (!playerInfos.isEmpty()) {
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

    /**
     * The private helper method would be called by askPlayersToPlacePenguin() and askPlayersToMovePenguin() to
     * communicate with the current player. It will ask them for the action that they want to make, an action can be
     * PlacePenguinAction when the gamePhase is placing, and it can be MovePenguinAction or SkipTurnAction when
     * the gamePhase is moving. Then it will perform the received action for the player. If a player cheat or did not
     * respond in 15 seconds, they will be removed from the game state and recorded in the gameReport.
     *
     * @param gamePhase the current phase of the game.
     */
    private void executeActionFromPlayer(GamePhase gamePhase) {
        int currentPlayerIndex = currentState.getCurrentPlayerIndex();
        PlayerInfo playerInfo = currentState.getAllPlayerInfos().get(currentPlayerIndex);
        IPlayer player = findPlayerByInfo(playerInfo);


        final Runnable performAction = new Thread(() -> {
            if (gamePhase.equals(GamePhase.placing)) {
                FishState stateCopy = createStateCopy(currentState);
                PlacePenguinAction action = player.getPlacePenguinAction(stateCopy);
                currentState = action.performAction(currentState);
                currentModel = currentState.getFishModel();
                updatePlayerInfoCopy(player);
            } else if (gamePhase.equals(GamePhase.moving)) {
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
        } catch (InterruptedException | TimeoutException e) {

            currentState = currentState.removeCurrentPlayerInfo();
            gameReport.addFailingPlayer(player);

        } catch (ExecutionException e) {

            currentState = currentState.removeCurrentPlayerInfo();
            gameReport.addCheatingPlayer(player);

        }
    }

    /**
     * the private helper method creates a copy of the given FishState by the GSON library.
     *
     * @param fishState the FishState that will be copied.
     * @return the deep copied FishState.
     */
    private FishState createStateCopy(FishState fishState) {
        Gson gson = new Gson();
        Type stateType = new TypeToken<FishState>() {
        }.getType();
        FishState stateCopy = gson.fromJson(gson.toJson(fishState), stateType);
        return stateCopy;
    }

    /**
     * the private helper method updates the PlayerInfo in the given player from the most recent FishState. The
     * updated PlayerInfo will be a copy, which prevents IPlayers from modifying the actual PlayerInfo.
     *
     * @param player
     */
    private void updatePlayerInfoCopy(IPlayer player) {
        PenguinColor penguinColor = player.getInfoCopy().getPenguinColor();
        PlayerInfo playerInfo = currentState.getPlayerInfo(penguinColor);

        Gson gson = new Gson();
        Type infoType = new TypeToken<PlayerInfo>() {
        }.getType();
        PlayerInfo infoCopy = gson.fromJson(gson.toJson(playerInfo), infoType);

        player.setInfoCopy(infoCopy);
    }

    /**
     * the private helper method takes in a PlayerInfo and find the IPlayer that matches the info.
     *
     * @param info the playerInfo used for identifying the IPlayer.
     * @return the IPlayer that matches the given PlayerInfo.
     */
    private IPlayer findPlayerByInfo(PlayerInfo info) {
        PenguinColor penguinColor = info.getPenguinColor();
        for (IPlayer player : originalPlayers) {
            if (player.getInfoCopy().getPenguinColor().equals(penguinColor)) {
                return player;
            }
        }
        throw new IllegalArgumentException("Error: no player found with the info");
    }


    /**
     * get the original players in the game.
     *
     * @return ArrayList of IPlayers who participated in the game.
     */
    public ArrayList<IPlayer> getOriginalPlayers() {
        return originalPlayers;
    }


    /**
     * get the current model of the game.
     *
     * @return the FishModel of the game which contains the the game board and some logic.
     */
    public FishModel getCurrentModel() {
        return currentModel;
    }

    /**
     * get the current state of the game.
     *
     * @return the FishState of the game.
     */
    public FishState getCurrentState() {
        return currentState;
    }


    /**
     * get the current phase of the game.
     *
     * @return the current phase of the game.
     */
    public GamePhase getGamePhase() {
        return gamePhase;
    }


    /**
     * set the current phase of the game with the given gamePhase.
     *
     * @param gamePhase the gamePhase that will be set as the current gamePhase.
     */
    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    /**
     * get the current gameReport of the game.
     *
     * @return the current GameReport of the game.
     */
    public GameReport getGameReport() {
        return gameReport;
    }


}
