package edu.up.cs301.crazyeights.players;

import android.util.Log;

import java.util.ArrayList;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.crazyeights.ceActionMessage.CEDrawAction;
import edu.up.cs301.crazyeights.ceActionMessage.CEPlaceAction;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.infoMessage.IllegalMoveInfo;
import edu.up.cs301.game.GameFramework.infoMessage.NotYourTurnInfo;
import edu.up.cs301.game.GameFramework.players.GameComputerPlayer;

/**
 * This class represents the SmartAI player. The AI for
 * this class is very sophisticated. The decision-making process
 * for the SmartAI is iterating through the player's hand starting from index 0
 * and finding the highest eligible face value card to be placed into the discardPile.
 * The objective is to keep the cards with the smallest value (In the end, the player with the lowest score wins).
 *
 *  @author Ronnie Delos Santos
 *  @author Emily Do
 *  @author Noelle Lei Sam
 *  @author Alex Melamai
 *  @version November 2022
 */
public class CESmartAI extends GameComputerPlayer {
    private ArrayList<CECard> cardsInHand = new ArrayList<CECard>();
    private int score;

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public CESmartAI(String name) {
        super(name);
        this.cardsInHand = new ArrayList<>();
        this.score = 0;
    }

    /**
     * Gets and returns the player's score
     * @return Updated player score
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's score
     * @param NewScore
     */
    public void setScore(int NewScore) {
        this.score = score+NewScore;
    }

    /**
     * Clears the player's hand
     */
    public void setCardsInHand(){
        ArrayList<CECard> newCardsInHand=new ArrayList<CECard>();
        this.cardsInHand=newCardsInHand;
    }

    /**
     * Gets and returns the player's name
     * @return the player's name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Callback method, called when player gets a message
     * @param info the message
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof NotYourTurnInfo || info instanceof IllegalMoveInfo) return;

        CEGameState ceGameState = new CEGameState((CEGameState) info);

        if (ceGameState.getPlayerToMove() != this.playerNum || this.cardsInHand.size() == 0) {
            return;
        }
        else {
            CECard maxCard = null;
            int minMaxCard = 1;
            String str = "";

            for (int j = 0; j < this.cardsInHand.size(); j++) {
                String faceName = this.cardsInHand.get(j).face.name();
                String suitName = this.cardsInHand.get(j).suit.name();
                str += faceName + " " + suitName + " | ";
            }
            Log.e("Smart AI" + this.playerNum, str);

            for (int i = 0; i < this.cardsInHand.size(); i++) {
                if (ceGameState.checkCardEligibility(this.cardsInHand.get(i))) {
                    CECard currCard = this.cardsInHand.get(i);
                    int currVal = currCard.getScore();
                    if (currVal > minMaxCard) {
                        minMaxCard = currVal;
                        maxCard = this.cardsInHand.get(i);
                    } else {
                        maxCard = this.cardsInHand.get(i);
                        break;
                    }
                }
            }
            if (maxCard == null) {
                game.sendAction(new CEDrawAction(this));
                return;
            }
            game.sendAction(new CEPlaceAction(this, maxCard));
        }
    }


    /**
     * Gets and returns the player's cards
     * @return the cards in hand
     */
    @Override
    public ArrayList<CECard> getCardsInHand() {
        return this.cardsInHand;
    }

    /**
     * Adds cards to the player's hand
     * @param card the selected card
     * @return the selected card
     */
    @Override
    public CECard addCardInHand(CECard card) {
        this.cardsInHand.add(card);
        return card;
    }

    /**
     * Removes a card from the cards in hand.
     * @param card
     */
    @Override
    public void removeCardInHand(CECard card) {
        this.cardsInHand.remove(card);
    }
}
