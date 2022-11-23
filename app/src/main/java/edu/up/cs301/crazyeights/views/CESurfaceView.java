package edu.up.cs301.crazyeights.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.R;
import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.crazyeights.players.CEDumbAI;
import edu.up.cs301.crazyeights.players.CEHumanPlayer;
import edu.up.cs301.crazyeights.players.CESecondHumanPlayer;
import edu.up.cs301.crazyeights.players.CESmartAI;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

/**
 * Custom SurfaceView that draws the entire GUI.
 * The GUI contains each players hands, player score information,
 * and the discardPile and the drawPile.
 *
 * @author Ronnie Delos Santos
 * @version November 2022
 */
public class CESurfaceView extends SurfaceView {

    // Card dimensions
    private final int cardWidthRatio = 8; // cards it will take to fill up the the screen width-wise
    private final int cardLengthRatio = 10; // cards it will take to fill up the the screen width-wise
    private final int CARD_WIDTH = 500;
    private final int CARD_HEIGHT = 726;
//    private final int ADJUSTED_CARD_WIDTH = CARD_WIDTH / 3;
//    private final int ADJUSTED_CARD_HEIGHT = CARD_HEIGHT / 3;
//    private final int ADJUSTED_CARD_HALF_WIDTH = ADJUSTED_CARD_WIDTH / 2;
//    private final int ADJUSTED_CARD_HALF_HEIGHT = ADJUSTED_CARD_HEIGHT / 2;

    boolean rightFilled;
    boolean topFilled;
    boolean leftFilled;

    // the current GUI holder's index of their player object in the player list
    private int ourIndex;

    // the game state
    protected CEGameState state;

    protected Rect drawPileBoundaries;

    /**
     * Constructor for the CESurfaceView class.
     *
     * @param context A reference to the activity in which this class is run under
     */
    public CESurfaceView(Context context) {
        super(context);
        init();
    }

    /**
     * Alternate constructor for when a subclass is directly specified in the layout.
     *
     * @param context A reference to the activity in which this class is run under
     * @param attrs A set of attributes passed from the system
     */
    public CESurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Helper-method for the constructors
     */
    private void init() {
        // initially set background of the CESurfaceView
        this.setBackgroundResource(R.drawable.mahogany);
        return;
    }

    /**
     * Assign CEGameState to this class's instance variable state.
     *
     * @param state The state to assign to our field
     */
    public void setState(CEGameState state) {
        this.state = state;
    }

    /**
     * Callback method called when it is time to redraw.
     * @param canvas The canvas to draw on
     */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // if state does not exist exit
        if (state == null || state.playerList == null) {
            return;
        }

        // invoke helper draw method
        drawCards(canvas);
    }

    /**
     * Draw the current GUI holder's cards.
     *
     * @param canvas The canvas to draw on
     */
    public void drawCards(Canvas canvas) {
        // Canvas dimensions
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        final int ADJUSTED_CARD_WIDTH = canvasWidth / cardWidthRatio;
        final int ADJUSTED_CARD_HEIGHT = canvasHeight / cardLengthRatio;
        final int ADJUSTED_CARD_HALF_WIDTH = ADJUSTED_CARD_WIDTH / 2;
        final int ADJUSTED_CARD_HALF_HEIGHT = ADJUSTED_CARD_HEIGHT / 2;

        // green alignment region paint
        Paint strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.GREEN);
        strokePaint.setStrokeWidth(1);

        Bitmap bmpBackOfCard = BitmapFactory.decodeResource(getResources(), R.drawable.back_card);

        // iterate through every player and draw the cards in their hand
        for (GamePlayer player : state.playerList) {

            // check if iterable is instance of CEHumanPlayer or CESecondHumanPlayer
            if (player instanceof CEHumanPlayer || player instanceof CESecondHumanPlayer) {

                // iterate through the player's hand
                for (int i = 0; i < player.getCardsInHand().size(); i++) {
                    ArrayList<CECard> cardsInHand = player.getCardsInHand();
                    CECard card = cardsInHand.get(i);
                    int numOfCards = cardsInHand.size();

                    // bitmap to draw the card onto
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), card.getId());

                    // top starts at the bottom and we subtract two card heights
                    int top = canvasHeight - (ADJUSTED_CARD_HEIGHT + canvasHeight / 24) - canvasHeight / 24;
                    // bottom boundary complete with full card height
                    int bottom = top + (ADJUSTED_CARD_HEIGHT + canvasHeight / 24);
                    // card left boundary starts at the left edge of screen which uses the
                    // product of the card's index and the card's half width to
                    // achieve a fan fold effect, then the entire fan is shifted to the right
                    // by calculating the total width of the fan and subtracting from the canvas
                    // width and by diving in two and adding the calculation to every card thereby
                    // centering the entire hand
                    int left = ((ADJUSTED_CARD_HALF_WIDTH + canvasWidth / 32) * cardsInHand.indexOf(card))
                            + (canvasWidth - ((numOfCards + 1) * (ADJUSTED_CARD_HALF_WIDTH + canvasWidth / 32))) / 2;
                    if(cardsInHand.size() >= 6) {
                        left = ((ADJUSTED_CARD_HALF_WIDTH + canvasWidth / 64) * cardsInHand.indexOf(card))
                                + (canvasWidth - ((numOfCards + 1) * (ADJUSTED_CARD_HALF_WIDTH + canvasWidth / 64))) / 2;
                    }
                    // complete the card's right boundary with a full card width
                    int right = left + (ADJUSTED_CARD_WIDTH + canvasWidth / 16);

                    // check if bitmap exists and draw it
                    if (bmp != null) {
                        canvas.drawBitmap(bmp, null, new Rect(left, top, right, bottom), new Paint());
                        bmp.recycle();
                    } else {
                        Log.e("CESurfaceView", card.face.name() + " " + card.suit.name());
                    }

                    // store visible card boundaries to the card object for listener use
                    if (cardsInHand.indexOf(card) == numOfCards - 1) {
                        card.setBounds(new Rect(left, top, left + ADJUSTED_CARD_WIDTH + canvasWidth / 16, bottom));
                    } else {
                        card.setBounds(new Rect(left, top, left + ADJUSTED_CARD_HALF_WIDTH + canvasWidth / 32, bottom));
                    }
                }
            } else if (player instanceof CEDumbAI || player instanceof CESmartAI) {
                if(!leftFilled) {
                    for (int i = 0; i < player.getCardsInHand().size(); i++) {
                        ArrayList<CECard> cardsInHand = player.getCardsInHand();
                        CECard card = cardsInHand.get(i);
                        int numOfCards = cardsInHand.size();
                        int top = ADJUSTED_CARD_HALF_WIDTH + ADJUSTED_CARD_HEIGHT * 2 + canvasHeight / 16
                                + ADJUSTED_CARD_HALF_WIDTH * cardsInHand.indexOf(card)
                                + ((canvasHeight - ADJUSTED_CARD_HEIGHT - canvasHeight / 24 - (ADJUSTED_CARD_HEIGHT * 2 + canvasHeight / 16))
                                - ((numOfCards + 1) * ADJUSTED_CARD_HALF_WIDTH)) / 2;
                        int left = ADJUSTED_CARD_HALF_HEIGHT + canvasWidth / 50;
                        if (bmpBackOfCard != null) {
                            canvas.save();
                            left -= ADJUSTED_CARD_HALF_WIDTH;
                            top -= ADJUSTED_CARD_HALF_HEIGHT;
                            canvas.rotate(90, left + ADJUSTED_CARD_HALF_WIDTH, top + ADJUSTED_CARD_HALF_HEIGHT);
                            canvas.drawBitmap(bmpBackOfCard, null, new Rect(left, top, left + ADJUSTED_CARD_WIDTH, top + ADJUSTED_CARD_HEIGHT), new Paint());
                            canvas.restore();
                        } else {
                            Log.e("CESurfaceView", card.face.name() + " " + card.suit.name());
                        }
                    }
                    leftFilled=true;
                }
                else if (!topFilled) {
                    for (int i = 0; i < player.getCardsInHand().size(); i++) {
                        ArrayList<CECard> cardsInHand = player.getCardsInHand();
                        CECard card = cardsInHand.get(i);
                        int numOfCards = cardsInHand.size();
                        int top = ADJUSTED_CARD_HEIGHT + canvasHeight / 16;
                        int bottom = top + ADJUSTED_CARD_HEIGHT;
                        int right = canvasWidth - (ADJUSTED_CARD_HALF_WIDTH * cardsInHand.indexOf(card))
                                - (canvasWidth - ((numOfCards + 1) * ADJUSTED_CARD_HALF_WIDTH)) / 2;
                        int left = right - 500/3;
                        if (bmpBackOfCard != null) {
                            canvas.drawBitmap(bmpBackOfCard, null, new Rect(left, top, right, bottom), new Paint());
                        } else {
                            Log.e("CESurfaceView", card.face.name() + " " + card.suit.name());
                        }
                    }
                    topFilled = true;
                } else if (!rightFilled) {
                    for (int i = 0; i < player.getCardsInHand().size(); i++) {
                        ArrayList<CECard> cardsInHand = player.getCardsInHand();
                        CECard card = cardsInHand.get(i);
                        int numOfCards = cardsInHand.size();
                        int top = ADJUSTED_CARD_HALF_WIDTH + ADJUSTED_CARD_HEIGHT * 2 + canvasHeight / 16
                                + ADJUSTED_CARD_HALF_WIDTH * (cardsInHand.size() - 1 - cardsInHand.indexOf(card))
                                + ((canvasHeight - ADJUSTED_CARD_HEIGHT - canvasHeight / 24 - (ADJUSTED_CARD_HEIGHT * 2 + canvasHeight / 16))
                                - ((numOfCards + 1) * ADJUSTED_CARD_HALF_WIDTH)) / 2;
                        int left = canvasWidth - ADJUSTED_CARD_HALF_HEIGHT - canvasWidth / 50;
                        if (bmpBackOfCard != null) {
                            canvas.save();
                            left -= ADJUSTED_CARD_HALF_WIDTH;
                            top -= ADJUSTED_CARD_HALF_HEIGHT;
                            canvas.rotate(90, left + ADJUSTED_CARD_HALF_WIDTH, top + ADJUSTED_CARD_HALF_HEIGHT);
                            canvas.drawBitmap(bmpBackOfCard, null, new Rect(left, top, left + ADJUSTED_CARD_WIDTH, top + ADJUSTED_CARD_HEIGHT), new Paint());
                            canvas.restore();
                        } else {
                            Log.e("CESurfaceView", card.face.name() + " " + card.suit.name());
                        }
                    }
                    rightFilled = true;
                }
            }
        }

        // draw drawPile
        int drawPileTop = 0;
        int drawPileLeft = 0;
        int drawPileBottom = 0;

        for (int i = 0; i < 7; i++) {
            int top = ADJUSTED_CARD_HALF_WIDTH * 3 + ADJUSTED_CARD_HEIGHT * 2 + canvasHeight / 16
                    + (canvasHeight / 241) * (7 - i);
            int left = canvasWidth / 2;
            if (bmpBackOfCard != null) {
                canvas.save();
                left -= ADJUSTED_CARD_HALF_WIDTH;
                top -= ADJUSTED_CARD_HALF_HEIGHT;
                canvas.rotate(90, left + ADJUSTED_CARD_HALF_WIDTH, top + ADJUSTED_CARD_HALF_HEIGHT);
                canvas.drawBitmap(bmpBackOfCard, null, new Rect(left, top, left + ADJUSTED_CARD_WIDTH, top + ADJUSTED_CARD_HEIGHT), new Paint());
                canvas.restore();
                // get draw pile boundaries
                if (i == 0) {
                    drawPileBottom = top + ADJUSTED_CARD_HEIGHT;
                    drawPileLeft = left;
                } else if (i == 6) {
                    drawPileTop = top;
                }
            }
        }

        // assign draw pile boundaries for listeners to know they are clicking the drawPile
        this.drawPileBoundaries = new Rect(drawPileLeft, drawPileTop, drawPileLeft + ADJUSTED_CARD_HEIGHT, drawPileBottom);

        // draw discardPile
        for (int i = 0; i < state.discardPile.size(); i++) {
            CECard card = state.discardPile.get(i);
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), card.getId());
            int top = (canvasHeight - ADJUSTED_CARD_HEIGHT - canvasHeight / 24 + (ADJUSTED_CARD_HEIGHT * 2 + canvasHeight / 16)) / 2;
            int left = canvasWidth / 2;
            int rotation = 0;

            if (card.getOffsetPos() == null  && card.rotation == null) {
                Point offsetPoint = card.setDiscardCardOffset(
                        new Point(new Random().nextInt(100) - 50, new Random().nextInt(100) - 50));
                left += offsetPoint.x;
                top += offsetPoint.y;
                rotation = card.setDiscardCardRotation(new Random().nextInt(360));
            } else {
                left += card.getOffsetPos().x;
                top += card.getOffsetPos().y;
                rotation = card.getRotation();
            }

            if (bmp != null) {
                canvas.save();
                left -= ADJUSTED_CARD_HALF_WIDTH;
                top -= ADJUSTED_CARD_HALF_HEIGHT;
                canvas.rotate(rotation, left + ADJUSTED_CARD_HALF_WIDTH, top + ADJUSTED_CARD_HALF_HEIGHT);
                canvas.drawBitmap(bmp, null, new Rect(left, top, left + ADJUSTED_CARD_WIDTH, top + ADJUSTED_CARD_HEIGHT), new Paint());
                canvas.restore();
//                bmp.recycle();
            } else {
                Log.e("discardPile", card.face.name() + " " + card.suit.name());
            }
        }

//        bmpBackOfCard.recycle();

        topFilled = false;
        rightFilled = false;
        leftFilled = false;

        canvas.drawRect(canvasWidth/2, 0, canvasWidth/2, canvasHeight, strokePaint);
        canvas.drawRect(0, canvasHeight / 2, canvasWidth, canvasHeight / 2
                , strokePaint);
        // playing region
        canvas.drawRect(0, ADJUSTED_CARD_HEIGHT * 2 + canvasHeight / 16, canvasWidth, canvasHeight - ADJUSTED_CARD_HEIGHT - canvasHeight / 24, strokePaint);
        canvas.drawRect(0, (canvasHeight - ADJUSTED_CARD_HEIGHT - canvasHeight / 24 + (ADJUSTED_CARD_HEIGHT * 2 + canvasHeight / 16)) / 2, canvasWidth, (canvasHeight - ADJUSTED_CARD_HEIGHT - canvasHeight / 24 + (ADJUSTED_CARD_HEIGHT * 2 + canvasHeight / 16)) / 2, strokePaint);
    }

    public Rect getDrawPileBoundaries() {
        return this.drawPileBoundaries;
    }

    public CEGameState getState() {
        return this.state;
    }
}
