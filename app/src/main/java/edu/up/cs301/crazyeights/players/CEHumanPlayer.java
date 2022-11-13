package edu.up.cs301.crazyeights.players;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import edu.up.cs301.R;
import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.crazyeights.CELocalGame;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.crazyeights.views.CESurfaceView;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.infoMessage.IllegalMoveInfo;
import edu.up.cs301.game.GameFramework.infoMessage.NotYourTurnInfo;
import edu.up.cs301.game.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.game.GameFramework.utilities.Logger;

public class CEHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {

    private static final String TAG = "CEHumanPlayer";
    private CESurfaceView surfaceView;
    private int layoutId;
    private ArrayList<CECard> cardsInHand;
    private CEGameState state;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public CEHumanPlayer(String name, int layoutId) {
        super(name);
        this.layoutId = layoutId;
        this.cardsInHand = new ArrayList<>();
    }

    public View getTopView() {
      return null;
    };

    public void receiveInfo(GameInfo info) {
        this.state = (CEGameState) info;

        if (surfaceView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if the move was out of turn or otherwise illegal, flash the screen
//            surfaceView.flash(Color.RED, 50);
        }
        else if (!(info instanceof CEGameState))
            return;
        else {
            surfaceView.setState((CEGameState)info);
            surfaceView.invalidate();
            Logger.log(TAG, "receiving");
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(layoutId);

        surfaceView = (CESurfaceView) myActivity.findViewById(R.id.ourView);
        Logger.log("set listener","OnTouch");
        surfaceView.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (CECard card : cardsInHand) {
                    if (card.bounds.contains((int) x, (int) y)) {
                        Log.i("CLICKED CARD ID", card.face.name() + " " + card.suit.name());
                    }
                }
                break;
        }

        return false;
    }

    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * CEGamePlayer method implementations * * * * * * * * * * * * * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    @Override
    public ArrayList<CECard> getCardsInHand() {
        return cardsInHand;
    }

    @Override
    public CECard addCardInHand(CECard card) {
        this.cardsInHand.add(card);
        return card;
    }

}
