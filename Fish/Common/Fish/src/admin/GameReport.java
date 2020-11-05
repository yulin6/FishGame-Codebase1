package admin;

import player.IPlayer;

import java.util.ArrayList;

/**
 * GameReport class stores all the winning, failing and cheating players on a game. It has getter and setter methods
 * for getting and generating the game report.
 */
public class GameReport {
    private ArrayList<IPlayer> winningPlayers;
    private ArrayList<IPlayer> failingPlayers;
    private ArrayList<IPlayer> cheatingPlayers;

    /**
     * the constructor of the GameReport class, which initiates the local player lists.
     */
    public GameReport(){
        this.winningPlayers = new ArrayList<>();
        this.failingPlayers = new ArrayList<>();
        this.cheatingPlayers = new ArrayList<>();
    }

    /**
     * get the winning players array list.
     * @return arrayList of winning IPlayers.
     */
    public ArrayList<IPlayer> getWinningPlayers() {
        return winningPlayers;
    }

    /**
     * add a winning player to the array list.
     * @param winningPlayer a IPlayer who won the game.
     */
    public void addWinningPlayer(IPlayer winningPlayer) {
        this.winningPlayers.add(winningPlayer);
    }

    /**
     * get the failing players array list.
     * @return arrayList of failing IPlayers.
     */
    public ArrayList<IPlayer> getFailingPlayers() {
        return failingPlayers;
    }

    /**
     * add a failing player to the array list.
     * @param failingPlayer a IPlayer who failed on the game.
     */
    public void addFailingPlayer(IPlayer failingPlayer) {
        this.failingPlayers.add(failingPlayer);
    }

    /**
     * get the cheating players array list.
     * @return a arraylist of cheating IPlayers.
     */
    public ArrayList<IPlayer> getCheatingPlayers() {
        return cheatingPlayers;
    }


    /**
     * add a cheating player to the array list.
     * @param cheatingPlayer a IPlayer who cheated on the game.
     */
    public void addCheatingPlayer(IPlayer cheatingPlayer) {
        this.cheatingPlayers.add(cheatingPlayer);
    }


}
