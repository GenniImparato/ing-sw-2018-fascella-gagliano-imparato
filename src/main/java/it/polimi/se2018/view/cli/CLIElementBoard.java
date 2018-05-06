package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.CellRestriction;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;

//a CLIElement that represents a Board and can be rendered by a CLIRender on the CLI
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

    @Override
    //fills all the matrix and renders on the CLIRenderer
    protected void refresh()
    {
        drawCells();
        drawOnRenderer();
        drawDice();
    }

    //helper
    //fills the matrix to draw a single Cell of the Board
    //the Cell is drawn at (x,y) offset
    //num is the number displayed on the top of each Cell when the Board is selected
    //restriction is the restriction of the single cell
    private void drawCell(int x, int y, int num, CellRestriction restriction)
    {
        for (int row=y; row < 6 + y; row++)
        {
            for(int col=x; col < x + 11  ; col++)
            {
                if ((row == y || row == 6 + y -1) &&
                        (col > x && col < x + 11 - 1))        //if the character is at the top or bottom border
                {

                        charMatrix[row][col] = '_';           //sets a '_' on the top and bottom border
                }
                else if (col == x || col == x + 11 -1)        //if the character is on the left or right border
                {
                    if(row == 0)
                        charMatrix[row][col] = ' ';           //sets a ' ' on the first row
                    else
                        charMatrix[row][col] = '|';           //sets a '|' on the others
                }
                else
                {
                    charMatrix[row][col] = ' ';               //sets ' ' on every position not on the border
                }

                if(selected)
                    colorMatrix[row][col] = Color.RED;        //draws in RED, if the Board is selected
            }
        }

        if(selected)                                          //draws the index of the Cell if selected
        {
            charMatrix[y +1][x + 5] =  Integer.toString(num).charAt(0);     //draws the first digit
            colorMatrix[y + 1][x + 5] = null;

            if(num >= 10)
            {
                charMatrix[y + 1][x + 6] =  Integer.toString(num).charAt(1);        //draws the second digit
                colorMatrix[y + 1][x + 6] = null;                                   //if there are 2 digits
            }
        }

        if(restriction.isValue()) {                            //draws the value in case of value restriction
            charMatrix[y + 1][x + 1] = Integer.toString(restriction.getValue()).charAt(0);
            colorMatrix[y + 1][x + 1] = null;
        }
        else if(restriction.isColor())                         //draws the color in case of color restriction
        {
            charMatrix[y + 1][x + 1] = restriction.getColor().getFirstChar();
            colorMatrix[y + 1][x + 1] = restriction.getColor();
        }
    }

    //fills the matrices to draw all the cells of the Board
    private void drawCells()
    {
        int cellNum = 0;

        for(int row=0; row<Board.ROWS; row++)
        {
            for(int col=0; col<Board.COLUMNS; col++)
            {
                drawCell(col*10, row*5, cellNum, board.getCell(row, col).getRestriction());
                cellNum++;                      //increases the index passed to the next cell
            }
        }
    }

    //draws all the dice contained in the Board at the correct position
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
