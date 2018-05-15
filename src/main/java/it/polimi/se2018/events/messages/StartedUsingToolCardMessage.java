package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.toolcards.ToolCard;
import it.polimi.se2018.view.cli.CLIMessageParser;

public class StartedUsingToolCardMessage extends Message
{
    private ToolCard card;

    public StartedUsingToolCardMessage(Model model, ToolCard card)
    {
        super(model);

        try
        {
            this.card = card.getClass().newInstance();
        }
        catch(InstantiationException e)
        {
        }
        catch(IllegalAccessException e)
        {
        }
    }

    public ToolCard getCard()
    {
        return card;
    }

    @Override
    public void beParsed(CLIMessageParser parser)
    {
        parser.parseMessage(this);
    }
}
