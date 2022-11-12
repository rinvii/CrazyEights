package edu.up.cs301.crazyeights.players;

import java.util.ArrayList;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.players.GameComputerPlayer;

public class CESmartAI extends GameComputerPlayer {
    private ArrayList<CECard> cardsInHand = new ArrayList<CECard>();

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public CESmartAI(String name) {
        super(name);
        this.cardsInHand = new ArrayList<>();
    }

    @Override
    protected void receiveInfo(GameInfo info) {

    }

    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * CEGamePlayer method implementations * * * * * * * * * * * * * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    @Override
    public ArrayList<CECard> getCardsInHand() {
        return cardsInHand;
    }

    @Override
    public CECard addCardInHand(CECard card) {
        this.cardsInHand.add(card);
        return card;
    }
}
