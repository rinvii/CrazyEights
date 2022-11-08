package edu.up.cs301.crazyeights.ceActionMessage;

import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

public class CEPlaceAction extends GameAction {

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CEPlaceAction(GamePlayer player) {
        super(player);
    }
}
