package edu.up.cs301.crazyeights.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.game.GameFramework.utilities.FlashSurfaceView;

public class CESurfaceView extends FlashSurfaceView {

    protected CEGameState state;



    public CESurfaceView(Context context) {
        super(context);
    }

    public CESurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        return;
    }

    public void setState(CEGameState state) {
        this.state = state;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
