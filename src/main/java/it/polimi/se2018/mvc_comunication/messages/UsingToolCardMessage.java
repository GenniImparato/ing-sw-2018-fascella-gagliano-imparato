package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Card;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class UsingToolCardMessage extends Message
{
    private Card card;
    private Player player;

    public UsingToolCardMessage(Model model, Card card, Player player)
    {
        super(model);

        this.card = card;
        this.player = new Player(player);
    }

    public Card getCard()
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
