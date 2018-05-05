package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.CellRestriction;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;

public class CLIElementBoard extends CLIElement
{
    private Board board;
    private boolean selected;

    public CLIElementBoard(CLIRenderer renderer, Board board, int x, int y, boolean selected)
    {
        super(renderer, x, y, 51, 21);
        this.board = board;
        this.selected = selected;
        refresh();
    }

    public void refresh()
    {
        drawCells();
        drawOnRenderer();
        drawDice();
    }

    //helper
    //init a single cell of the board
    private void drawCell(int x, int y, int num, CellRestriction restriction)
    {
        for (int row=y; row < 6 + y; row++)
        {
            for(int col=x; col < x + 11  ; col++)
            {
                if ((row == y || row == 6 + y -1) &&
                        (col > x && col < x + 11 - 1))        //if the character is at the top or bottom border
                {
                    if(col % 2 == 0)                          //sets a ' ' on even x positions on the top and bottom border
                        charMatrix[row][col] = '_';
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

                if(selected)
                    colorMatrix[row][col] = Color.RED;
            }
        }

        if(selected)
        {
            charMatrix[y +1][x + 5] =  Integer.toString(num).charAt(0);
            colorMatrix[y + 1][x + 5] = null;

            if(num >= 10)
            {
                charMatrix[y + 1][x + 6] =  Integer.toString(num).charAt(1);
                colorMatrix[y + 1][x + 6] = null;
            }
        }

        if(restriction.isValue()) {
            charMatrix[y + 1][x + 1] = Integer.toString(restriction.getValue()).charAt(0);
            colorMatrix[y + 1][x + 1] = null;
        }
        else if(restriction.isColor())
        {
            charMatrix[y + 1][x + 1] = restriction.getColor().getFirstChar();
            colorMatrix[y + 1][x + 1] = restriction.getColor();
        }
    }

    //init all the matrix
    private void drawCells()
    {
        int cellNum = 0;

        for(int row=0; row<Board.ROWS; row++)
        {
            for(int col=0; col<Board.COLUMNS; col++)
            {
                drawCell(col*10, row*5, cellNum, board.getCell(row, col).getRestriction());
                cellNum++;
            }
        }
    }

    private void drawDice()
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
