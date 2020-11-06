package admin;

import common.models.FishState;
import common.models.PlayerInfo;
import common.models.actions.IAction;
import common.models.actions.PlacePenguinAction;
import player.IPlayer;

/**
 * the class mocks a cheating player, which will be used for testing purposes.
 */
public class CheatingPlayerMock implements IPlayer {

    private PlayerInfo infoCopy;


    public CheatingPlayerMock(PlayerInfo infoCopy){
        this.infoCopy = infoCopy;
    }

    @Override
    public PlacePenguinAction getPlacePenguinAction(FishState fishState) throws IllegalArgumentException {
        throw new IllegalArgumentException("I am a cheating player");
    }

    @Override
    public IAction getMovePenguinAction(FishState fishState) throws IllegalArgumentException {
        throw new IllegalArgumentException("I am a cheating player");
    }

    @Override
    public PlayerInfo getInfoCopy() {
        return infoCopy;
    }

    @Override
    public void setInfoCopy(PlayerInfo infoCopy) {
        this.infoCopy = infoCopy;
    }
}
