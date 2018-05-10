package it.polimi.se2018.controller.gameactions;

import it.polimi.se2018.model.Game;

public abstract class GameAction
{
    Game game;

    public GameAction(Game game)
    {
        this.game = game;
    }

    public Game getGame()
    {
        return game;
    }
}
