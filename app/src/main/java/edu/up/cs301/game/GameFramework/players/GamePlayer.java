package edu.up.cs301.game.GameFramework.players;

import java.util.ArrayList;

import edu.up.cs301.crazyeights.CECard;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;

/**
 * A player who plays a (generic) game. Each class that implements a player for
 * a particular game should implement this interface.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */

public interface GamePlayer {

    public abstract ArrayList<CECard> getCardsInHand();
    public abstract CECard addCardInHand(CECard card);
    public abstract void removeCardInHand(CECard card);
    public abstract int getScore();

    // sets this player as the GUI player (implemented as final in the
    // major player classes)
    public abstract void gameSetAsGui(GameMainActivity activity);

    // sets this player as the GUI player (overrideable)
    public abstract void setAsGui(GameMainActivity activity);

    // sends a message to the player
    public abstract void sendInfo(GameInfo info);


    // start the player
    public abstract void start();

    // whether this player requires a GUI
    public boolean requiresGui();

    // whether this player supports a GUI
    public boolean supportsGui();

    //TESTING
    public GameMainActivity getActivity();

    void setScore(int i);

    void setCardsInHand();

    String getName();
}// interface GamePlayer
