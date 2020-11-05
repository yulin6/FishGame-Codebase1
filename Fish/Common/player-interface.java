package player;

import common.models.actions.IAction;
import common.models.actions.PlacePenguinAction;
import common.models.FishState;
import common.models.PlayerInfo;

/**
 * The player interface. An implementation of it will contain a PlayerInfo parameter, which contains age, color,
 * score of the player. By calling the getPlacePenguinAction method, it will produce a PlacePenguinAction for
 * placing a penguin. By calling the getMovePenguinAction method, it will produce a MovePenguinAction for a penguin
 * movement or a SkipTurnAction when there is no more moves for the player's penguins.
 */
public interface IPlayer {

    /**
     * By calling the getPlacePenguinAction method, it will produce a PlacePenguinAction for placing a penguin base
     * on the given FishState.
     * @param fishState the FishState that will be used for checking the position to place a penguin.
     * @return a PlacePenguinAction for placing a penguin.
     * @throws IllegalArgumentException when the given fishState is invalid for finding a position to place penguin.
     */
    PlacePenguinAction getPlacePenguinAction(FishState fishState) throws IllegalArgumentException;

    /**
     * By calling the getMovePenguinAction method, it will produce a MovePenguinAction for a penguin
     * movement or a SkipTurnAction when there is no more moves for the player's penguins base on the given FishState.
     * @param fishState the FishState that will be used for checking the position to move a penguin.
     * @return a IACtion which can either be MovePenguinAction or SkipTurnAction.
     * @throws IllegalArgumentException when the given fishState is invalid for finding a position to move a penguin.
     */
    IAction getMovePenguinAction(FishState fishState) throws IllegalArgumentException;

    /**
     * get the PlayerInfo of the player, which contains age, color, score. Ideally, the playerInfo should always
     * be a copy.
     * @return the PlayerInfo of the player. 
     */
    PlayerInfo getInfoCopy();

    /**
     * set the PlayerInfo of the player with the given PlayerInfo. Ideally, the playerInfo should always
     * be a copy.
     * @param infoCopy the PlayerInfo what will be set to the player.
     */
    void setInfoCopy(PlayerInfo infoCopy);



}
