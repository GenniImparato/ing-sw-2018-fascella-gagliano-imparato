package it.polimi.se2018.model;


public class Cell
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
        restriction = new CellRestriction(cell.getRestriction());
    }


    public CellRestriction getRestriction()
    {
        return  restriction;
    }
}
