package edu.up.cs301.crazyeights;

public class CECard {
    public FACE face;
    public SUIT suit;

    public CECard(FACE f, SUIT s) {
        this.face = f;
        this.suit = s;
    }

    public enum FACE {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, KING, QUEEN, JACK;
    }

    public enum SUIT {
        HEART, SPADE, DIAMOND, CLUB;
    }
}
