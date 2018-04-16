package it.polimi.se2018.Game;

public class CellRestriction
{
    private boolean active;         //false = nessuna restrizione  true = restrizione attiva
    private boolean type;           //false = valore               true = colore
    private int     val;            //valore nel caso di restrizione di valore
    private Color   col;            //colore in caso di restrizione di colore

    public CellRestriction()        //crea una restrizione non attiva (nessuna restrizione)
    {
        active = false;
    }

    public CellRestriction(int value)        //crea una restrizione di valore
    {
        val = value;
        type = false;   //restrizione di valore
        active = true;
    }

    public CellRestriction(Color color)        //crea una restrizione di colore
    {
        col = color;
        type = true;    //restrizione di colore
        active = true;
    }

    public boolean getType()    {return type;}
    public boolean isValue()    {return !type;}
    public boolean isColor()    {return type;}
    public boolean isActive()   {return  active;}

    //restituisce il valore della restrizione oppure -1 in caso di restrizione di colore
    public  int getValue()
    {
        if(isValue())     return val; //restrizione di valore
        else return -1;
    }

    //restituisce il colore della restrizione oppure null in caso di restrizione di valore
    public  Color getColor()
    {
        if(isColor())     return col; //restrizione di valore
        else return null;
    }
}