package it.polimi.se2018.game;

public class Cell
{
    private CellRestriction restriction;

    public Cell()           //create a cell without restriction
    {
        restriction = new CellRestriction();
    }

    public Cell(int value)      //create a cell with a value restriction
    {
        restriction = new CellRestriction(value);
    }

    public Cell(Color color)        //create a cell with a color restriction
    {
        restriction = new CellRestriction(color);
    }

    public Cell(Cell cell)        //copy constructor
    {
        if(cell != null)
            restriction = new CellRestriction(cell.getRestriction());
        else
            restriction = new CellRestriction();
    }


    public CellRestriction getRestriction() {return  restriction;}
}
