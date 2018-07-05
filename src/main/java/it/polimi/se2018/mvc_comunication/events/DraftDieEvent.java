package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

/**
 * This Event subclass specifies the kind of event. In this case it represents the event sent by a source (the View)
 * when a player asks to draft a die
 */
public class DraftDieEvent extends Event
{
    private int dieNum;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current View).
     * It sets the num (id to distinguish dice in the draftpool) of the die to draft
     * @param view copy of the view related to the player
     * @param dieNum id of the die in the draftpool
     */
    public DraftDieEvent(ViewInterface view, int dieNum)
    {
        super(view);
        this.dieNum = dieNum;
    }

    /**
     * Returns the id(to distinguish dice in the draftpool) of the die to draft
     * @return id(to distinguish dice in the draftpool) of the die to draft
     */
    public int getDieNum()
    {
        return dieNum;
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
