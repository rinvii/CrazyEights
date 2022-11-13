package edu.up.cs301.crazyeights.players;

import java.util.ArrayList;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
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
}
