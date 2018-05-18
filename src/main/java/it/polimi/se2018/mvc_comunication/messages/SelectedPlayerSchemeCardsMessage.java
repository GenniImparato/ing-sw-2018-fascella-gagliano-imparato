package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

public class SelectedPlayerSchemeCardsMessage extends Message
{
    private Player  player;
    private Board[] schemeCards;

    public SelectedPlayerSchemeCardsMessage(Model model, Player player, Board[] schemeCards)
    {
        super(model);
        this.player = new Player(player);
        this.schemeCards = new Board[4];
        for(int i=0; i<4; i++)
            this.schemeCards[i] = new Board(schemeCards[i]);
    }

    public Player getPlayer()
    {
        return player;
    }

    public Board[] getSchemeBoards()
    {
        return schemeCards;
    }

    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
