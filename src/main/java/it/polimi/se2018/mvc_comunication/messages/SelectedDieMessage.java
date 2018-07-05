package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a Die has been selected.
 */
public class SelectedDieMessage extends Message
{
    private Die die;
    private Player player;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the current player
     * and the selected die
     * @param model copy of the current Model of the Game
     * @param die selected die
     * @param player current player
     */
    public SelectedDieMessage(Model model, Die die, Player player)
    {
        super(model);
        this.die = new Die(die);
        this.player = new Player(player);
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
     * Returns the selected die
     * @return selected die
     */
    public Die getDie()
    {
        return die;
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
