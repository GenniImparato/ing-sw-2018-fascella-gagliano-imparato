package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Card;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a tool card has ended its actions,
 */
public class ToolCardEndedMessage extends Message
{
    private Card    card;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the tool card that
     * has just been used.
     * @param model copy of the current Model of the Game
     * @param card toolcard that has just been used
     */
    public ToolCardEndedMessage(Model model, Card card)
    {
        super(model);
        this.card = card;
    }

    /**
     * Returns the tool card that has just been used
     * @return tool card that has just been used
     */
    public Card getCard()
    {
        return card;
    }

    /**
     * This method lets a parser parse this message
     * @param visitor parser that parses the message
     */
    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
