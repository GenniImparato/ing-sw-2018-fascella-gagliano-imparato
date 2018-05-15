package it.polimi.se2018.view.cli.renderer;

import it.polimi.se2018.model.*;
import it.polimi.se2018.utils.Color;

//a class used to render all the elements in the CLI
public abstract class CLIRenderer
{
    protected char[][]            charMatrix;
    protected Color[][]           colorMatrix;

    protected Model model;

    protected int                 width;
    protected int                 height;


    public CLIRenderer(Model model, int width, int height)
    {
        this.width=width;
        this.height=height;
        charMatrix  = new char[height][width];
        colorMatrix = new Color[height][width];

        this.model = model;
    }

    //draws everything in the console
    protected void render()
    {
        refresh();

        for(int row=0; row < height; row++)
        {
            for(int col=0; col < width; col++)
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
        for( int row=0; row < height; row++)
        {
            for(int col=0; col < width; col++)
            {
                charMatrix[row][col] = ' ';
            }
        }
    }

    //needs to be overridden
    protected abstract void refresh ();
}