package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Player;

public class CLIElementPlayer extends CLIElement
{

    private boolean showObjective;
    private Player player;

    public CLIElementPlayer (CLIRenderer renderer, Player player, int x, int y, boolean showObjective)
    {
        super (renderer, x, y, 51, 6);
        this.showObjective = showObjective;
        this.player = player;
        refresh();
    }

    protected void refresh ()
    {
        drawBorders();
        drawNickname();
        drawPrivateObjectiveCard();
        drawTokens();
        drawOnRenderer();
    }

    private void drawNickname()
    {
        for(int i=0; i<player.getNickname().length(); i++)
            charMatrix[1][i+1] = player.getNickname().charAt(i);
    }

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

    private void drawPrivateObjectiveCard()
    {
        String string1 = "P R I V A T E";
        for(int i = 0; i<string1.length(); i++)
            charMatrix[1][i+width-17] = string1.charAt(i);

        String string2 = "O B J E C T I V E";
        for(int i = 0; i<string2.length(); i++)
            charMatrix[2][i+width-19] = string2.charAt(i);

        for(int i=1; i<height; i++)
            charMatrix[i][width-21] = '|';

        for(int j=width-14; j<width-6; j++)
        {
            if (showObjective)
            {
                charMatrix[4][j]='═';
                colorMatrix[4][j]=player.getPrivateObjectiveCard().getColor();
            }
            else
                charMatrix[4][j]='X';
        }
    }

    private void drawTokens ()
    {
        for (int i=0; i<player.getTokens(); i++)
            charMatrix[3][i*4+5] = 'O';
    }


}
