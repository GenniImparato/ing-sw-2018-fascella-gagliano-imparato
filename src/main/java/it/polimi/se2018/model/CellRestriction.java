package it.polimi.se2018.model;

import it.polimi.se2018.utils.Color;

/**
 * Class used to represent the restriction of each Cell
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class CellRestriction
{
    private boolean active;         //false = no restriction       true = restriction active
    private boolean type;           //false = value restriction    true = color restriction
    private int     val;            //value stored in case of value restriction
    private Color col;            //color stored in case of color restriction

    /**
     * Constructor that creates a non active restriction (no restriction)
     */
    public CellRestriction()
    {
        active = false;
    }

    /**
     * Constructor that creates a value restriction
     * @param value value of the restriction
     */
    public CellRestriction(int value)
    {
        val = value;
        type = false;   //value restriction
        active = true;
    }

    /**
     * Constructor that creates a color restriction
     * @param color color of the restriction
     */
    public CellRestriction(Color color)
    {
        col = color;
        type = true;    //color restriction
        active = true;
    }

    /**
     * Copy constructor
     * @param cellRestriction source instance to be cloned
     */
    public CellRestriction(CellRestriction cellRestriction)
    {
        active = cellRestriction.active;
        type = cellRestriction.type;
        val = cellRestriction.val;
        col = cellRestriction.col;
    }

    /**
     * Returns the type of the restriction (false is color, true is value)
     * @return type of the restriction (false is color, true is value)
     */
    private boolean getType()
    {
        return type;
    }

    /**
     * Returns true if it's a value restriction, false otherwise
     * @return true if it's a value restriction, false otherwise
     */
    public boolean isValue()
    {
        if(isActive())
            return !getType();
        else                    //if the restriction is not active it's not a value restriction
            return false;
    }

    /**
     * Returns true if it's a color restriction, false otherwise
     * @return true if it's a color restriction, false otherwise
     */
    public boolean isColor()
    {
        if(isActive())
            return getType();
        else                    //if the restriction is not active it's not a color restriction
            return false;
    }

    /**
     * Returns true if it's active, false otherwise
     * @return true if it's active, false otherwise
     */
    public boolean isActive()
    {
        return  active;
    }

    /**
     * Returns the value of the restriction or -1 in case of color restriction
     * @return the value of the restriction or -1 in case of color restriction
     */
    public int getValue()
    {
        if(isValue())     return val;
        else return -1;
    }

    /**
     * Returns the color of the restriction or null in case of value restriction
     * @return the color of the restriction or null in case of value restriction
     */
    public Color getColor()
    {
        if(isColor())     return col; //value restriction
        else return null;
    }
}