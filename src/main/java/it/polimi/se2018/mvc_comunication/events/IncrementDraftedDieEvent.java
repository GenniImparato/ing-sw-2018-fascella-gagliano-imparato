package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;

import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

/**
 * This Event subclass specifies the kind of event. In this case it represents the event sent by a source (the View)
 * when a player increments the drafted die.
 */
public class IncrementDraftedDieEvent extends Event
{
    private boolean type;

    public static final boolean INCREMENT = true;
    public static final boolean DECREMENT = false;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current View). It sets the type of the operation
     * (true is increment, false is decrement)
     * @param view copy of the view related to the player
     * @param type  if true the die needs to be incremented, if false it needs to be decremented
     */
    public IncrementDraftedDieEvent(ViewInterface view, boolean type)
    {
        super(view);
        this.type = type;
    }

    /**
     * Tells if it's an increment
     * @return true if the die needs to be incremented, false otherwise
     */
    public boolean isIncrement()
    {
        return type == INCREMENT;
    }

    /**
     * Tells if it's a decrement
     * @return true if the die needs to be decremented, false otherwise
     */
    public boolean isDecrement()
    {
        return type == DECREMENT;
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
