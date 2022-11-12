package edu.up.cs301.crazyeights.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import edu.up.cs301.R;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;

public class CEView extends RelativeLayout {

    protected CEGameState state;

    public CEView(Context context) {
        super(context);
        init();
    }

    public CEView(Context context, AttributeSet attrs) {
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
    }
}
