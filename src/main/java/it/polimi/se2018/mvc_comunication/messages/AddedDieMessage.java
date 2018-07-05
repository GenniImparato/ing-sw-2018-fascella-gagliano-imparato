package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a Die has been added to the Board of a Player. Hence the instance of this class contained the reference of the Player
 * who made the move, the reference of its Board, the reference of the added die and its coordinates.
 */
public class AddedDieMessage extends Message
{
    private Player  player;
    private Board   board;
    private int     row;
    private int     col;
    private Die     die;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets all its attributes
     * thanks to the parameters passed.
     * @param model copy of the model of the player
     * @param player player who wanted to add the Die
     * @param die die added to the player's board
     * @param row first coordinate of the added die
     * @param col second coordinate of the added die
     */
    public AddedDieMessage(Model model, Player player, Die die, int row, int col)
    {
        super(model);
        this.player = new Player(player);
        this.row = row;
        this.col = col;
        this.die = new Die(die);
    }

    /**
     * Returns the player who added the die to its board (i.e. the current player).
     * @return the player who added the die to its board.
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Returns the board of the player who added the die to its board.
     * @return the board of the player who added the die to its board.
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Returns the second coordinate of the added die.
     * @return the second coordinate of the added die.
     */
    public int getColumn()
    {
        return col;
    }

    /**
     * Returns the first coordinate of the added die.
     * @return the second coordinate of the added die.
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Returns the die that has been added from the Player
     * @return die that has been added from the Player
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
