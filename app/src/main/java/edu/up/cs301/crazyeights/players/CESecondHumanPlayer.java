package edu.up.cs301.crazyeights.players;

import android.view.View;

import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.players.GameHumanPlayer;

public class CESecondHumanPlayer extends GameHumanPlayer {

    private static final String TAG = "CEHumanPlayer";

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public CESecondHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }
}
