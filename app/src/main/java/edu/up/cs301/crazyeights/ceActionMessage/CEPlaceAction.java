package edu.up.cs301.crazyeights.ceActionMessage;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

/**
 * A game-move object that a Crazy Eights player sends to the game to make
 * a move.
 *
 * @author Ronnie Delos Santos
 * @version November 2022
 */
public class CEPlaceAction extends GameAction {
    private CECard selectedCard;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CEPlaceAction(GamePlayer player, CECard selectedCard) {
        super(player);
        this.selectedCard = new CECard(selectedCard);
        player.removeCardInHand(selectedCard);
    }

    public CECard getSelectedCard() {
        return this.selectedCard;
    }
}
