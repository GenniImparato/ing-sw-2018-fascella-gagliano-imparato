package it.polimi.se2018.output;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.Game;
import it.polimi.se2018.game.Matrix;

public class ConsoleOutput extends Output
{
    public ConsoleOutput() {
    }

    @Override
    public void refresh()
    {
        Game game = Game.getInstance();
        Matrix m = game.getPlayer(0).getBoard().getMatrix();

        for(int row = 0; row< Matrix.ROWS; row++)
        {
            for(int col=0; col<Matrix.COLUMNS; col++)
            {
                if(!m.getCell(row, col).getRestriction().isActive())
                    System.out.print("[   ]   ");
                else if (m.getCell(row, col).getRestriction().isValue())
                    System.out.print("[ " +m.getCell(row, col).getRestriction().getValue()+ " ]   ");
                else if (m.getCell(row, col).getRestriction().isColor())
                    System.out.print(
                            m.getCell(row, col).getRestriction().getColor().getConsoleString()
                            +"[   ]   "
                            +"\033[0m");
            }

            System.out.println();
        }
    }
}
