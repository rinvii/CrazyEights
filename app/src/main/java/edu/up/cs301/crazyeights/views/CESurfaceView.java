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

public class CESurfaceView extends SurfaceView {

    boolean leftFilled;
    boolean topFilled;

    protected CEGameState state;

    public CESurfaceView(Context context) {
        super(context);
        init();
    }

    public CESurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setBackgroundResource(R.drawable.mahogany);
        return;
    }

    public void setState(CEGameState state) {
        this.state = state;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (state == null || state.playerList == null) {
            return;
        }

        drawOurCards(canvas);
        drawTopCards(canvas);

    }

    public void drawOurCards(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        // green center line
//        Paint strokePaint = new Paint();
//        strokePaint.setStyle(Paint.Style.STROKE);
//        strokePaint.setColor(Color.GREEN);
//        strokePaint.setStrokeWidth(10);

        for (GamePlayer player : state.playerList) {
            if (player instanceof CEHumanPlayer || player instanceof CESecondHumanPlayer) {
                for (CECard card : player.getCardsInHand()) {
                    ArrayList<CECard> cardsInHand = player.getCardsInHand();
                    int numOfCards = cardsInHand.size();
                    Log.i("CARD ID", card.face.name() + " " + card.suit.name());
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), card.getId());
                    int top = canvasHeight-726/3-726/3;
                    int bottom = canvasHeight-726/3;
                    int left = (500/6 * cardsInHand.indexOf(card)) + (canvasWidth - ((numOfCards + 1) * 500/6))/2;
                    int right = left + 500/3;

                    if (bmp != null) {
                        canvas.drawBitmap(bmp, null, new Rect(left, top, right, bottom), new Paint());
                        bmp.recycle();
                    }

                    if (cardsInHand.indexOf(card) == numOfCards - 1) {
                        card.setBounds(new Rect(left, top, left + 500 / 3, bottom));
                    } else {
                        card.setBounds(new Rect(left, top, left + 500 / 6, bottom));
                    }
                }
            }
        }

//        canvas.drawRect(canvasWidth/2-5, 0, canvasWidth/2+5, canvasHeight, strokePaint);

    }

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
                    Log.i("CARD ID", "BACK CARD DRAWN");
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
