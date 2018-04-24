package it.polimi.se2018.game;

public class CellRestriction
{
    private boolean active;         //false = no restriction       true = restriction active
    private boolean type;           //false = value restriction    true = color restriction
    private int     val;            //value stored in case of value restriction
    private Color   col;            //color stored in case of color restriction

    //create a non active restriction (no restriction)
    public CellRestriction()
    {
        active = false;
    }

    //create a value restriction
    public CellRestriction(int value)
    {
        val = value;
        type = false;   //value restriction
        active = true;
    }

    //create a color restriction
    public CellRestriction(Color color)
    {
        col = color;
        type = true;    //color restriction
        active = true;
    }

    //create a clone of the cellRestriction passed by arguments
    public CellRestriction(CellRestriction cellRestriction)
    {
        active = cellRestriction.active;
        type = cellRestriction.type;
        val = cellRestriction.val;
        col = cellRestriction.col;
    }

    private boolean getType()
    {
        return type;
    }


    public boolean isValue()
    {
        if(isActive())
            return !getType();
        else                    //if the restriction is not active it's not a value restriction
            return false;
    }

    //return true if the restriction is active and it's a color restriction
    public boolean isColor()
    {
        if(isActive())
            return getType();
        else                    //if the restriction is not active it's not a color restriction
            return false;
    }

    public boolean isActive()
    {
        return  active;
    }

    //return the value of the restriction or -1 in case of color restriction
    public int getValue()
    {
        if(isValue())     return val;
        else return -1;
    }

    //return the color of the restriction or null in case of value restriction
    public Color getColor()
    {
        if(isColor())     return col; //value restriction
        else return null;
    }
}