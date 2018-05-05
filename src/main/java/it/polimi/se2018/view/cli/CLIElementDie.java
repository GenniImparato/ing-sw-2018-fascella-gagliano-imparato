package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Die;

//a CLIElement that represents a Die and can be rendered by a CLIRender on the CLI
public class CLIElementDie extends CLIElement
{
    private Die die;

    //creates a die element for the CLI at given coordinates
    public CLIElementDie(CLIRenderer renderer, Die die, int x, int y)
    {
        super(renderer, x, y, 7, 4);
        this.die = die;
        refresh();
    }

    @Override
    //fills the Matrices and renders on the CLIRenderer
    public void refresh()
    {
        drawBorder();
        drawColor();
        drawValue();
        drawOnRenderer();
    }

    //fills the charMatrix to represent the border of the Die
    private void drawBorder()
    {
        charMatrix[0] = new char[] {' ', '_', '_', '_', '_', '_', ' '};
        charMatrix[1] = new char[] {'|', ' ', ' ', ' ', ' ', ' ', '|'};
        charMatrix[2] = new char[] {'|', ' ', ' ', ' ', ' ', ' ', '|'};
        charMatrix[3] = new char[] {'|', '_', '_', '_', '_', '_', '|'};
    }

    //fills the colorMatrix to represent the color of the Die
    private void drawColor()
    {
        for(int row=0; row < height; row++)
        {
            for(int col=0; col < width; col++)
            {
                colorMatrix[row][col] = die.getColor();
            }
        }
    }

    //fills the charMatrix to represent the value of the Die
    private void drawValue()
    {
        switch(die.getValue())
        {
            case 1:
                charMatrix[2][3] = 'O';
                break;
            case 2:
                charMatrix[1][3] = 'O';
                charMatrix[3][3] = 'O';
                break;
            case 3:
                charMatrix[1][3] = 'O';
                charMatrix[2][3] = 'O';
                charMatrix[3][3] = 'O';
                break;
            case 4:
                charMatrix[1][1] = 'O';
                charMatrix[3][1] = 'O';
                charMatrix[3][5] = 'O';
                charMatrix[1][5] = 'O';
                break;
            case 5:
                charMatrix[1][1] = 'O';
                charMatrix[3][1] = 'O';
                charMatrix[3][5] = 'O';
                charMatrix[1][5] = 'O';
                charMatrix[2][3] = 'O';
                break;
            case 6:
                charMatrix[1][1] = 'O';
                charMatrix[3][1] = 'O';
                charMatrix[3][5] = 'O';
                charMatrix[1][5] = 'O';
                charMatrix[2][1] = 'O';
                charMatrix[2][5] = 'O';
                break;
        }
    }

}
