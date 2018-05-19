package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;

import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

public class IncrementDraftedDieEvent extends Event
{
    private boolean type;

    public static final boolean INCREMENT = true;
    public static final boolean DECREMENT = false;

    public IncrementDraftedDieEvent(ViewInterface view, boolean type)
    {
        super(view);
        this.type = type;
    }

    public boolean isIncrement()
    {
        return type == INCREMENT;
    }

    public boolean isDecrement()
    {
        return type == DECREMENT;
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}
