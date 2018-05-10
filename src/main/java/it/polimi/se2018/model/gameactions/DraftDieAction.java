package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.Game;

public class DraftDieAction extends GameAction
{
    int dieNum;

    public DraftDieAction(int dieNum)
    {
        this.dieNum = dieNum;
    }

    @Override
    public void execute(Game game)
    {
        game.draftDie(dieNum);
    }
}
