package edu.up.cs301.crazyeights.players;

import android.view.View;

import java.util.ArrayList;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.players.GameHumanPlayer;

public class CESecondHumanPlayer extends GameHumanPlayer {
    private ArrayList<CECard> cardsInHand;

    private static final String TAG = "CEHumanPlayer";

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public CESecondHumanPlayer(String name) {
        super(name);
        this.cardsInHand = new ArrayList<>();
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

    @Override
    public ArrayList<CECard> getCardsInHand() {
        return null;
    }

    @Override
    public CECard addCardInHand(CECard card) {
        return null;
    }
}
