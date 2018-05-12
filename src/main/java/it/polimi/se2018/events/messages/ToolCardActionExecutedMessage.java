package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.view.cli.CLIMessageParser;

public class ToolCardActionExecutedMessage extends Message
{
    private String message;

    public ToolCardActionExecutedMessage(Game game, String message)
    {
        super(game);
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public void beParsed(CLIMessageParser parser)
    {
        parser.parseMessage(this);
    }
}
