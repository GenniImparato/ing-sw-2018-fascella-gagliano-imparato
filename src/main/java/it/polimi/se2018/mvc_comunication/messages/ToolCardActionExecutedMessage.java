package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;

public class ToolCardActionExecutedMessage extends Message
{
    private String message;

    public ToolCardActionExecutedMessage(Model model, String message)
    {
        super(model);
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
