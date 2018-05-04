package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Die;

public class CLIElementDie extends CLIElement
{
    private Die die;

    //create a die element for the CLI at given coordinates
    public CLIElementDie(CLIRenderer renderer, Die die, int x, int y)
    {
        super(renderer, x, y, 7, 4);
        this.die = die;
        init();
        initColor();
        initValue();
        draw();
    }

    private void init()
    {
        charMatrix[0] = new char[] {' ', '_', ' ', '_', ' ', '_', ' '};
        charMatrix[1] = new char[] {'|', ' ', ' ', ' ', ' ', ' ', '|'};
        charMatrix[2] = new char[] {'|', ' ', ' ', ' ', ' ', ' ', '|'};
        charMatrix[3] = new char[] {'|', '_', ' ', '_', ' ', '_', '|'};
    }

    private void initColor()
    {
        for(int row=0; row < height; row++)
        {
            for(int col=0; col < width; col++)
            {
                colorMatrix[row][col] = die.getColor();
            }
        }
    }

    private void initValue()
    {
        charMatrix[2][2] = new Integer(die.getValue()).toString().charAt(0);
        charMatrix[2][4] = die.getColor().getFirstChar();

    }

}
