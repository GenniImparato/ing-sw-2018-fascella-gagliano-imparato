package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

public class DraftDieEvent extends Event
{
    private int dieNum;

    public DraftDieEvent(ViewInterface view, int dieNum)
    {
        super(view);
        this.dieNum = dieNum;
    }

    public int getDieNum()
    {
        return dieNum;
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}
