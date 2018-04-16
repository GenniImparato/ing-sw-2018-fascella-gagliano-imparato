package it.polimi.se2018.Game;

public class Cell
{
    private CellRestriction restriction;

    public Cell()           //crea una cella senza restrizioni
    {
        restriction = new CellRestriction();
    }

    public Cell(int value)      //crea una cella con restrizione di valore
    {
        restriction = new CellRestriction(value);
    }

    public Cell(Color color)        //crea una cella con restrizione di colore
    {
        restriction = new CellRestriction(color);
    }

    public CellRestriction getRestriction() {return  restriction;}
}
