package edu.up.cs301.crazyeights.players;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.crazyeights.ceActionMessage.CEDrawAction;
import edu.up.cs301.crazyeights.ceActionMessage.CEPlaceAction;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.infoMessage.IllegalMoveInfo;
import edu.up.cs301.game.GameFramework.infoMessage.NotYourTurnInfo;
import edu.up.cs301.game.GameFramework.players.GameComputerPlayer;

/**
 * This class represents the DumbAI player. The AI for
 * this class is not very sophisticated. The decision-making process
 * for the AI is iterating through the player's hand starting from index 0
 * and finding the first eligible card to be placed into the discardPile.
 * Furthermore, it has a 25% of drawing.
 *
 * @author Ronnie Delos Santos
 * @author Emily Do
 * @author Noelle Lei Sam
 * @author Alex Melamai
 * @version November 2022
 */
public class CEDumbAI extends GameComputerPlayer {
    private ArrayList<CECard> cardsInHand;
    private int score;

    /**
     * Generic constructor. Assigns the instance variable cardsInHand
     * and score a value.
     *
     * @param name the player's name (e.g., "John")
     */
    public CEDumbAI(String name) {
        super(name);
        this.cardsInHand = new ArrayList<>();
        this.score = 0;
    }

    /**
     * Getter for instance variable score.
     *
     * @return the score of this player
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for instance variable score.
     * @param newScore the score to assign
     */
    public void setScore(int newScore) {
        this.score = score + newScore;
    }

    /**
     * This controls what decision the AI is going to make
     * and sends and action to the game.
     * @param info GameInfo object sent from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof NotYourTurnInfo || info instanceof IllegalMoveInfo) return;

        CEGameState ceGameState = new CEGameState((CEGameState) info);

        if (ceGameState.getPlayerToMove() != this.playerNum || this.cardsInHand.size() == 0) {
            return;
        } else {
            // 1/4 chance of AI drawing
            if (new Random().nextInt(4) == 0) {
                Log.i("Player Action", "Dumb AI" + this.playerNum + " drawing");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
//            e.printStackTrace();
                }
                game.sendAction(new CEDrawAction(this));
            } else {
                for (int i = 0; i < this.cardsInHand.size(); i++) {
                    if (ceGameState.checkCardEligibility(this.cardsInHand.get(i))) {
                        CECard randomCard = this.cardsInHand.get(i);
                        Log.i("Player Action", "DUMB AI " + this.playerNum + " playing " + randomCard.face.name() + " " + randomCard.suit);
                        game.sendAction(new CEPlaceAction(this, randomCard));
                        return;
                    }
                }
                Log.i("Player Action", "Dumb AI" + this.playerNum + " drawing");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
//            e.printStackTrace();
                }
                game.sendAction(new CEDrawAction(this));
            }
        }
    }

    /**
     * Getter for this player's hand.
     * @return the hand of this player
     */
    @Override
    public ArrayList<CECard> getCardsInHand() {
        return this.cardsInHand;
    }

    /**
     * Adds a card to this player's hand.
     * @param card the card to be added
     * @return the card that was added
     */
    @Override
    public CECard addCardInHand(CECard card) {
        this.cardsInHand.add(card);
        return card;
    }

    /**
     * Clears the player's hand.
     */
    public void setCardsInHand(){
        this.cardsInHand.clear();
    }

    @Override
    public String getName() {
        return this.name;
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
