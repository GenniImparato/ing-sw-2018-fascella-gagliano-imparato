package it.polimi.se2018.mvc_comunication.events;


import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;

public class StartGameEvent extends Event
{
    public StartGameEvent(View view)
    {
        super(view);
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}
