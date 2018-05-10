package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Game;

public class GameStartedMessage extends Message
{
    public GameStartedMessage(Game game)
    {
        super(game);
    }
}
