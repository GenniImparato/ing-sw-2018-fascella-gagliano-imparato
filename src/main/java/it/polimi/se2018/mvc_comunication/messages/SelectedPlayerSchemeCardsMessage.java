package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class SelectedPlayerSchemeCardsMessage extends Message
{
    private Player player;
    private Board  firstScheme;
    private Board  secondScheme;

    public SelectedPlayerSchemeCardsMessage(Model model, Player player, Board firstScheme, Board secondScheme)
    {
        super(model);
        this.player = new Player(player);
        this.firstScheme = new Board(firstScheme);
        this.secondScheme = new Board(secondScheme);
    }

    public Player getPlayer()
    {
        return player;
    }

    public Board getFirstSchemeBoard()
    {
        return firstScheme;
    }

    public Board getSecondSchemeBoard()
    {
        return secondScheme;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
