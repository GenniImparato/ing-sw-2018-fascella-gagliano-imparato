package it.polimi.se2018.model;

import java.util.Random;

public enum Color
{
    RED ("\033[31m", 0, 'R'),
    BLUE ("\033[34m", 1, 'B'),
    GREEN ("\033[32m", 2, 'G'),
    YELLOW ("\033[33m", 3, 'Y'),
    PURPLE ("\033[35m", 4, 'P');

    private String consoleString;
    private int num;
    private char firstLetter;

    Color(String consoleString, int num, char firstLetter)
    {
        this.consoleString = consoleString;
        this.num = num;
        this.firstLetter = firstLetter;
    }

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

    //convert a string into a Color
    //return null if the string doesn't mach any color
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

    public int getNum()
    {
        return num;
    }

    public String getConsoleString()
    {
        return consoleString;
    }

    public char getFirstChar ()
    {
        return firstLetter;
    }
}