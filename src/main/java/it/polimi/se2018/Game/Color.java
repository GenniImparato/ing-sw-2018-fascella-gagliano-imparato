package it.polimi.se2018.Game;

import java.util.Random;

public enum Color
{
    RED ("\033[31m"),
    BLUE ("\033[34m"),
    GREEN ("\033[32m"),
    YELLOW ("\033[33m"),
    PURPLE ("\033[35m");

    private String cString;

    Color(String consoleString) {cString = consoleString;}

    static public Color getRandomColor()
    {
        int rand = new Random().nextInt(5);

        switch(rand)
        {
            case 0: return Color.RED;
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
            case 3: return Color.YELLOW;
            case 4: return Color.PURPLE;
        }

        return null;
    }

    public String getConsoleString() {return cString;}
}
