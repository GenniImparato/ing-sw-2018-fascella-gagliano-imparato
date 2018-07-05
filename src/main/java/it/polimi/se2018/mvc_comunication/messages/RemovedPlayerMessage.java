package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a player has been removed from the game.
 */
public class RemovedPlayerMessage extends Message
{
    private Player player;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the removed player
     * @param model copy of the current Model of the Game
     * @param player player who's been removed from te Game
     */
    public RemovedPlayerMessage(Model model, Player player)
    {
        super(model);
        this.player = new Player(player);
    }

    /**
     * Returns the removed player from the game
     * @return removed player from the game
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
