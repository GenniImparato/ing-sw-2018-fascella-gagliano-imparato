package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.ViewInterface;

/**
 * This Event subclass specifies the kind of event. In this case it represents the event sent by a source (the View)
 * when a player is ready to play.
 */
public class PlayerReadyEvent extends Event
{
    private String nickname;
    private boolean ready;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current View). It sets the nickname of the player
     * and his status (ready or !ready)
     * @param view copy of the view related the player
     * @param nickname nickname of the player
     * @param ready if true the player is ready, if false the player is not ready to play
     */
    public PlayerReadyEvent(ViewInterface view, String nickname, boolean ready)
    {
        super(view);
        this.nickname = nickname;
        this.ready = ready;
    }

    /**
     * Returns the nickname of the player ready or not ready to play
     * @return nickname of the player ready or not ready to play
     */
    public String getNickname()
    {
        return nickname;
    }

    /**
     * Tells if the player is ready or not
     * @return true if the player is ready, false otherwise
     */
    public boolean isReady()
    {
        return ready;
    }

    /**
     * This method lets a parser parse this event
     * @param visitor parser that parses the event
     */
    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}
