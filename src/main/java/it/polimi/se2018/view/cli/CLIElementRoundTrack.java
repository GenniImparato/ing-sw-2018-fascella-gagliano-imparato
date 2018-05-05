package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.RoundTrack;

public class CLIElementRoundTrack extends CLIElement
{
    public CLIElementRoundTrack(CLIRenderer renderer, RoundTrack roundTrack, int x, int y)
    {
        super(renderer, x, y, 75, 10);
        refresh();
    }

    public void refresh()
    {
        drawBorders();
        drawOnRenderer();
    }

    private void drawBorders()
    {
        for(int row=0; row<height; row++)
        {
            for(int col=0; col<width; col++)
            {
                if((col == 0 || col == 74) && row != 0)
                    charMatrix[row][col] = '|';
                else if ((row == 0 || row == 9) && col != 0)
                    charMatrix[row][col] = '_';
                else
                    charMatrix[row][col] = ' ';
            }
        }
    }

}
