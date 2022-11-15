package edu.up.cs301.crazyeights.players;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.crazyeights.ceActionMessage.CEDrawAction;
import edu.up.cs301.crazyeights.ceActionMessage.CEPlaceAction;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.infoMessage.NotYourTurnInfo;
import edu.up.cs301.game.GameFramework.players.GameComputerPlayer;

public class CEDumbAI extends GameComputerPlayer {
    private ArrayList<CECard> cardsInHand;

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public CEDumbAI(String name) {
        super(name);
        this.cardsInHand = new ArrayList<>();
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof NotYourTurnInfo) return;

        CEGameState ceGameState = new CEGameState((CEGameState) info);

        if (ceGameState.getPlayerToMove() != this.playerNum || this.cardsInHand.size() == 0) {
            return;
        } else {
            // 1/8 chance of AI drawing
            if (new Random().nextInt(8) == 0) {
                Log.i("Player Action", "Dumb AI" + this.playerNum + " drawing");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
//            e.printStackTrace();
                }
                game.sendAction(new CEDrawAction(this));
            } else {
                CECard randomCard = this.cardsInHand.get(new Random().nextInt(this.cardsInHand.size()));
                Log.i("Player Action", "DUMB AI " + this.playerNum + " playing " + randomCard.face.name() + " " + randomCard.suit);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
//            e.printStackTrace();
                }
                game.sendAction(new CEPlaceAction(this, randomCard));
            }
        }
    }

    @Override
    public ArrayList<CECard> getCardsInHand() {
        return this.cardsInHand;
    }

    @Override
    public CECard addCardInHand(CECard card) {
        this.cardsInHand.add(card);
        return card;
    }

    /**
     * Removes card in the hand.
     * @param card The card to be removed
     * @return The card that was removed
     */
    @Override
    public void removeCardInHand(CECard card) {
        this.cardsInHand.remove(card);
    }

}
