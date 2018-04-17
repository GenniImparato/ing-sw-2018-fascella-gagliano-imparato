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

    public boolean getType()    {return type;}
    public boolean isValue()    {return !getType();}
    public boolean isColor()    {return getType();}
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