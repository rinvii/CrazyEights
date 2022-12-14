package edu.up.cs301.crazyeights;

import android.graphics.Point;
import android.graphics.Rect;

import edu.up.cs301.R;

/**
 *  This class represents each carc to displayed on the user interface.
 *  It contains an extensive amount of switch statements which are used
 *  to correspond to their images.
 *
 * @author Ronnie Delos Santos
 * @author Emily Do
 * @author Noelle Lei Sam
 * @author Alex Melamai
 * @version December 2022
 */
public class CECard {
    public FACE face;
    public SUIT suit;
    public Rect bounds;
    Point offsetPos;
    public Integer rotation;

    /**
     * Sets the card to the appropriate face and suit values
     * @param face
     * @param suit
     */
    public CECard(FACE face, SUIT suit) {
        this.face = face;
        this.suit = suit;
    }

    /**
     * Copy Constructor
     * @param original
     */
    public CECard(CECard original) {
        this.face = original.face;
        this.suit = original.suit;
        this.bounds = original.bounds;
        this.offsetPos = original.offsetPos;
        this.rotation = original.rotation;
    }

    /**
     * Enumerates FACE names
     */
    public enum FACE {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, KING, QUEEN, JACK;
    }

    /**
     * Enumerates SUIT names
     */
    public enum SUIT {
        HEART, SPADE, DIAMOND, CLUB;
    }

    /**
     * @return the image of the card which corresponds with SUIT and FACE
     */
    public int getId() {
        switch (suit) {
            case HEART:
                switch (face) {
                    case ACE:
                        return R.drawable.ace_hearts;
                    case TWO:
                        return R.drawable.two_hearts;
                    case THREE:
                        return R.drawable.three_hearts;
                    case FOUR:
                        return R.drawable.four_hearts;
                    case FIVE:
                        return R.drawable.five_hearts;
                    case SIX:
                        return R.drawable.six_hearts;
                    case SEVEN:
                        return R.drawable.seven_hearts;
                    case EIGHT:
                        return R.drawable.eight_hearts;
                    case NINE:
                        return R.drawable.nine_hearts;
                    case TEN:
                        return R.drawable.ten_hearts;
                    case KING:
                        return R.drawable.king_hearts;
                    case QUEEN:
                        return R.drawable.queen_hearts;
                    case JACK:
                        return R.drawable.jack_hearts;
                }
                break;
            case CLUB:
                switch (face) {
                    case ACE:
                        return R.drawable.ace_clubs;
                    case TWO:
                        return R.drawable.two_clubs;
                    case THREE:
                        return R.drawable.three_clubs;
                    case FOUR:
                        return R.drawable.four_clubs;
                    case FIVE:
                        return R.drawable.five_clubs;
                    case SIX:
                        return R.drawable.six_clubs;
                    case SEVEN:
                        return R.drawable.seven_clubs;
                    case EIGHT:
                        return R.drawable.eight_clubs;
                    case NINE:
                        return R.drawable.nine_clubs;
                    case TEN:
                        return R.drawable.ten_clubs;
                    case KING:
                        return R.drawable.king_clubs;
                    case QUEEN:
                        return R.drawable.queen_clubs;
                    case JACK:
                        return R.drawable.jack_clubs;
                }
                break;
            case DIAMOND:
                switch (face) {
                    case ACE:
                        return R.drawable.ace_diamonds;
                    case TWO:
                        return R.drawable.two_diamonds;
                    case THREE:
                        return R.drawable.three_diamonds;
                    case FOUR:
                        return R.drawable.four_diamonds;
                    case FIVE:
                        return R.drawable.five_diamonds;
                    case SIX:
                        return R.drawable.six_diamonds;
                    case SEVEN:
                        return R.drawable.seven_diamonds;
                    case EIGHT:
                        return R.drawable.eight_diamonds;
                    case NINE:
                        return R.drawable.nine_diamonds;
                    case TEN:
                        return R.drawable.ten_diamonds;
                    case KING:
                        return R.drawable.king_diamonds;
                    case QUEEN:
                        return R.drawable.queen_diamonds;
                    case JACK:
                        return R.drawable.jack_diamonds;
                }
                break;
            case SPADE:
                switch (face) {
                    case ACE:
                        return R.drawable.ace_spades;
                    case TWO:
                        return R.drawable.two_spades;
                    case THREE:
                        return R.drawable.three_spades;
                    case FOUR:
                        return R.drawable.four_spades;
                    case FIVE:
                        return R.drawable.five_spades;
                    case SIX:
                        return R.drawable.six_spades;
                    case SEVEN:
                        return R.drawable.seven_spades;
                    case EIGHT:
                        return R.drawable.eight_spades;
                    case NINE:
                        return R.drawable.nine_spades;
                    case TEN:
                        return R.drawable.ten_spades;
                    case KING:
                        return R.drawable.king_spades;
                    case QUEEN:
                        return R.drawable.queen_spades;
                    case JACK:
                        return R.drawable.jack_spades;
                }
                break;
        }
        return 0;
    }

    /**
     * Sets face card values
     * @return the score value of the appropriate face cord
     */
    public int getScore() {
                switch (face) {
                    case ACE:
                        return 1;
                    case TWO:
                        return 2;
                    case THREE:
                        return 3;
                    case FOUR:
                        return 4;
                    case FIVE:
                        return 5;
                    case SIX:
                        return 6;
                    case SEVEN:
                        return 7;
                    case EIGHT:
                        return 50;
                    case NINE:
                        return 9;
                    case TEN:
                        return 10;
                    case KING:
                        return 10;
                    case QUEEN:
                        return 10;
                    case JACK:
                        return 10;
                }
        return 0;
    }

    /**
     * Sets the x,y boundaries for this card
     * @param bounds
     * @return
     */
    public Rect setBounds(Rect bounds) {
        this.bounds = bounds;
        return bounds;
    }

    /**
     * Sets the offset of this card
     * @param offset
     * @return
     */
    public Point setDiscardCardOffset(Point offset) {
        this.offsetPos = offset;
        return offset;
    }

    /**
     * Sets the discard card rotation
     * @param rotation
     * @return degrees to rotate
     */
    public int setDiscardCardRotation(int rotation) {
        this.rotation = rotation;
        return rotation;
    }

    /**
     * Gets the offset position of this card
     * @return the offset position of this card
     */
    public Point getOffsetPos() {
        return this.offsetPos;
    }

    /**
     * Gets the degree of the card's position.
     * @return the degree of this card's position
     */
    public Integer getRotation() {
        return this.rotation;
    }
}
