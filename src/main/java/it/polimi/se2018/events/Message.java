package it.polimi.se2018.events;

import it.polimi.se2018.controller.CLIEventParser;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.view.cli.CLIMessageParser;

import java.io.Serializable;

public abstract class Message implements Serializable
{
    private Game game;

    public Message(Game game)
    {
        this.game = new Game(game);
    }

    public Game getGame()
    {
        return game;
    }

    public abstract void beParsed(CLIMessageParser parser);
}
