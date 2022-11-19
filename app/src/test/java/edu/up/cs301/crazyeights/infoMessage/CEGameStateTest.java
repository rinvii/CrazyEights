package edu.up.cs301.crazyeights.infoMessage;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Random;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.crazyeights.players.CEDumbAI;
import edu.up.cs301.crazyeights.players.CEHumanPlayer;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

public class CEGameStateTest {

    @Test
    public void init() {

    }

    @Test
    public void testInitialPlayerTurnRandomness() {
        GamePlayer[] players = new GamePlayer[4];
        CEGameState gamestate = new CEGameState(players);

    }

    @Test
    public void testIfDrawPileIsDealt() {
        GamePlayer[] players = new GamePlayer[4];
        CEGameState gamestate = new CEGameState(players);
        assertEquals(52, gamestate.drawPile.size());
    }

    @Test
    public void testIfCardsAreDealt() {
        GamePlayer[] players = new GamePlayer[4];
        CEGameState gamestate = new CEGameState(players);
        for(int i = 0; i < 4; ++i){
            players[i] = new CEDumbAI(i + "");
        }
        gamestate.dealCards();
        assertEquals(5, players[0].getCardsInHand().size());
        assertEquals(5, players[1].getCardsInHand().size());
        assertEquals(5, players[2].getCardsInHand().size());
        assertEquals(5, players[3].getCardsInHand().size());
    }

    @Test
    public void testIfCardIsPlaced() {
        GamePlayer[] players = new GamePlayer[4];
        CEGameState gamestate = new CEGameState(players);

    }

    @Test
    public void getRandomCard() {

    }

    @Test
    public void testIfCardIsEligible() {
        GamePlayer[] players = new GamePlayer[4];
        CEGameState gamestate = new CEGameState(players);
        gamestate.placeCard(gamestate.drawPile.get(0));
        CECard card1 = new CECard(CECard.FACE.ACE, CECard.SUIT.values()[new Random().nextInt(CECard.SUIT.values().length)]);
        CECard card2 = new CECard(CECard.FACE.values()[new Random().nextInt(CECard.FACE.values().length)], CECard.SUIT.HEART);
        CECard card3 = new CECard(CECard.FACE.EIGHT, CECard.SUIT.DIAMOND);
        CECard card4 = new CECard(CECard.FACE.KING, CECard.SUIT.CLUB);
        assertTrue(gamestate.checkCardEligibility(card1));
        assertTrue(gamestate.checkCardEligibility(card2));
        assertTrue(gamestate.checkCardEligibility(card3));
        assertFalse(gamestate.checkCardEligibility(card4));
    }

    @Test
    public void testIfCardIsDrawn() {
        GamePlayer[] players = new GamePlayer[4];
        for(int i =0; i < 4; ++i){
            players[i] = new CEDumbAI(i + "");
        }
        CEGameState gamestate = new CEGameState(players);
        gamestate.drawCard(players[0]);
        assertEquals(1, players[0].getCardsInHand().size());
    }

    @Test
    public void testIfPlayerTurnIsIncremented() {
        GamePlayer[] players = new GamePlayer[4];
        CEGameState gamestate = new CEGameState(players);
        int playerTurn = new Integer(gamestate.playerToMove);
        gamestate.setNumPlayerTurn();
        assertEquals(++playerTurn % 4, gamestate.playerToMove);
    }

    @Test
    public void getPlayerToMove() {
    }
}