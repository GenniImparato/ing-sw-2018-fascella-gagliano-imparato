package it.polimi.se2018.output;

import it.polimi.se2018.game.*;

public class ConsoleOutput extends Output
{
    private Game game;

    public ConsoleOutput(Game game)
    {
        this.game = game;
    }

    @Override
    public void refresh()
    {
        Board b = game.getPlayer(0).getBoard();

        for(int row = 0; row< Board.ROWS; row++)
        {
            for(int col = 0; col< Board.COLUMNS; col++)
            {
                if(!b.getCell(row, col).getRestriction().isActive())
                    System.out.print("[   ]   ");
                else if (b.getCell(row, col).getRestriction().isValue())
                    System.out.print("[ " +b.getCell(row, col).getRestriction().getValue()+ " ]   ");
                else if (b.getCell(row, col).getRestriction().isColor())
                    System.out.print(
                            b.getCell(row, col).getRestriction().getColor().getConsoleString()
                            +"[   ]   "
                            +"\033[0m");
            }

            System.out.println();
        }
    }
}
