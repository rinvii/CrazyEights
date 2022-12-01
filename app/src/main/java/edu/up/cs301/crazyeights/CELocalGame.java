package edu.up.cs301.crazyeights;

import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

import edu.up.cs301.crazyeights.ceActionMessage.CEDrawAction;
import edu.up.cs301.crazyeights.ceActionMessage.CEPlaceAction;
import edu.up.cs301.crazyeights.infoMessage.CEGameState;
import edu.up.cs301.crazyeights.players.CEDumbAI;
import edu.up.cs301.crazyeights.players.CEHumanPlayer;
import edu.up.cs301.crazyeights.players.CESmartAI;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

/**
 * This class represents the actual instance of the game.
 * It handles major interactions between the players and the game.
 *
 * @author Ronnie Delos Santos
 * @version November 2022
 */
public class CELocalGame extends LocalGame {
    //Tag for logging
    private static final String TAG = "CELocalGame";

    // the game state
    private CEGameState ceGameState;

    /**
     * Constructor which _creates_ a new instance of
     * CEGameState with argument containing a list of players.
     * This constructor assigns a reference to ceGameState.
     *
     * @param players A list of players
     */
    public CELocalGame(GamePlayer[] players){
        super();
        this.ceGameState = new CEGameState(players);
        super.state = this.ceGameState;
    }

    /**
     * Alternate constructor for when a CEGameState instance already exists (a saved file).
     *
     * @param gameState The state of the game
     */
    public CELocalGame(CEGameState gameState){
        super();
        this.ceGameState = gameState;
        super.state = this.ceGameState;
    }

    /**
     * Starts the game. This method is _important_ in regards to invoking various
     * methods in the CEGameState class. This class is the "initial controller" which
     * is responsible for loading data before anything happens on the end-user.
     *
     * @param players A list of players
     */
    public void start(GamePlayer[] players) {
        super.start(players);
        // deals cards to every player
        ceGameState.setInitialPlayerToMoveTurn();
        ceGameState.dealCards();
//        for (int i = 0; i < 10; i++) {
//            ceGameState.placeCard(ceGameState.getRandomCard());
//        }
        // tell the game that we are ready to display the GUI
        sendAllUpdatedState();
    }

    /**
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player. If the game is not a perfect-information game
     * this method should remove any information from the game that the player is not
     * allowed to know.
     *
     * @param p
     * 			the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new CEGameState((CEGameState) state));
    }

    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return
     * 		true iff the player is allowed to move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return ((CEGameState) state).getPlayerToMove() == playerIdx;
    }



    /**
     * Check if the game is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the game is not over, return null;
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        String winner = null;
        int minPoints=20;
        int currPoints;
          for(int i = 0; i < players.length; i++) {
              if (players[i].getScore() > 20) {
                  for(int j = 0; j < players.length; j++) {
                  currPoints = players[j].getScore();
                  if(currPoints < minPoints){
                      minPoints = currPoints;
                      winner = playerNames[j];
                  }
                  }
                  Log.e("won the round: ", winner);
                  return winner + " wins the game! ";
              }
          }
              return null;
    }

    /**
     * Check if the round is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the round is not over, return null;
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfRoundOver() {
        for(int i = 0; i < players.length; i++) {
            if (players[i].getCardsInHand().size() == 0) {
                Log.e("won the round: ", String.valueOf(playerNames[i]));
                if (checkIfGameOver() != null) {
                    return checkIfGameOver();
                }
                return playerNames[i] + " wins the round! ";
            }
        }
        return null;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @param action
     * 			The move that the player has sent to the game
     * @return
     * 			Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        GamePlayer player = action.getPlayer();

        if (getPlayerIdx(player) != ceGameState.getPlayerToMove()) return false;

        if (action instanceof CEDrawAction) {
            if (player instanceof CEDumbAI || player instanceof CESmartAI) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//            e.printStackTrace();
                }
            }
            ceGameState.drawCard(player);
            ceGameState.setNumPlayerTurn();
            return true;
        } else if (action instanceof CEPlaceAction) {
            if (ceGameState.checkCardEligibility(((CEPlaceAction) action).getSelectedCard())){

                if (player instanceof CEDumbAI || player instanceof CESmartAI) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
//            e.printStackTrace();
                    }
                }
                ceGameState.placeCard(((CEPlaceAction) action).getSelectedCard());
                player.removeCardInHand(((CEPlaceAction) action).getSelectedCard());
                if(checkIfRoundOver() != null){
                    HashMap<Integer, Integer> scores = ceGameState.tallyScores();
                    if (player instanceof CEHumanPlayer) {
                        ((CEHumanPlayer) player).displayScores(scores);
                    }
                    ceGameState.setDrawPile();
                    ceGameState.dealCards();
                    ceGameState.setDiscardPile();
                }
                ceGameState.setNumPlayerTurn();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
