package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Color;

import javax.swing.*;

public abstract class CLIElement
{
    protected   char[][]    charMatrix;
    protected   Color[][]   colorMatrix;
    protected   int         width;
    protected   int         height;
    protected   int         x;
    protected   int         y;
    protected   CLIRenderer renderer;

    public CLIElement(CLIRenderer renderer, int x, int y, int width, int height)
    {
        this.renderer = renderer;
        this.x = x;
        this.y = y;
        this.width  = width;
        this.height = height;
        charMatrix  = new char[height][width];
        colorMatrix = new Color[height][width];
    }

    //draw the current element on the buffer
    public void draw()
    {
        for(int row=0; row < height; row++)
        {
            for(int col=0; col < width; col++)
            {
                renderer.setChar(charMatrix[row][col], x+col, y+row);
                renderer.setColor(colorMatrix[row][col], x+col, y+row);
            }
        }
    }

}
