package it.polimi.se2018.view.cli.renderer.elements;

import it.polimi.se2018.model.Card;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.controller.public_objective_cards.PublicObjectiveCard;
import it.polimi.se2018.view.cli.renderer.CLIRenderer;

import java.util.ArrayList;

public class CLIElementCard extends CLIElement
{
    private Card    card;
    private boolean selected;
    private int     index;

    public CLIElementCard (CLIRenderer renderer, Card card, int x, int y, int index, boolean selected)
    {
        super (renderer, x, y, 30, 20);
        this.card=card;
        this.selected = selected;
        this.index = index;
        refresh();

    }

    protected void refresh ()
    {
        initMatrix();
        drawBorders();
        drawName();
        drawTitle();
        drawDescription();
        if(selected)
        {
            drawSelectedColor();
            drawIndex();
        }
        drawOnRenderer();
    }



    //fills the charMatrix to draw the boarder of the Card
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

                else if (col>0 && col<width-1)    //draw the first line separator
                    charMatrix[row][col] = '_';                                                     //of the public cards                     //of the tool cards

                else if (row==8 && (col>0 && col<width-1))                  //draw the second line separator
                    charMatrix[row][col] = '_';
                else
                    charMatrix[row][col] = ' ';
            }
        }
    }

    private void drawSelectedColor()
    {
        for(int row=0; row<height; row++)
        {
            for (int col = 0; col < width; col++)
            {
                colorMatrix[row][col] = Color.RED;
            }
        }
    }

    private void drawIndex()
    {
        colorMatrix[18][15] = Color.RED;
        charMatrix[18][15] = Integer.toString(index).charAt(0);
    }

    private void drawName()
    {
        String name = card.getName();
        int row=1;

        for (int i=0; i<name.length(); i++)
        {
            if (i<26)
                row=6;
            else if (i<26*2)
                row=7;
            charMatrix[row][2+i%26]=name.charAt(i);
        }
    }

    private void drawTitle ()
    {
        /*ArrayList<String> titleCard = new ArrayList<>();
        if (card instanceof PublicObjectiveCard)
        {
            titleCard.add("P U B L I C");
            titleCard.add("O B J E C T I V E");
            titleCard.add("C A R D");
        }

        for (int i=0; i<titleCard.size(); i++)
        {
            for (int j=0; j<titleCard.get(i).length(); j++)
                charMatrix[i+1][j+2] = titleCard.get(i).charAt(j);
        }*/


    }

    private void drawDescription()
    {
        String description = "  ";
        int row=1;

        for (int i=0; i<description.length(); i++)
        {
            if (i<26)
                row=9;
            else if (i<26*2)
                row=10;
            else if (i<26*3)
                row=11;
            else if (i<26*4)
                row=12;
            charMatrix[row][2 + i%26]=description.charAt(i);
        }
    }


}
