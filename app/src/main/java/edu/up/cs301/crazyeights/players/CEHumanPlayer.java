package edu.up.cs301.crazyeights.players;

import android.graphics.Color;
import android.view.View;

import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.crazyeights.views.CESurfaceView;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.infoMessage.IllegalMoveInfo;
import edu.up.cs301.game.GameFramework.infoMessage.NotYourTurnInfo;
import edu.up.cs301.game.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.game.GameFramework.utilities.Logger;

public class CEHumanPlayer extends GameHumanPlayer {

    private static final String TAG = "CEHumanPlayer";

    private CESurfaceView surfaceView;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public CEHumanPlayer(String name) {
        super(name);
    }

    public View getTopView() {
      return null;
    };

    public void receiveInfo(GameInfo info) {
        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if the move was out of turn or otherwise illegal, flash the screen
            surfaceView.flash(Color.RED, 50);
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

    @Override
    public void setAsGui(GameMainActivity activity) {

    }
}
