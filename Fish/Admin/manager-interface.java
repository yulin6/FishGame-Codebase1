
/**
 * Interface for Tournament Manager. Local variables are a list of GameObservers,
 * and a list of all PlayerInfos, which contains the age of a player.
 */
public interface ITournamentManager {

    /**
     * Gets the local ArrayList of PlayerInfo.
     * @return an ArrayList of PlayerInfo.
     */
    ArrayList<PlayerInfo> getPlayerInfo();
  
    /**
     * Gets the local ArrayList of Referees.
     * @return an ArrayList of Referee.
     */
    ArrayList<Referee> getReferees();
  
    /**
     * Gets the local ArrayList of GameObservers.
     * @return an ArrayList of GameObservers.
     */
    ArrayList<GameObserver> getGameObservers();
  
    /**
     * Adds a GameObserver to the ArrayList of GameObservers.
     * @param gameObserver a GameObserver class.
     */
    void addObserver(GameObserver gameObserver);
  
    /**
     * Removes a GameObserver in the ArrayList of GameObservers.
     * @param gameObserver a GameObserver class.
     */
    void removeObserver(GameObserver gameObserver);
  
    /**
     * Gets the local ArrayList of IPlayers.
     * @return an ArrayList of IPlayers.
     */
    ArrayList<IPlayer> getIPlayers();
  
    /**
     * Creates an ArrayList of IPlayers from the local ArrayList of PlayerInfo.
     * @return an ArrayList of IPlayers.
     */
    ArrayList<IPlayer> createPlayers();
  
    /**
     * Creates a game based of the IPlayer.
     * @param listOfPlayers an ArrayList of IPlayers.
     * @return an ArrayList of Referees
     */
    ArrayList<Referee> createReferees(ArrayList<IPlayer> listOfPlayers);
  
    /**
     * Gets the ArrayList of GameReports from an ArrayList of referees.
     * @param referees an ArrayList of Referees.
     * @return a GameReport of a specific game.
     */
    ArrayList<GameReport> getReportOfGames(ArrayList<Referee> referees);
  
    /**
     * Determines whether each game within the tournament, has
     * the game phase set to over in each referee class.
     * @return a boolean to determine if a round of games has finished.
     */
    boolean isRoundFinished();
  
    /**
     * When the round is finished, the tournament manager can get the ArrayList of winning IPlayers.
     * The tournament will continue to create new rounds until there is only one player standing or
     * the size of getWinningPlayers is equal to 1. Winning players' scores will set to zero
     * before next round.
     * @param gameReports an ArrayList of GameReports.
     * @return an ArrayList of IPlayers.
     */
    ArrayList<IPlayer> getWinningPlayers(ArrayList<GameReport> gameReports);
  
    /**
     * Sends an update of on-going action of a certain game in a string to GameObservers.
     * @param updateOnGame a string of on-going action.
     */
    void sendUpdateToGameOberver(String updateOnGame);
  }