package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

/**
 * This Event subclass specifies the kind of event. In this case it represents the event sent by a source (the View)
 * when a player ends his turn.
 */
public class EndTurnEvent extends Event
{
    /**
     * Constructor that calls the constructor of the superclass (that sets the current View).
     * @param view copy of the view related to the player
     */
    public EndTurnEvent(ViewInterface view)
    {
        super(view);
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
