package edu.up.cs301.crazyeights;

import android.media.MediaPlayer;

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
import edu.up.cs301.game.GameFramework.utilities.Logger;
import edu.up.cs301.game.GameFramework.utilities.Saving;


/**
 * The primary activity for our Crazy Eights game.
 * This class defines four player types: Human Player,
 * Second Human Player, Dumb AI, and Smart AI. It creates
 * the default lobby of one human player and three dumb AIs.
 * This class instantiates a CELocalGame object.
 *
 * @author Ronnie Delos Santos
 * @author Emily Do
 * @author Noelle Lei Sam
 * @author Alex Melamai
 * @version November 2022
 */
public class CEMainActivity extends GameMainActivity {
    // tag for logging
    private static final String TAG = "CEMainActivity";
    public static final int PORT_NUMBER = 8000;


    /**
     * Creates the default configuration for what the player
     * types will be. Adds four default players to the lobby.
     *
     * @return
     */
    @Override
    public GameConfig createDefaultConfig() {


        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // First human player
        playerTypes.add(new GamePlayerType("Human Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new CEHumanPlayer(name, R.layout.activity_main);
            }
        });

        // Second human player for network play
        playerTypes.add(new GamePlayerType("Second Human Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new CESecondHumanPlayer(name);
            }
        });

        // Dumb AI player
        playerTypes.add(new GamePlayerType("Dumb AI") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new CEDumbAI(name);
            }
        });

        // Smart AI player
        playerTypes.add(new GamePlayerType("Smart AI") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new CESmartAI(name);
            }
        });

        // Create default configuration object for Crazy Eights
        GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "Crazy Eights", PORT_NUMBER);

        // Add default players
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer1", 2);
        defaultConfig.addPlayer("Computer2", 2);
        defaultConfig.addPlayer("Computer3", 2);

        // Set the in initial information for the remote player
//        defaultConfig.setRemoteData("Remote Player", "", 1);

        return defaultConfig;
    }

    /**
     *
     * @param gameState
     *              The desired gameState to start at or null for new game
     *
     * @param players
     *              The list of players initialized by the default configuration
     * @return A new instance of CELocalGame
     */
    @Override
    public LocalGame createLocalGame(GameState gameState, GamePlayer[] players) {
        if(gameState == null) {
            return new CELocalGame(players);
        }
        return new CELocalGame((CEGameState) gameState);
    }

    /**
     * saveGame, adds this games prepend to the filename
     *
     * @param gameName
     * 				Desired save name
     * @return String representation of the save
     */
    @Override
    public GameState saveGame(String gameName) {
        return super.saveGame(getGameString(gameName));
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /**
     * loadGame, adds this games prepend to the desire file to open and creates the game specific state
     * @param gameName
     * 				The file to open
     * @return The loaded GameState
     */
    @Override
    public GameState loadGame(String gameName){
        String appName = getGameString(gameName);
        super.loadGame(appName);
        Logger.log(TAG, "Loading: " + gameName);
        return (GameState) new CEGameState((CEGameState) Saving.readFromFile(appName, this.getApplicationContext()));
    }
}