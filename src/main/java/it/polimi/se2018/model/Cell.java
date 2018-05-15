package it.polimi.se2018.model;

import it.polimi.se2018.utils.Color;

/**
 * Class used to represent a Cell of the Player's Board
 * @author Matteo Gagliano
 * @author Carmelo Fascello
 * @author Generoso Imperato
 */
public class Cell
{
    private CellRestriction restriction;

    /**
     * Constructor that creates a Cell without restrictions
     */
    public Cell()
    {
        restriction = new CellRestriction();
    }

    /**
     * Constructor that creates a Cell with a value restriction
     * @param value value of the restriction of the Cell
     */
    public Cell(int value)
    {
        restriction = new CellRestriction(value);
    }

    /**
     * Constructor that creates a Cell with a color restriction
     * @param color color of the restriction
     */
    public Cell(Color color)
    {
        restriction = new CellRestriction(color);
    }

    /**
     * Copy constructor
     * @param cell source instance to be cloned
     */
    public Cell(Cell cell)
    {
        restriction = new CellRestriction(cell.getRestriction());
    }

    /**
     * Returns a cell restriction
     * @return restriction of the Cell
     */
    public CellRestriction getRestriction()
    {
        return  restriction;
    }
}
