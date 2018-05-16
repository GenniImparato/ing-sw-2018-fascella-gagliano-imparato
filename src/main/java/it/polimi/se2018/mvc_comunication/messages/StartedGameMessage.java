package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;

public class StartedGameMessage extends Message
{
    public StartedGameMessage(Model model)
    {
        super(model);
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
