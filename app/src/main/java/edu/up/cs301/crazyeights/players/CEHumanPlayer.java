package edu.up.cs301.crazyeights.players;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import edu.up.cs301.R;
import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.crazyeights.views.CEView;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.infoMessage.IllegalMoveInfo;
import edu.up.cs301.game.GameFramework.infoMessage.NotYourTurnInfo;
import edu.up.cs301.game.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.game.GameFramework.utilities.Logger;

public class CEHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {

    private static final String TAG = "CEHumanPlayer";
    private CEView surfaceView;
    private int layoutId;
    private ArrayList<CECard> cardsInHand;

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

        surfaceView = (CEView) myActivity.findViewById(R.id.ourView);
        Logger.log("set listener","OnTouch");
        surfaceView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
//        switch (view) {
//            case :
//        }
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
