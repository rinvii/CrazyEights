package edu.up.cs301.crazyeights.views;

import android.content.Context;

import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.game.GameFramework.utilities.FlashSurfaceView;

public class CESurfaceView extends FlashSurfaceView {

    protected CEGameState state;



    public CESurfaceView(Context context) {
        super(context);
    }

    public void setState(CEGameState state) {
        this.state = state;
    }
}
