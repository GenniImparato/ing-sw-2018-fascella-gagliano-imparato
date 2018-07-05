package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.ViewInterface;
/**
 * This Event subclass specifies the kind of event. In this case it represents the event sent by a source (the View)
 * when a Player has been disconnected.
 */
public class ClientDisconnectedEvent extends Event
{
    private String nickname;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current View). It sets the nickname of
     * the disconnected player
     * @param view copy of the view relate to the player
     * @param nickname nickname of the player
     */
    public ClientDisconnectedEvent(ViewInterface view, String nickname)
    {
        super(view);
        this.nickname = nickname;
    }

    /**
     * Returns the nickname of the disconnected player
     * @return nickname of the disconnected player
     */
    public String getNickname()
    {
        return nickname;
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
