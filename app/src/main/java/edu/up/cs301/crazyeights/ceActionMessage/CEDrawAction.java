package edu.up.cs301.crazyeights.ceActionMessage;

import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

public class CEDrawAction extends GameAction {

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CEDrawAction(GamePlayer player) {
        super(player);
    }
}
