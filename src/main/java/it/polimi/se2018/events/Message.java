package it.polimi.se2018.events;

import it.polimi.se2018.model.Game;

public abstract class Message
{
    protected Game game;

    public Message(Game game)
    {
        this.game = game;
    }

    public Game getGame()
    {
        return game;
    }
}
