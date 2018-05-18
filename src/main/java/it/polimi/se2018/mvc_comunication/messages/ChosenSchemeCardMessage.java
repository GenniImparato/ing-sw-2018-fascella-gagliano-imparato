package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class ChosenSchemeCardMessage extends Message
{
    private Player player;
    private Board schemeCard;

    public ChosenSchemeCardMessage(Model model, Player player, Board schemeCard)
    {
        super(model);
        this.player = new Player(player);
        this.schemeCard = new Board(schemeCard);
    }

    public Player getPlayer()
    {
        return player;
    }

    public Board getSchemeBoards()
    {
        return schemeCard;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}