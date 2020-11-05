package admin;

import player.IPlayer;

import java.util.ArrayList;

public class GameReport {
    private ArrayList<IPlayer> winningPlayers;
    private ArrayList<IPlayer> failingPlayers;
    private ArrayList<IPlayer> cheatingPlayers;

    public GameReport(){
        this.winningPlayers = new ArrayList<>();
        this.failingPlayers = new ArrayList<>();
        this.cheatingPlayers = new ArrayList<>();
    }

    public ArrayList<IPlayer> getWinningPlayers() {
        return winningPlayers;
    }

    public void addWinningPlayer(IPlayer winningPlayer) {
        this.winningPlayers.add(winningPlayer);
    }

    public ArrayList<IPlayer> getFailingPlayers() {
        return failingPlayers;
    }

    public void addFailingPlayer(IPlayer failingPlayer) {
        this.failingPlayers.add(failingPlayer);
    }

    public ArrayList<IPlayer> getCheatingPlayers() {
        return cheatingPlayers;
    }

    public void addCheatingPlayer(IPlayer cheatingPlayer) {
        this.cheatingPlayers.add(cheatingPlayer);
    }


}
