package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Class used to represent a Die
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class Die implements Serializable
{
    private Color       color;
    private int         value;

    /**
     * Constructor that creates a Die of the color passed by parameter and random value (range 1 to 6)
     * @param color color of the Die
     */
    public Die(Color color)
    {
        this.color = color;
        roll();
    }

    /**
     * Copy constructor
     * @param die source instance to be cloned
     */
    public Die(Die die)
    {
        this.color = die.getColor();
        this.value = die.getValue();
    }

    /**
     * Returns the value of the Die
     * @return value of the DIe
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Returns the color of the Die
     * @return color of the Die
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Generates a random value of the Die (range 1 to 6)
     */
    public void roll()
    {
        Random random = new Random();
        value = random.nextInt(6)+1;
    }

    /**
     * Inverts the Die side
     */
    public void invert()
    {
        value = 7 - value;
    }
}
