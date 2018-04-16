package it.polimi.se2018.Output;

import it.polimi.se2018.Game.Board;
import it.polimi.se2018.Game.Matrix;
import it.polimi.se2018.Output.Output;

public class ConsoleOutput extends Output
{
    public ConsoleOutput(Board board) {
        super(board);
        brd = board;
    }

    @Override
    public void Refresh()
    {
        for(int row = 0; row< Matrix.ROWS; row++)
        {
            for(int col=0; col<Matrix.COLUMNS; col++)
            {
                if(!brd.getMatrix().getCell(row, col).getRestriction().isActive())
                    System.out.print("[   ]   ");
                else if (brd.getMatrix().getCell(row, col).getRestriction().isValue())
                    System.out.print("[ " +brd.getMatrix().getCell(row, col).getRestriction().getValue()+ " ]   ");
                else if (brd.getMatrix().getCell(row, col).getRestriction().isColor())
                    System.out.print(
                            brd.getMatrix().getCell(row, col).getRestriction().getColor().getConsoleString()
                            +"[   ]   "
                            +"\033[0m");
            }

            System.out.println();
        }
    }
}
