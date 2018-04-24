package it.polimi.se2018.game;

import java.util.Random;

public class Die
{
    private Color       color;
    private int         value;

    public Die(Color color)
    {
        this.color = color;
        roll();
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
