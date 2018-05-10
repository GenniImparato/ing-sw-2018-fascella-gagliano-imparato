package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.Game;

public class BeginTurnAction extends GameAction
{
    @Override
    public void execute(Game game)
    {
        game.beginPlayerTurn();
    }
}
