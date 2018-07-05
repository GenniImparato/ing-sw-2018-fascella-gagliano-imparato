package it.polimi.se2018.mvc_comunication.events;


import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;
/**
 * @deprecated
 */
public class StartGameEvent extends Event
{
    public StartGameEvent(ViewInterface view)
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
