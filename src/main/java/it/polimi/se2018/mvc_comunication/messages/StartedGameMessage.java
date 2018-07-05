package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when has just started.
 */
public class StartedGameMessage extends Message
{
    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model)
     * @param model copy of the current Model of the Game
     */
    public StartedGameMessage(Model model)
    {
        super(model);
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
