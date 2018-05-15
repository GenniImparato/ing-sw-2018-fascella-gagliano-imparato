package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.view.cli.CLIMessageParser;

public class GameStartedMessage extends Message
{
    public GameStartedMessage(Model model)
    {
        super(model);
    }

    @Override
    public void beParsed(CLIMessageParser parser)
    {
        parser.parseMessage(this);
    }
}
