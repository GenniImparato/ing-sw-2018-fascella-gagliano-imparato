package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a Die has been moved.
 */
public class MovedDieMessage extends Message
{
    private Die die;
    private Player player;
    private int row;
    private int col;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the current player,
     * the moved die and its coordinates (in the board of the player when it has been moved).
     * @param model copy of the current Model of the Game
     * @param die moved die
     * @param player current player
     * @param row first coordinate of the moved die
     * @param column second coordinate of the moved die
     */
    public MovedDieMessage(Model model, Die die, Player player, int row, int column)
    {
        super(model);
        this.die = new Die(die);
        this.player = new Player(player);
        this.row = row;
        this.col = column;
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
     * Returns the moved die
     * @return moved die
     */
    public Die getDie()
    {
        return die;
    }

    /**
     * Returns the first coordinate of the die (in the board of the player where it has been moved)
     * @return first coordinate of the die (in the board of the player where it has been moved)
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Returns the second coordinate of the die (in the board of the player where it has been moved)
     * @return second coordinate of the die (in the board of the player where it has been moved)
     */
    public int getColumn()
    {
        return col;
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