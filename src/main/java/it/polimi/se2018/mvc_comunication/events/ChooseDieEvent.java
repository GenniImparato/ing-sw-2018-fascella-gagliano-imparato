package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.ViewInterface;

public class ChooseDieEvent extends Event
{
    private int round;

    public ChooseDieEvent(ViewInterface view, int round)
    {
        super(view);
        this.round = round;
    }

    public int getRound()
    {
        return round;
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}