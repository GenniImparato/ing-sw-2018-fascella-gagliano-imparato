package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;
import it.polimi.se2018.mvc_comunication.events.PlayerReadyEvent;

public class PlayerReadyMessage extends Message
{
    private Player player;
    private boolean ready;

    public PlayerReadyMessage(Model model, Player player, boolean ready)
    {
        super(model);
        this.player = player;
        this.ready = ready;
    }

    public Player getPlayer()
    {
        return player;
    }

    public boolean isReady()
    {
        return ready;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
