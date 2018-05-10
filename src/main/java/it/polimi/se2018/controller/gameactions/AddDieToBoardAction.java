package it.polimi.se2018.controller.gameactions;

import it.polimi.se2018.model.CannotAddDieException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Game;

public class AddDieToBoardAction extends GameAction
{
    public AddDieToBoardAction(Game game, int row, int col) throws CannotAddDieException
    {
        super(game);
        game.getCurrentPlayer().getBoard().addDie(game.getLastDraftedDie(), row, col);
    }
}
