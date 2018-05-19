package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

import java.io.Serializable;

public abstract class Event implements EventVisitable, Serializable
{
    private ViewInterface    view;

    public Event(ViewInterface view)
    {
        this.view = view;
    }

    public ViewInterface getView()
    {
        return view;
    }

    public void setView(ViewInterface view)
    {
        this.view = view;
    }
}
