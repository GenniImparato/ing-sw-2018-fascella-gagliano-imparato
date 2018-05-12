package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.toolcards.ToolCard;
import it.polimi.se2018.view.cli.CLIMessageParser;

public class StartedUsingToolCardMessage extends Message
{
    ToolCard card;

    public StartedUsingToolCardMessage(Game game, ToolCard card)
    {
        super(game);

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
