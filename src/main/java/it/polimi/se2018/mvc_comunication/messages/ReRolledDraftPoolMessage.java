package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class ReRolledDraftPoolMessage extends Message
{
    private Player player;

    public ReRolledDraftPoolMessage(Model model, Player player)
    {
        super(model);
        this.player = player;
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
