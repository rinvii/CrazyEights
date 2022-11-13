package edu.up.cs301.crazyeights.ceActionMessage;

import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

/**
 * A game-move object that a Crazy Eights player sends to the game to make
 * a move.
 *
 * @author Ronnie Delos Santos
 * @version November 2022
 */
public class CEDrawAction extends GameAction {
    //Tag for logging
    private static final String TAG = "TTTMoveAction";
    private static final long serialVersionUID = -2242980258970485343L;

    /**
     * constructor for GameActio
     *
     * @param player the player who created the action
     */
    public CEDrawAction(GamePlayer player) {
        super(player);
    }
}
