package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.CannotAddDieException;
import it.polimi.se2018.model.Game;

public class AddDieToBoardAction extends GameAction
{
    int row, col;

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
            game.getCurrentPlayer().getBoard().addDie(game.getLastDraftedDie(), row, col);
            executed = true;
        }
        catch(CannotAddDieException e)
        {
            errorMessage = e.getMessage();
        }
    }
}
