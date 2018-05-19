package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

public class UseToolCardEvent extends Event
{
    private int cardNum;

    public UseToolCardEvent(ViewInterface view, int cardNum)
    {
        super(view);
        this.cardNum = cardNum;
    }

    public int getCardNum()
    {
        return cardNum;
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}
