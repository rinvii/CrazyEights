package edu.up.cs301.crazyeights;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.up.cs301.R;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.gameConfiguration.GameConfig;
import edu.up.cs301.game.GameFramework.infoMessage.GameState;

public class CEMainActivity extends GameMainActivity {
    private static final String TAG = "TTTMainActivity";

    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        if(gameState == null)
            return new CELocalGame();
        return new CELocalGame((CEGameState) gameState);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}