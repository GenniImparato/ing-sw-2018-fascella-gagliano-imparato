package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when the turn of the player saved in the message has begun.
 */
public class BegunTurnMessage extends Message
{
    private Player player;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the player authorized
     * to make a move.
     * @param model Copy of the current Model of the Game.
     * @param player Authorized player to make a move.
     */
    public BegunTurnMessage(Model model, Player player)
    {
        super(model);
        this.player = new Player(player);
    }

    /**
     * Returns the authorized player to make a move.
     * @return authorized player to make a move.
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * This method lets a parser parse this message
     * @param visitor parser that parses the message
     */
    @Override
    public void acceptVisitor(MessageVisitor visitor)
    {
        visitor.visit(this);
    }
}
