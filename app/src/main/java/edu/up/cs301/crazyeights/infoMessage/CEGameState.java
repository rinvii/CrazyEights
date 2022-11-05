package edu.up.cs301.crazyeights.infoMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.GameFramework.infoMessage.GameState;

public class CEGameState extends GameState implements Serializable {
    public int numPlayerTurn;//int to track which Player object is in play
    public ArrayList<Player> players;//ArrayList of Player objects
    public ArrayList<Card> discard; //ArrayList of both cards played and cards that are drawable
    public ArrayList<Card> drawPile;


    public CEGameState(){
        players = new ArrayList<Player>(); //initializes ArrayLists in method
        drawPile = new ArrayList<Card>(); // ArrayList of cards in the drawpile
        discard = new ArrayList<Card>(); // Arraylist of cards in the discard pile

        setPlayers();
        setDrawPile();
        numPlayerTurn = new Random().nextInt(players.size()); // randomly choosing a players turn at start of game
    }

    /**
     * Populates players ArrayList with 4 Player objects
     */
    public void setPlayers() {
        for (int i = 0; i < 4; i++)
        {
            players.add(new Player(i));
        }
    }

    /**
     * Populates drawPile with each drawable card
     */
    public void setDrawPile() {
        for(Card.SUIT s: Card.SUIT.values())
        {
            for (Card.FACE f : Card.FACE.values())
            {
                drawPile.add(new Card(f, s));
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
     * Deals 5 cards to each of the 4 Player object's ArrayLists of cards_in_hand from the drawPile
     */
    public void Deal()
    {
        for (Player p : players)
        {
            for (int i = 0; i < 5; ++i)
            {
                int index = new Random().nextInt(drawPile.size());
                p.cards_in_Hand.add(drawPile.get(index));
                drawPile.remove(index); //updates the draw pile
            }
        }
    }

    /**
     * Prints out GameState information in the form of a string
     * @return: All information of Players, draw pile, and discard pile
     *          for the instance of the game
     */
    @Override
    public String toString()
    {
        String playersHand = "";

        for (Player al: players)
        {
            playersHand += "player " + al.playerID + ": ";
            for (Card card : al.cards_in_Hand)
            {
                playersHand += card.toString() + " ";
            }
            playersHand += "\n\n";
        }

        String drawPileText = "Draw Pile Size: " + drawPile.size() ;
        drawPileText += "\n\n";

        String discardPileText = "Discard Pile: ";

        for (Card card : discard) {
            discardPileText += card.toString() + " ";
        }
        discardPileText += "\n\n";
        String returnText = drawPileText + playersHand + discardPileText + "\nPlayer Turn: " + numPlayerTurn;

        return returnText;
    }

    /**
     * Boolean method if a card is selected it is removed from cards_in_hand and added to discard
     *
     * @param p: current player
     * @param selectedCard: card selected by Player
     * @return: True if the action is performed
     */
    public boolean placeCard(Player p, Card selectedCard) {
        if(selectedCard != null) {
            p.cards_in_Hand.remove(selectedCard);
            discard.add(selectedCard);
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
    public Card checkCardEligibility(Card card) {
        Card recentDiscardedCard;
        if (discard.size() == 0) {
            return card;
        }

        recentDiscardedCard = discard.get(discard.size()-1);
        if (card.face == recentDiscardedCard.face || card.suit == recentDiscardedCard.suit) {
            return card;
        }
        return null;
    }

    public void testPlay()
    {
        Player human = players.get(0);
        if(numPlayerTurn == 0){
            for (int i = 0; i < human.cards_in_Hand.size(); i++) {
                Card selectedCard = checkCardEligibility(human.cards_in_Hand.get(i));
                if (selectedCard == null) {
                    drawCard(human);
                    break;
                }
                placeCard(human, selectedCard);
            }
            numPlayerTurn++;
        }
    }

    /**
     * Random Card object from drawPile is added to cards_in_hand ArrayList of Player object
     * @param p
     */
    public void drawCard(Player p)
    {
        int index = new Random().nextInt(drawPile.size());
        p.cards_in_Hand.add(drawPile.get(index));
        drawPile.remove(index);
    }

    /**
     * Increments player turn
     */
    public void setNumPlayerTurn()
    {
        if (numPlayerTurn == 3){
            numPlayerTurn = 0;
        } else {
            numPlayerTurn++;
        }
    }
}
