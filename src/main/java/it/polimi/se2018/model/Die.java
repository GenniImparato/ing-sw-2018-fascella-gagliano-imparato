package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.Random;

public class Die implements Serializable
{
    private Color       color;
    private int         value;

    public Die(Color color)
    {
        this.color = color;
        roll();
    }

    //copy constructor
    public Die(Die die)
    {
        this.color = die.getColor();
        this.value = die.getValue();
    }

    public int getValue()
    {
        return value;
    }

    public Color getColor()
    {
        return color;
    }

    public void roll()
    {
        Random random = new Random();
        value = random.nextInt(6)+1;
    }

    public void invert()
    {
        value = 7 - value;
    }
}