package it.polimi.se2018.game;

public class CellRestriction
{
    private boolean active;         //false = no restriction       true = restriction active
    private boolean type;           //false = value restriction    true = color restriction
    private int     val;            //value stored in case of value restriction
    private Color   col;            //color stored in case of color restriction

    public CellRestriction()        //create a non active restriction (no restriction)
    {
        active = false;
    }

    public CellRestriction(int value)        //create a value restriction
    {
        val = value;
        type = false;   //value restriction
        active = true;
    }

    public CellRestriction(Color color)        //create a color restriction
    {
        col = color;
        type = true;    //color restriction
        active = true;
    }

    public CellRestriction(CellRestriction cellRestriction)        //create a clone of the cellRestriction passed by arguments
    {
        active = cellRestriction.active;
        type = cellRestriction.type;
        val = cellRestriction.val;
        col = cellRestriction.col;
    }

    public boolean getType()    {return type;}

    public boolean isValue()    //return true in case of value restriction
    {
        if(isActive())
            return !getType();
        else                    //if the restriction is not active it's not a value restriction
            return false;
    }

    public boolean isColor()    //return true in case of color restriction
    {
        if(isActive())
            return getType();
        else                    //if the restriction is not active it's not a color restriction
            return false;
    }

    public boolean isActive()   {return  active;}

    //return the value of the restriction or -1 in case of color restriction
    public  int getValue()
    {
        if(isValue())     return val;
        else return -1;
    }

    //return the color of the restriction or null in case of value restriction
    public  Color getColor()
    {
        if(isColor())     return col; //value restriction
        else return null;
    }
}