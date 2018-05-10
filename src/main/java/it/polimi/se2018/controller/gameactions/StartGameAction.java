package it.polimi.se2018.controller.gameactions;

import it.polimi.se2018.model.Game;

public class StartGameAction extends GameAction
{
    public StartGameAction(Game game)
    {
        super(game);
        game.startGame();
    }
}
