package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

/**
 * This Event subclass specifies the kind of event. In this case it represents the event sent by a source (the View)
 * when a player wants to use a tool card.
 */
public class UseToolCardEvent extends Event
{
    private int cardNum;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current View).
     * It sets the card that the player wants to use
     * @param view copy of the view related to the player
     * @param cardNum integer associated to a tool card
     */
    public UseToolCardEvent(ViewInterface view, int cardNum)
    {
        super(view);
        this.cardNum = cardNum;
    }

    /**
     * Returns the integer associated to a tool card
     * @return integer associated to a tool card
     */
    public int getCardNum()
    {
        return cardNum;
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
