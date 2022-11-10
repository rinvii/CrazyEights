package edu.up.cs301.crazyeights;

import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

public class CELocalGame extends LocalGame {
    //Tag for logging
    private static final String TAG = "CELocalGame";

    public CELocalGame() {
        super();
        super.state = new CEGameState();
    }

    public CELocalGame(CEGameState ceGameState){
        super();
        super.state = new CEGameState(ceGameState);
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new CEGameState((CEGameState) state));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
