package edu.up.cs301.crazyeights;

import edu.up.cs301.R;
import edu.up.cs301.crazyeights.ceActionMessage.CEDrawAction;
import edu.up.cs301.crazyeights.ceActionMessage.CEPlaceAction;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

public class CELocalGame extends LocalGame {
    //Tag for logging
    private static final String TAG = "CELocalGame";

    private CEGameState ceGameState;

    public CELocalGame(GamePlayer[] players){
        super();
        this.ceGameState = new CEGameState(players);
        super.state = this.ceGameState;
    }

    public CELocalGame(CEGameState gameState){
        super();
        this.ceGameState = gameState;
        super.state = this.ceGameState;
    }

    public void start(GamePlayer[] players) {
        super.start(players);
        ceGameState.dealCards();
        sendAllUpdatedState();
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

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        GamePlayer player = (GamePlayer) action.getPlayer();
        if (action instanceof CEDrawAction) {
            ceGameState.drawCard(player);
            return true;
        } else if (action instanceof CEPlaceAction) {
            return true;
        }
        return false;
    }
}
