package edu.up.cs301.crazyeights.players;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import edu.up.cs301.R;
import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.crazyeights.ceActionMessage.CEPlaceAction;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.crazyeights.views.CESurfaceView;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.infoMessage.IllegalMoveInfo;
import edu.up.cs301.game.GameFramework.infoMessage.NotYourTurnInfo;
import edu.up.cs301.game.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.game.GameFramework.utilities.Logger;

public class CEHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {
    // tag for logging
    private static final String TAG = "CEHumanPlayer";

    // the surface view
    private CESurfaceView surfaceView;

    // the ID for the layout to use
    private int layoutId;

    // list of cards in the player's hand
    private ArrayList<CECard> cardsInHand;

    /**
     * constructor
     *
     * @param name the player's name
     * @param layoutId the id of the layout to use
     */
    public CEHumanPlayer(String name, int layoutId) {
        super(name);
        this.layoutId = layoutId;
        this.cardsInHand = new ArrayList<>();
    }

    /**
     * returns the GUI's top view
     *
     * @return
     * 		the GUI's top view
     */
    public View getTopView() {
//      return myActivity.findViewById(R.id.top_gui_layout);
        return null;
    };

    /**
     * Callback method, called when player gets a message
     *
     * @param info the message
     */
    @Override
    public void receiveInfo(GameInfo info) {

        if (surfaceView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if the move was out of turn or otherwise illegal, flash the screen
//            surfaceView.flash(Color.RED, 50);
        }
        else if (!(info instanceof CEGameState))
            // if we do not have a TTTState, ignore
            return;
        else {
            surfaceView.setState((CEGameState)info);
            surfaceView.invalidate();
            Logger.log(TAG, "receiving");
        }
    }

    /**
     * sets the current player as the activity's GUI
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(layoutId);

        surfaceView = (CESurfaceView) myActivity.findViewById(R.id.ourView);
        Logger.log("set listener","OnTouch");
        surfaceView.setOnTouchListener(this);

    }

    /**
     * callback method when the screen it touched. We're
     * looking for a screen touch (which we'll detect on
     * the "up" movement" onto a card
     *
     * @param motionEvent
     * 		the motion event that was detected
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != MotionEvent.ACTION_UP) return true;

        // x and y position of the touch
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < this.cardsInHand.size(); i++) {
                    CECard card = this.cardsInHand.get(i);
                    if (card.bounds.contains((int) x, (int) y)) {
                        game.sendAction(new CEPlaceAction(this, card));
                        Log.i("Player Action", "Human Player playing " + card.face.name() + " " + card.suit.name());
                    }
                }
                break;
        }
        surfaceView.invalidate();
        return true;
    }

    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * CEGamePlayer method implementations * * * * * * * * * * * * * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * Get a list of the player's hand.
     *
     * @return the list of cards in the player's hand
     */
    @Override
    public ArrayList<CECard> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * Adds card to hand.
     *
     * @param card the card to be added to this player's hand
     * @return the card which was added to the player's hand
     */
    @Override
    public CECard addCardInHand(CECard card) {
        this.cardsInHand.add(card);
        return card;
    }

    /**
     * Removes card in the hand.
     * @param card The card to be removed
     * @return The card that was removed
     */
    @Override
    public void removeCardInHand(CECard card) {
        this.cardsInHand.remove(card);
    }

}
