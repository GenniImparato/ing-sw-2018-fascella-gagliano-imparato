package it.polimi.se2018.game;

import java.util.Random;

public enum Color
{
    RED ("\033[31m", 0),
    BLUE ("\033[34m", 1),
    GREEN ("\033[32m", 2),
    YELLOW ("\033[33m", 3),
    PURPLE ("\033[35m", 4);

    private String consoleString;
    private int num;

    Color(String consoleString, int num)
    {
        this.consoleString = consoleString;
        this.num = num;
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
}