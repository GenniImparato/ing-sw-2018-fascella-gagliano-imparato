package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;

public class AddedPlayerMessage extends Message
{
    private Player player;

    public AddedPlayerMessage(Model model, Player player)
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