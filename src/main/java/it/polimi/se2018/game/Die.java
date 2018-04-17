package it.polimi.se2018.game;

import java.util.Random;

public class Die
{
    private Color       color;
    private int         value;

    public Die()
    {
        Random random = new Random();
        color = Color.getRandomColor();
        value = random.nextInt(6)+1;
    }

    public int getValue()   {return value;}
    public Color getColor() {return color;}
}
