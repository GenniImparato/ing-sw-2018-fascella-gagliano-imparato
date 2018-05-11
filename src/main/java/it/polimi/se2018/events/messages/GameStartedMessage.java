package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.view.cli.CLIMessageParser;

public class GameStartedMessage extends Message
{
    public GameStartedMessage(Game game)
    {
        super(game);
    }

    @Override
    public void beParsed(CLIMessageParser parser)
    {
        parser.parseMessage(this);
    }
}
