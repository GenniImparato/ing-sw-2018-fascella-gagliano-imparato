package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.CellRestriction;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;

public class CLIElementBoard extends CLIElement
{
    private Board board;
    public CLIElementBoard(CLIRenderer renderer, Board board, int x, int y)
    {
        super(renderer, x, y, 51, 21);
        this.board = board;

        init();
        draw();

        initDice();
    }

    //helper
    //init a single cell of the board
    private void initCell(int x, int y, CellRestriction restriction)
    {
        for (int row=y; row < 6 + y; row++)
        {
            for(int col=x; col < x + 11  ; col++)
            {
                if ((row == y || row == 6 + y -1) &&
                        (col > x && col < x + 11 - 1))        //if the character is at the top or bottom border
                {
                    if(col % 2 == 0)                          //sets a ' ' on even x positions on the top and bottom border
                        charMatrix[row][col] = ' ';
                    else
                        charMatrix[row][col] = '_';  //sets a '_' on odd positions on te top and bottom border
                }
                else if (col == x || col == x + 11 -1)       //if the character is on the left or right border
                {
                    if(row == 0)
                        charMatrix[row][col] = ' '; //sets a ' ' on even x positions on the top or bottom border
                    else
                        charMatrix[row][col] = '|'; //sets a '_' on odd positions on the left or right border
                }
                else
                {
                    charMatrix[row][col] = ' ';     //sets  ' ' on every position not on the border
                }

                    //colorMatrix[row][col] = color;
            }
        }

        if(restriction.isValue())
            charMatrix[y+1][x+1] = new Integer(restriction.getValue()).toString().charAt(0);
        else if(restriction.isColor())
        {
            charMatrix[y + 1][x + 1] = restriction.getColor().getFirstChar();
            colorMatrix[y + 1][x + 1] = restriction.getColor();
        }
    }

    //init all the matrix
    private void init()
    {
        for(int row=0; row<Board.ROWS; row++)
        {
            for(int col=0; col<Board.COLUMNS; col++)
            {
                initCell(col*10, row*5, board.getCell(row, col).getRestriction());
            }
        }
    }

    private void initDice()
    {
        for(int row=0; row<Board.ROWS; row++)
        {
            for(int col=0; col<Board.COLUMNS; col++)
            {
                Die die = board.getDie(row, col);

                if(die != null)
                {
                    CLIElementDie dieElem = new CLIElementDie(renderer, die, (2 + col * 10) + x, (1 + row * 5) + y);
                }
            }
        }
    }

}
