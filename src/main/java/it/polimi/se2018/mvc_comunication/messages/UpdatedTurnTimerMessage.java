package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class UpdatedTurnTimerMessage extends Message
{
    private int time;

    public UpdatedTurnTimerMessage(Model model, int time)
    {
        super(model);
        this.time = time;
    }

    public int getTime()
    {
        return time;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}