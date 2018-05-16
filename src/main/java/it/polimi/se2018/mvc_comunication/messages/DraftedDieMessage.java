package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;

public class DraftedDieMessage extends Message
{
    private Die die;
    private Player player;

    public DraftedDieMessage(Model model, Die die, Player player)
    {
        super(model);
        this.die = new Die(die);
        this.player = new Player(player);
    }

    public Player getPlayer()
    {
        return player;
    }

    public Die getDie()
    {
        return die;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
