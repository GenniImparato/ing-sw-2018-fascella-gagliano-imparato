package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.*;

import java.util.ArrayList;

//a class used to render all the elements in the CLI
public abstract class CLIRenderer
{
    protected char[][]            charMatrix;
    protected Color[][]           colorMatrix;

    protected Game                game;

    protected static final int    WIDTH = 220;
    protected static final int    HEIGHT = 35;

    protected ArrayList<CLIElement> elements;

    public CLIRenderer(Game game)
    {
        elements = new ArrayList<>();
        charMatrix  = new char[HEIGHT][WIDTH];
        colorMatrix = new Color[HEIGHT][WIDTH];

        this.game = game;
    }

    //draws everything in the console
    protected void render()
    {
        refresh();

        for(int row=0; row < HEIGHT; row++)
        {
            for(int col=0; col < WIDTH; col++)
            {
                if(colorMatrix[row][col] != null)
                    System.out.print(colorMatrix[row][col].getConsoleString());
                else
                    System.out.print(Color.getResetConsoleString());
                System.out.print(charMatrix[row][col]);
            }
            System.out.println();
        }
    }

    public void setChar(char c, int x, int y)
    {
        charMatrix[y][x] = c;
    }

    public void setColor(Color color, int x, int y)
    {
        colorMatrix[y][x] = color;
    }

    //helper
    //fills the charMatrix with blank character
    protected void initMatrix()
    {
        for( int row=0; row < HEIGHT; row++)
        {
            for(int col=0; col < WIDTH; col++)
            {
                charMatrix[row][col] = ' ';
            }
        }
    }

    //needs to be overridden
    protected abstract void refresh ();

    //helper
    protected void addElement(CLIElement element)
    {
        elements.add(element);
    }

    //helper
    protected void removeAllElements()
    {
        elements.clear();
    }



}