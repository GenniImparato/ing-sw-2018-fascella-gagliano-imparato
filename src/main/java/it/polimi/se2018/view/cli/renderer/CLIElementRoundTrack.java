package it.polimi.se2018.view.cli.renderer;

import it.polimi.se2018.utils.Color;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.RoundTrack;

import java.util.List;

public class CLIElementRoundTrack extends CLIElement
{
    private boolean selected;
    private RoundTrack roundTrack;

    public CLIElementRoundTrack(CLIRenderer renderer, RoundTrack roundTrack, int x, int y, boolean selected)
    {
        super(renderer, x, y, 101, 8);
        this.roundTrack=roundTrack;
        this.selected=selected;
        refresh();
    }

    @Override
    protected void refresh()
    {
        initMatrix();
        drawBorders();
        drawTitle();
        drawIndices();
        if (selected)
        {
            drawSelectedColor();
            drawSelectedIndices();
        }
        drawMultipleDice();

        drawOnRenderer();
        drawSingleDie();
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

    private void drawIndices ()
    {
        for(int i=0; i<10; i++)
            charMatrix[4][i*10+5] = Integer.toString(i).charAt(0);
    }

    private void drawMultipleDice()
    {
        for(int round=0; round < 10; round++)
        {
            List<Die> dice = roundTrack.getDiceAtRound(round);
            if (dice.size() > 1)        //draws only rounds with more than one day
            {
                charMatrix[4][round*10 + 5] = ' ';      //overwrites the round index
                for (int i = 0; i < dice.size(); i++)
                {
                    int row;
                    if (i < 3)
                        row = 0;
                    else if (i < 6)
                        row = 1;
                    else
                        row = 2;

                    charMatrix[row + 3][i % 3 + (i*1)%3 + round * 10 + 3] = Integer.toString(dice.get(i).getValue()).charAt(0);
                    colorMatrix[row + 3][i % 3 + (i*1)%3 + round * 10 + 3] = dice.get(i).getColor();

                }
            }
        }
    }

    private void drawSingleDie()
    {
        for(int round=0; round < 10; round++)
        {
            List<Die> dice = roundTrack.getDiceAtRound(round);
            if (dice.size() == 1)
                new CLIElementDie(renderer, dice.get(0), x + round * 10 + 2, y + 2);
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

    private void drawSelectedIndices()
    {
        for(int i = 0; i<10; i++)
        {
            charMatrix[6][i * 10 + 5] = Integer.toString(i).charAt(0);
            colorMatrix[6][i * 10 + 5] = null;
        }
    }
}
