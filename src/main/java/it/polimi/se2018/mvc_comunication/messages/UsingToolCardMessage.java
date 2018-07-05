package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Card;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a player is using a tool card.
 */
public class UsingToolCardMessage extends Message
{
    private Card card;
    private Player player;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the tool card that
     * a player is using and the player associated (current player).
     * @param model copy of the current Model of the Game
     * @param card tool card that the current player is using
     * @param player who is using the tool card
     */
    public UsingToolCardMessage(Model model, Card card, Player player)
    {
        super(model);

        this.card = card;
        this.player = new Player(player);
    }

    /**
     * Returns the tool card that the current player is using
     * @return tool card that the curren player is using
     */
    public Card getCard()
    {
        return card;
    }

    /**
     * Returns the current player (who is using the tool card)
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
