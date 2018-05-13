package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Enumeration used to represent colors.
 * @author  Generoso Imparato
 */
public enum Color
{
    RED ("\u001b[31;1m", 0, 'R'),
    BLUE ("\u001b[34;1m", 1, 'B'),
    GREEN ("\u001b[32;1m", 2, 'G'),
    YELLOW ("\u001b[33;1m", 3, 'Y'),
    PURPLE ("\u001b[35;1m", 4, 'P');

    private String consoleString;
    private int num;
    private char firstLetter;

    Color(String consoleString, int num, char firstLetter)
    {
        this.consoleString = consoleString;
        this.num = num;
        this.firstLetter = firstLetter;
    }

    /**
     * Gets a random color from those saved in the enum
     * @return random color
     */
    public static Color getRandomColor()
    {
        int rand = new Random().nextInt(5);

        switch(rand)
        {
            case 0: return Color.RED;
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
            case 3: return Color.YELLOW;
            case 4: return Color.PURPLE;
            default: return null;
        }

    }

    /**
     * Converts a String into a Color;
     * returns null if the string doesn't match any color
     * @param string String to convert into a Color
     * @return Color obtained from the String passed by argument
     */
    public static Color getColorFromString(String string)
    {
        if(string.equals("red"))
            return Color.RED;
        else if(string.equals("blue"))
            return Color.BLUE;
        else if(string.equals("yellow"))
            return Color.YELLOW;
        else if(string.equals("purple"))
            return Color.PURPLE;
        else if(string.equals("green"))
            return Color.GREEN;

        return null;
    }

    /**
     * Returns a number associated to a Color
     * @return an Integer representing a Color
     */
    public int getNum()
    {
        return num;
    }

    /**
     * Returns an ANSI code that has to be printed in console to set the current color
     * @return String used to set the current color in the console
     */
    public String getConsoleString()
    {
        return consoleString;
    }


    /**
     * When it's printed in console, it resets the text to the standard color
     * @return a String needed to reset the text to the standard color in the console
     */
    public static String getResetConsoleString() { return new String("\u001b[0m");}

    /**
     * Returns a character representing the first letter of the Color name
     * @return character associated to the Color
     */
    public char getFirstChar ()
    {
        return firstLetter;
    }
}