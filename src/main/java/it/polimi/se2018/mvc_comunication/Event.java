package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.view.View;

import java.io.Serializable;

public abstract class Event implements EventVisitable, Serializable
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
}
