package it.polimi.se2018.events;

import it.polimi.se2018.model.Game;

import java.io.Serializable;

public abstract class Message implements Serializable
{
    private Game game;

    public Message(Game game)
    {
        this.game = game;
    }

    public Game getGame()
    {
        return game;
    }
}
