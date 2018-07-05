package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;
import it.polimi.se2018.mvc_comunication.events.PlayerReadyEvent;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a player gets ready to play or is not ready anymore
 */
public class PlayerReadyMessage extends Message
{
    private Player player;
    private boolean ready;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the player
     * who changed state ( ready or !ready)
     * @param model copy of the current Model of the Game
     * @param player player who got ready to play or who's not ready anymore
     * @param ready if the value is 1 the player got ready, otherwise it isn't ready anymore
     */
    public PlayerReadyMessage(Model model, Player player, boolean ready)
    {
        super(model);
        this.player = player;
        this.ready = ready;
    }

    /**
     * Returns the player who got ready to play or who's not ready anymore
     * @return player who got ready to play or who's not ready anymore
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Tells if a player is ready to play or not
     * @return 1 means player ready, 0 means player not ready anymore
     */
    public boolean isReady()
    {
        return ready;
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
