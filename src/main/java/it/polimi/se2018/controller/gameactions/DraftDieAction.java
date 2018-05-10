package it.polimi.se2018.controller.gameactions;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Game;

public class DraftDieAction extends GameAction
{
    public DraftDieAction(Game game, int dieNum)
    {
        super(game);
        game.draftDie(dieNum);
    }
}
