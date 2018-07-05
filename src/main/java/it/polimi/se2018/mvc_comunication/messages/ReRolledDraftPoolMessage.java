package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when the draftpool has been rerolled.
 */
public class ReRolledDraftPoolMessage extends Message
{
    private Player player;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the current player
     * @param model copy of the current Model of the Game
     * @param player current player
     */
    public ReRolledDraftPoolMessage(Model model, Player player)
    {
        super(model);
        this.player = player;
    }

    /**
     * Returns the current player
     * @return current player
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
