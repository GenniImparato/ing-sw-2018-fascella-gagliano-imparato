package it.polimi.se2018.model;

import java.io.Serializable;

public class Cell implements Serializable
{
    private CellRestriction restriction;

    //create a cell without restriction
    public Cell()
    {
        restriction = new CellRestriction();
    }

    //create a cell with a value restriction
    public Cell(int value)
    {
        restriction = new CellRestriction(value);
    }

    //create a cell with a color restriction
    public Cell(Color color)
    {
        restriction = new CellRestriction(color);
    }

    //copy constructor
    public Cell(Cell cell)
    {
        if(cell != null)
            restriction = new CellRestriction(cell.getRestriction());
        else
            restriction = new CellRestriction();
    }


    public CellRestriction getRestriction()
    {
        return  restriction;
    }
}
