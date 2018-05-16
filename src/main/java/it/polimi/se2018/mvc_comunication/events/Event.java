package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.view.View;

public abstract class Event implements EventVisitable
{
    private View    view;

    public Event(View view)
    {
        this.view = view;
    }

    public View getView()
    {
        return view;
    }

    public abstract void acceptVisitor(EventVisitor visitor);
}
