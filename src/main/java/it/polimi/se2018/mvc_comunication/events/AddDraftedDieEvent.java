package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.view.View;

public class AddDraftedDieEvent extends Event
{
    private int row;
    private int col;

    public AddDraftedDieEvent(View view, int row, int column)
    {
        super(view);
        this.row = row;
        this.col = column;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return col;
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}