package edu.up.cs301.crazyeights.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.ArrayList;

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
    private final int CARD_WIDTH = 500;
    private final int CARD_HEIGHT = 726;
    private final int ADJUSTED_CARD_WIDTH = CARD_WIDTH / 3;
    private final int ADJUSTED_CARD_HEIGHT = CARD_HEIGHT / 3;
    private final int ADJUSTED_CARD_HALF_WIDTH = ADJUSTED_CARD_WIDTH / 2;
    private final int ADJUSTED_CARD_HALF_HEIGHT = ADJUSTED_CARD_HEIGHT / 2;

    boolean leftFilled;
    boolean topFilled;

    // the game state
    protected CEGameState state;

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

        // invoke helper draw methods
        drawOurCards(canvas);
        drawTopCards(canvas);

    }

    /**
     * Draw the current GUI holder's cards.
     *
     * @param canvas The canvas to draw on
     */
    public void drawOurCards(Canvas canvas) {
        // Canvas dimensions
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        // green center line
//        Paint strokePaint = new Paint();
//        strokePaint.setStyle(Paint.Style.STROKE);
//        strokePaint.setColor(Color.GREEN);
//        strokePaint.setStrokeWidth(10);

        // iterate through every player
        for (GamePlayer player : state.playerList) {

            // check if iterable is instance of CEHumanPlayer or CESecondHumanPlayer
            if (player instanceof CEHumanPlayer || player instanceof CESecondHumanPlayer) {

                // iterate through the player's hand
                for (CECard card : player.getCardsInHand()) {

                    ArrayList<CECard> cardsInHand = player.getCardsInHand();
                    int numOfCards = cardsInHand.size();

                    Log.i("CARD ID", card.face.name() + " " + card.suit.name());

                    // bitmap to draw the card onto
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), card.getId());

                    // top starts at the bottom and we subtract two card heights
                    int top = canvasHeight - ADJUSTED_CARD_HEIGHT * 2;
                    // bottom starts at the bottom and we subtract one card height for a correct card height aftermath
                    int bottom = canvasHeight - ADJUSTED_CARD_HEIGHT;
                    // card left boundary starts at the left edge of screen which uses the
                    // product of the card's index and the card's half width to
                    // achieve a fan fold effect, then the entire fan is shifted to the right
                    // by calculating the total width of the fan and subtracting from the canvas
                    // width and by diving in two and adding the calculation to every card thereby
                    // centering the entire hand
                    int left = (ADJUSTED_CARD_HALF_WIDTH * cardsInHand.indexOf(card))
                            + (canvasWidth - ((numOfCards + 1) * ADJUSTED_CARD_HALF_WIDTH)) / 2;
                    // complete the card's right boundary with a full card width
                    int right = left + ADJUSTED_CARD_WIDTH;

                    // check if bitmap exists and draw it
                    if (bmp != null) {
                        canvas.drawBitmap(bmp, null, new Rect(left, top, right, bottom), new Paint());
                        bmp.recycle();
                    }

                    // store visible card boundaries to the card object for listener use
                    if (cardsInHand.indexOf(card) == numOfCards - 1) {
                        card.setBounds(new Rect(left, top, left + ADJUSTED_CARD_WIDTH, bottom));
                    } else {
                        card.setBounds(new Rect(left, top, left + ADJUSTED_CARD_HALF_WIDTH, bottom));
                    }
                }
            }
        }

//        canvas.drawRect(canvasWidth/2-5, 0, canvasWidth/2+5, canvasHeight, strokePaint);

    }

    /**
     * Effectively the same as the drawOurCards() method.
     *
     * @param canvas The canvas to draw
     */
    public void drawTopCards(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        // green center line
//        Paint strokePaint = new Paint();
//        strokePaint.setStyle(Paint.Style.STROKE);
//        strokePaint.setColor(Color.GREEN);
//        strokePaint.setStrokeWidth(10);

        for (GamePlayer player : state.playerList) {
            if (player instanceof CEDumbAI || player instanceof CESmartAI) {
                for (CECard card : player.getCardsInHand()) {
                    ArrayList<CECard> cardsInHand = player.getCardsInHand();
                    int numOfCards = cardsInHand.size();
//                    Log.i("CARD ID", card.face.name() + " " + card.suit.name());
                    Log.i("CARD ID", "BACK CARD");
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.back_card);
                    int top;
                    int bottom;
                    int left;
                    int right;

//                    if (!topFilled) {
                        top = 726 / 3 + 726/3;
                        bottom = 726 / 3 + 726 / 3 + 726/3;
                        right = canvasWidth - (500/6 * cardsInHand.indexOf(card)) - (canvasWidth - ((numOfCards + 1) * 500/6))/2;
                        left = right - 500/3;
//                    } else if (!leftFilled) {
//                        top = 726 / 3 + 726/3 + 726/3;
//                        bottom = 726 / 3 + 726 / 3 + 726/3;
//                        right = canvasWidth - (500/6 * cardsInHand.indexOf(card)) - (canvasWidth - ((numOfCards + 1) * 500/6))/2;
//                        left = right - 500/3;
//                    } else {
//                        top = 726 / 3 + 726/3;
//                        bottom = 726 / 3 + 726 / 3 + 726/3;
//                        right = canvasWidth - (500/6 * cardsInHand.indexOf(card)) - (canvasWidth - ((numOfCards + 1) * 500/6))/2;
//                        left = right - 500/3;
//                    }

                    if (bmp != null) {
                        canvas.drawBitmap(bmp, null, new Rect(left, top, right, bottom), new Paint());
                        bmp.recycle();
                    }

//                    if (cardsInHand.indexOf(card) == numOfCards - 1) {
//                        card.setBounds(new Rect(left, top, left + 500 / 3, bottom));
//                    } else {
//                        card.setBounds(new Rect(left, top, left + 500 / 6, bottom));
//                    }
                }
                break;
            }
        }
    }
}
