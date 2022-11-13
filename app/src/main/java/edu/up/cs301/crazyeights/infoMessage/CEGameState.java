package edu.up.cs301.crazyeights.infoMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.crazyeights.views.CESurfaceView;
import edu.up.cs301.game.GameFramework.infoMessage.GameState;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

/**
 * This class contains the state of the game. This class
 * is used by most other classes to determine what information
 * should be displayed on the screen.
 *
 * @author Ronnie Delos Santos
 * @author Emily Do
 * @author Noelle Lei Sam
 * @author Alex Melamai
 * @version November 2022
 */
public class CEGameState extends GameState implements Serializable {
    // the player index indicating who is moving
    public int playerToMove;
    // the discard pile storing card placed in the middle after it is selected
    public ArrayList<CECard> discardPile;
    // the draw pile containing all cards which will be dealt
    public ArrayList<CECard> drawPile;
    // the list of players existing in the game
    public GamePlayer[] playerList;

    /**
     * Constructor for CEGameState.
     * Assigns a reference to playerList.
     * This reference was obtained by modifying
     * the game framework source file: GameMainActivity.java.
     *
     * @param players List of players
     */
    public CEGameState(GamePlayer[] players){
        playerList = players;
        init();
    }

    /**
     * Helper method for our constructors.
     */
    public void init() {
        drawPile = new ArrayList<CECard>(); // ArrayList of cards in the drawPile
        discardPile = new ArrayList<CECard>(); // Arraylist of cards in the discard pile
        playerToMove = new Random().nextInt(playerList.length); // randomly choosing a players turn at start of game
        setDrawPile();
    }
    /**
     * Populates drawPile with each drawable card
     */
    public void setDrawPile() {
        for(CECard.SUIT s: CECard.SUIT.values())
        {
            for (CECard.FACE f : CECard.FACE.values())
            {
                drawPile.add(new CECard(f, s));
            }
        }
    }

    /**
     * Create more instances of GameState
     * @return
     */
    public CEGameState(CEGameState original) {
        this.playerList = original.playerList;
        this.drawPile = original.drawPile;
        this.discardPile = original.discardPile;
        this.playerToMove = original.playerToMove;
    }

    /**
     * Deals 5 cards to each of the 4 Player object's ArrayLists of cards_in_hand from the drawPile.
     */
    public void dealCards() {
        for (GamePlayer player : playerList) {
            for (int j = 0; j < 10; j++) {
                int index = new Random().nextInt(drawPile.size());
                player.addCardInHand(drawPile.get(index));
                drawPile.remove(index);
            }
        }
    }

    /**
     * Prints out GameState information in the form of a string
     * @return: All information of Players, draw pile, and discard pile
     *          for the instance of the game
     */
//    @Override
//    public String toString()
//    {
//        String playersHand = "";
//
//        for (GamePlayer al: playerList)
//        {
//            playersHand += "player " + al.playerID + ": ";
//            for (Card card : al.cards_in_Hand)
//            {
//                playersHand += card.toString() + " ";
//            }
//            playersHand += "\n\n";
//        }
//
//        String drawPileText = "Draw Pile Size: " + drawPile.size() ;
//        drawPileText += "\n\n";
//
//        String discardPileText = "Discard Pile: ";
//
//        for (Card card : discard) {
//            discardPileText += card.toString() + " ";
//        }
//        discardPileText += "\n\n";
//        String returnText = drawPileText + playersHand + discardPileText + "\nPlayer Turn: " + numPlayerTurn;
//
//        return returnText;
//    }

    /**
     * Boolean method if a card is selected it is removed from cards_in_hand and added to discard
     *
     * @param p: current player
     * @param selectedCard: card selected by Player
     * @return: True if the action is performed
     */
//    public boolean placeCard(Player p, CECard selectedCard) {
//        if(selectedCard != null) {
//            p.cards_in_Hand.remove(selectedCard);
//            discardPile.add(selectedCard);
//            return true;
//        }
//        return false;
//    }

    /**
     * Check if card is playable
     * @param card
     * @return: playable card
     */
    public CECard checkCardEligibility(CECard card) {
        CECard recentDiscardedCard;
        if (discardPile.size() == 0) {
            return card;
        }

        recentDiscardedCard = discardPile.get(discardPile.size()-1);
        if (card.face == recentDiscardedCard.face || card.suit == recentDiscardedCard.suit) {
            return card;
        }
        return null;
    }

    /**
     * Random Card object from drawPile is added to cards_in_hand ArrayList of Player object
     * @param player
     */
    public void drawCard(GamePlayer player) {
        int index = new Random().nextInt(drawPile.size());
        player.addCardInHand(drawPile.get(index));
        drawPile.remove(index);
    }

    /**
     * Increments player turn
     */
    public void setNumPlayerTurn()
    {
        if (playerToMove == 3){
            playerToMove = 0;
        } else {
            playerToMove++;
        }
    }
}
