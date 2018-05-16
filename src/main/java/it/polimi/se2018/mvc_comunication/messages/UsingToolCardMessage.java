package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.toolcards.ToolCard;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class UsingToolCardMessage extends Message
{
    private ToolCard card;
    private Player player;

    public UsingToolCardMessage(Model model, ToolCard card, Player player)
    {
        super(model);

        try
        {
            this.card = card.getClass().newInstance();
        }
        catch(InstantiationException | IllegalAccessException e)
        {
        }

        this.player = new Player(player);
    }

    public ToolCard getCard()
    {
        return card;
    }

    public Player getPlayer()
    {
        return player;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
