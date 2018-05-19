package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class BegunTurnMessage extends Message
{
    private Player player;

    public BegunTurnMessage(Model model, Player player)
    {
        super(model);
        this.player = new Player(player);
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
