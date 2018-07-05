package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * to update the initial timer (the lobby timer).
 */
public class UpdatedStartTimerMessage extends Message
{
    private int time;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the updated time.
     * @param model copy of the current Model of the Game
     * @param time update value of the timer
     */
    public UpdatedStartTimerMessage(Model model, int time)
    {
        super(model);
        this.time = time;
    }

    /**
     * Returns the updated time
     * @return updated time
     */
    public int getTime()
    {
        return time;
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
