package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

/**
 * This Event subclass specifies the kind of event. In this case it represents the event sent by a source (the View)
 * when a player moves a die in his board.
 */
public class MoveSelectedDieEvent extends Event
{
    private int row;
    private int col;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current View). It sets the die coordinates
     * @param view copy of the view related to the player
     * @param row first coordinate of the selected die in the board
     * @param column second coordinate of the selected die in the board
     */
    public MoveSelectedDieEvent(ViewInterface view, int row, int column)
    {
        super(view);
        this.row = row;
        this.col = column;
    }

    /**
     * Returns the first coordinate of the selected die in the board
     * @return first coordinate of the selected die in the board
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Returns the second coordinate of the selected die in the board
     * @return second coordinate of the selected die in the board
     */
    public int getColumn()
    {
        return col;
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