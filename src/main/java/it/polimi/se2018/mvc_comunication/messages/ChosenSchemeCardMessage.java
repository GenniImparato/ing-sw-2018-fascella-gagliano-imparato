package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a player has chosen its scheme card.
 */
public class ChosenSchemeCardMessage extends Message
{
    private Player player;
    private Board schemeCard;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the player
     * who chose the scheme card and its board.
     * @param model copy of the current Model of the game
     * @param player player who chose the schemeCard
     * @param schemeCard scheme card associated to the player
     */
    public ChosenSchemeCardMessage(Model model, Player player, Board schemeCard)
    {
        super(model);
        this.player = new Player(player);
        this.schemeCard = new Board(schemeCard);
    }

    /**
     * Returns the player who chose the scheme card
     * @return player who chose the scheme card
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Returns the scheme board of the player
     * @return scheme board of the player
     */
    public Board getSchemeBoards()
    {
        return schemeCard;
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