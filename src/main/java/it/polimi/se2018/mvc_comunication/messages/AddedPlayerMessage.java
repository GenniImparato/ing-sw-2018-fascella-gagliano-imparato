package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a Player has been added to the Game.
 */
public class AddedPlayerMessage extends Message
{
    private Player player;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the player
     * that has been added.
     * @param model Copy of the current Model of the Game
     * @param player Latest added Player.
     */
    public AddedPlayerMessage(Model model, Player player)
    {
        super(model);
        this.player = new Player(player);
    }

    /**
     * Returns the latest added player
     * @return latest added player
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