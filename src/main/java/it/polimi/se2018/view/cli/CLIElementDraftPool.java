package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;

import java.util.ArrayList;

//a CLIElement that represents a DraftPool and can be rendered by a CLIRender on the CLI
public class CLIElementDraftPool extends CLIElement
{
    private DraftPool   draftPool;
    private boolean     selected;

    public CLIElementDraftPool(CLIRenderer renderer, DraftPool draftPool, int x, int y, boolean selected)
    {
        super(renderer, x, y, 75, 8);
        this.draftPool = draftPool;
        this.selected = selected;
        refresh();
    }

    @Override
    //fills the matrices to draw the element and renders it on the CLIRenderer
    public void refresh()
    {
        drawBorders();
        drawTitle();
        if(selected)                    //draws dice indices and draws wit a different color
        {                               //if the element is selected
            drawSelectedColor();
            drawIndices();
        }
        drawOnRenderer();
        drawDice();
    }

    //fills the charMatrix to draw the boarder of the DraftPool
    private void drawBorders()
    {
        for(int row=0; row<height; row++)
        {
            for(int col=0; col<width; col++)
            {
                if((col == 0 || col == width-1) && row != 0)
                    charMatrix[row][col] = '|';
                else if ((row == 0 || row == height-1) && col != 0 && col != width-1)
                    charMatrix[row][col] = '_';
                else
                    charMatrix[row][col] = ' ';
            }
        }
    }

    //changes the color to RED
    private void drawSelectedColor()
    {
        for(int row=0; row<height; row++)
        {
            for(int col=0; col<width; col++)
            {
                colorMatrix[row][col] = Color.RED;
            }
        }
    }

    //fills the charMatrix to draw "DRAFT POOL" on the top left of the border
    private void drawTitle()
    {
        String title = "D R A F T   P O O L";

        for(int i=0; i<title.length(); i++)
            charMatrix[1][i+1] = title.charAt(i);
    }

    //draws each die in the DraftPool at the correct position
    private void drawDice()
    {
        ArrayList<Die> dice = draftPool.getAllDice();

        for(int i = 0; i<dice.size(); i++)
            new CLIElementDie(renderer, dice.get(i), i*8 + 2, 2);
    }

    //fills the matrices to draw the indices of the dice
    private void drawIndices()
    {
        ArrayList<Die> dice = draftPool.getAllDice();

        for(int i = 0; i<dice.size(); i++)
        {
            charMatrix[6][i * 8 + 5] = Integer.toString(i).charAt(0);
            colorMatrix[6][i * 8 + 5] = null;
        }
    }

}
