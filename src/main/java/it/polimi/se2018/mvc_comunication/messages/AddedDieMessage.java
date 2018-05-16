package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class AddedDieMessage extends Message
{
    private Player  player;
    private Board   board;
    private int     row;
    private int     col;
    private Die     die;

    public AddedDieMessage(Model model, Player player, Die die, int row, int col)
    {
        super(model);
        this.player = new Player(player);
        this.row = row;
        this.col = col;
        this.die = new Die(die);
    }

    public Player getPlayer()
    {
        return player;
    }

    public Board getBoard()
    {
        return board;
    }

    public int getCol()
    {
        return col;
    }

    public int getRow()
    {
        return row;
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
