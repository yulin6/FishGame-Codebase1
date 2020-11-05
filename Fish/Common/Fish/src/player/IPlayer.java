package player;

import common.models.actions.IAction;
import common.models.actions.PlacePenguinAction;
import common.models.FishState;
import common.models.PlayerInfo;

public interface IPlayer {

    PlacePenguinAction getPlacePenguinAction(FishState fishState) throws IllegalArgumentException;

    IAction getMovePenguinAction(FishState fishState) throws IllegalArgumentException;

    PlayerInfo getInfoCopy();

    void setInfoCopy(PlayerInfo infoCopy);

//    FishState getStateCopy();
//
//    void setStateCopy(FishState stateCopy);



}
