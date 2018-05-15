package it.polimi.se2018.view.cli.renderer;

import it.polimi.se2018.utils.Color;


//represents a single abstract element that can be displayed by a CLIRenderer on the CLI view
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

    //draw the current element on the buffer matrix in the renderer
    public void drawOnRenderer()
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

    //needs to be overridden by each concrete CLIElement
    //sets the char matrix and the color matrix with the right values representing the object
    //it also should call drawOnRenderer() to actually render the element
    protected abstract void refresh();

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

}
