package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.RoundTrack;

import java.util.ArrayList;

public class CLIElementRoundTrack extends CLIElement
{

    private RoundTrack roundTrack;

    public CLIElementRoundTrack(CLIRenderer renderer, RoundTrack roundTrack, int x, int y)
    {
        super(renderer, x, y, 101, 8);
        this.roundTrack=roundTrack;
        refresh();
    }

    @Override
    public void refresh()
    {
        drawBorders();
        drawTitle();
        drawIndices();
        drawOnRenderer();
        drawDice();
    }

    private void drawBorders()
    {
        for(int row=0; row<height; row++)
        {
            for(int col=0; col<width; col++)
            {
                if((col%10==0) && (row > 1 &&(col>0 && col<width-1 )))
                    charMatrix[row][col] = '|';
                else if((col==0 || col==width-1) && row>0)
                    charMatrix[row][col] = '|';
                else if ((row == 0 || row == height-1) && col != 0 && col != width-1)
                    charMatrix[row][col] = '_';
                else
                    charMatrix[row][col] = ' ';
            }
        }
    }

    private void drawTitle()
    {
        String title = "R O U N D   T R A C K";

        for(int i=0; i<title.length(); i++)
            charMatrix[1][i+1] = title.charAt(i);
    }

    private void drawDice()
    {
        for(int i=0; i<10; i++)
        {
            ArrayList<Die> dice = roundTrack.getDicesAtRound(i);
            if (dice.size()>0)
                new CLIElementDie(renderer, dice.get(0), x+i*10+2, y+2);
        }
    }

    private void drawIndices ()
    {
        for(int i=0; i<10; i++)
            charMatrix[4][i*10+5] = Integer.toString(i).charAt(0);
    }

}
