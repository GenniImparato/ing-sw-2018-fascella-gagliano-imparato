package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

/**
 * This Event subclass specifies the kind of event. In this case it represents the event sent by a source (the View)
 * when a player selects his scheme card.
 */
public class SelectSchemeCardEvent extends Event
{
    private int choice;
    private String nickname;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current View). It sets the choice (i.e. integer
     * associated to a precise scheme card), and the nickname of the player
     * @param view copy of the view related to the player
     * @param playerNickname nickname of the player
     * @param choice number associated to a schemecard
     */
    public SelectSchemeCardEvent(ViewInterface view, String playerNickname, int choice)
    {
        super(view);
        this.choice = choice;
        this.nickname = playerNickname;
    }

    /**
     * Returns the choice of the player
     * @return choice of the player
     */
    public int getChoice()
    {
        return choice;
    }

    /**
     * Returns the nickname of the player
     * @return nickname of the player
     */
    public String getPLayerNickame()
    {
        return nickname;
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
