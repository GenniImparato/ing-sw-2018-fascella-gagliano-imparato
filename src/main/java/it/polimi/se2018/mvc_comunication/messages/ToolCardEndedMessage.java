package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Card;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class ToolCardEndedMessage extends Message
{
    private Card    card;

    public ToolCardEndedMessage(Model model, Card card)
    {
        super(model);
        this.card = card;
    }

    public Card getCard()
    {
        return card;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
