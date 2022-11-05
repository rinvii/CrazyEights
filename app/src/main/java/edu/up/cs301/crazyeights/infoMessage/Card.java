package edu.up.cs301.crazyeights.infoMessage;
/**
    @authors Ronnie Delos Santos, Noelle Lei Sam, Emily Do, Alex Melemai
 */

public class Card {

    /**
     * Card constructor assigns face value and suit
     * @param f: FACE enum
     * @param s: SUIT enum
     */
    public Card(FACE f, SUIT s)
    {
        face = f;
        suit = s;
    }

    /**
     * Enum for ace through king constants
     */
    public enum FACE
    {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, KING, QUEEN, JACK
    }

    /**
     * Enum for suit constants
     */
    public enum SUIT
    {
        HEART, SPADE, DIAMOND, CLUB
    }

    public FACE face; // face and suit values initalized
    public SUIT suit;

    public String toString()
    {
        return suit.name() + "-" + face.name();
    }
}
