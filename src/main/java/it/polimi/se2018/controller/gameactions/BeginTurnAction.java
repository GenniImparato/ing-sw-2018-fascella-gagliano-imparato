package it.polimi.se2018.controller.gameactions;

import it.polimi.se2018.model.Game;

public class BeginTurnAction extends GameAction
{
    public BeginTurnAction(Game game)
    {
        super(game);
        game.beginPlayerTurn();
    }
}
