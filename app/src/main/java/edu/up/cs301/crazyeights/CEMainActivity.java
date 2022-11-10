package edu.up.cs301.crazyeights;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import edu.up.cs301.R;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.crazyeights.players.CEDumbAI;
import edu.up.cs301.crazyeights.players.CEHumanPlayer;
import edu.up.cs301.crazyeights.players.CESecondHumanPlayer;
import edu.up.cs301.crazyeights.players.CESmartAI;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.gameConfiguration.GameConfig;
import edu.up.cs301.game.GameFramework.gameConfiguration.GamePlayerType;
import edu.up.cs301.game.GameFramework.infoMessage.GameState;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

public class CEMainActivity extends GameMainActivity {
    private static final String TAG = "CEMainActivity";
    public static final int PORT_NUMBER = 8000;


    @Override
    public GameConfig createDefaultConfig() {
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("Human Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new CEHumanPlayer(name, R.layout.activity_main);
            }
        });

        playerTypes.add(new GamePlayerType("Second Human Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new CESecondHumanPlayer(name);
            }
        });

        playerTypes.add(new GamePlayerType("Dumb AI") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new CEDumbAI(name);
            }
        });

        playerTypes.add(new GamePlayerType("Smart AI") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new CESmartAI(name);
            }
        });

        GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "Crazy Eights", PORT_NUMBER);

        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer", 2);

//        defaultConfig.setRemoteData("Remote Player", "", 1);

        return defaultConfig;
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