package it.polimi.se2018.model;

import it.polimi.se2018.utils.Color;

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

    private int         id;

    private static int  totalNumberOfDice = 0;

    /**
     * Constructor that creates a Die of the color passed by parameter and random value (range 1 to 6)
     * @param color color of the Die
     */
    public Die(Color color)
    {
        this.color = color;
        this.id = totalNumberOfDice;
        totalNumberOfDice++;
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
        this.id = die.id;
    }

    /**
     * Sets the value of the Die
     * @param value fixed value of the die
     */
    public void setValue(int value)
    {
        if (value>=1 && value<=6)
            this.value = value;
    }

    /**
     * Increments the value of the Die only if the current value associated is less than 6
     */
    public void incrementValue()
    {
        if (this.value<6)
            this.value++;
    }

    /**
     * Decrements the value of the Die only if the current value associated is higher than 1
     */
    public void decrementValue()
    {
        if (this.value>1)
            this.value--;
    }

    /**
     * Returns the value of the Die
     * @return value of the DIe
     */
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

    /**
     * This method checks if the Die passed by parameter is exactly the same of this Die.
     * Concretely it checks if the Id are the same.
     * @param die die to confront
     * @return true if the dice are the same, false otherwise
     */
    public boolean isSameDie(Die die)
    {
        return this.id == die.id;
    }
}
