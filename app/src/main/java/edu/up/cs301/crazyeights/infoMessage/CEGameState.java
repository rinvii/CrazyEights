package edu.up.cs301.crazyeights.infoMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.game.GameFramework.infoMessage.GameState;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

public class CEGameState extends GameState implements Serializable {
    public int playerToMove;
    public ArrayList<CECard> discardPile;
    public ArrayList<CECard> drawPile;
    public GamePlayer[] playerList;

    public CEGameState(){
        drawPile = new ArrayList<CECard>(); // ArrayList of cards in the drawpile
        discardPile = new ArrayList<CECard>(); // Arraylist of cards in the discard pile
        playerToMove = new Random().nextInt(playerList.length); // randomly choosing a players turn at start of game
        setDrawPile();
    }

    public CEGameState(GamePlayer[] players){
        drawPile = new ArrayList<CECard>(); // ArrayList of cards in the drawpile
        discardPile = new ArrayList<CECard>(); // Arraylist of cards in the discard pile
        playerList = players;
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
//        try {
//            return (CEGameState) super.clone();
//        } catch (CloneNotSupportedException e) {
//            return null;
//        }
    }

    /**
     * Deals 5 cards to each of the 4 Player object's ArrayLists of cards_in_hand from the drawPile.
     */
    public void dealCards() {
        for (GamePlayer player : playerList) {
            for (int j = 0; j < 5; j++) {
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
    public boolean placeCard(Player p, CECard selectedCard) {
        if(selectedCard != null) {
            p.cards_in_Hand.remove(selectedCard);
            discardPile.add(selectedCard);
            return true;
        }
        return false;
    }

    public boolean difficulty() {
        return false;
    }
    public boolean restart() {
        return false;
    }

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
