package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class MovedDieMessage extends Message
{
    private Die die;
    private Player player;
    private int row;
    private int col;

    public MovedDieMessage(Model model, Die die, Player player, int row, int column)
    {
        super(model);
        this.die = new Die(die);
        this.player = new Player(player);
        this.row = row;
        this.col = column;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Die getDie()
    {
        return die;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return col;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}