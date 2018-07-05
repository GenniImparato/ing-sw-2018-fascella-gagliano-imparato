package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

/**
 * This class is used to represent a generic event in order to respect the MVC pattern.
 */
import java.io.Serializable;

public abstract class Event implements EventVisitable, Serializable
{
    private ViewInterface    view;

    /**
     * Constructor that sets the view that sends the event.
     * @param view Source of the event.
     */
    public Event(ViewInterface view)
    {
        this.view = view;
    }

    /**
     * This method is used to return the saved view.
     * @return view associated to the event.
     */
    public ViewInterface getView()
    {
        return view;
    }

    /**
     * This method is used to save an instance of ViewInterface.
     * @param view view that needs to be set.
     */
    public void setView(ViewInterface view)
    {
        this.view = view;
    }
}
