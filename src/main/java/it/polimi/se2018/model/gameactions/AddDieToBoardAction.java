package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.CannotAddDieException;
import it.polimi.se2018.model.Game;

public class AddDieToBoardAction extends GameAction
{
    int row;
    int col;

    public AddDieToBoardAction(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute(Game game)
    {
        try
        {
            game.addDraftedDieToBoard(row, col);
            executed = true;
        }
        catch(CannotAddDieException e)
        {
            errorMessage = e.getMessage();
        }
    }
}
