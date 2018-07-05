package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.ViewInterface;

/**
 * This Event subclass specifies the kind of event. In this case it represents the event sent by a source (the View)
 * when a Player wants to choose a Die.
 */
public class ChooseDieEvent extends Event
{
    private int round;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current View). It sets the round
     * where to choose the die.
     * @param view copy of the view related to the player
     * @param round  selected round by the player
     */
    public ChooseDieEvent(ViewInterface view, int round)
    {
        super(view);
        this.round = round;
    }

    /**
     * Returns the round selected by the player
     * @return round selected by the player
     */
    public int getRound()
    {
        return round;
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