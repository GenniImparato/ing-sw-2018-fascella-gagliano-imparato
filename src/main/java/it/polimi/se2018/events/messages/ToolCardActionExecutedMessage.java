package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.view.cli.CLIMessageParser;

public class ToolCardActionExecutedMessage extends Message
{
    private String message;

    public ToolCardActionExecutedMessage(Model model, String message)
    {
        super(model);
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
