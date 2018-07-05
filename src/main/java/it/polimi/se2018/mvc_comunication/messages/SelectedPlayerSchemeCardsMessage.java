package it.polimi.se2018.mvc_comunication.messages;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.MessageVisitor;

/**
 * This Message subclass specifies the kind of message. In this case it represents the message sent by a source (the Model)
 * when a four player scheme cards have been genereted so that the player can choose his one.
 */
public class SelectedPlayerSchemeCardsMessage extends Message
{
    private Player  player;
    private Board[] schemeCards;

    /**
     * Constructor that calls the constructor of the superclass (that sets the current Model). It sets the player associated
     * to the schemeCards and the schemeCards
     * @param model copy of the current Model of the Game
     * @param player player who received the four scheme cards
     * @param schemeCards four scheme cards to choose from
     */
    public SelectedPlayerSchemeCardsMessage(Model model, Player player, Board[] schemeCards)
    {
        super(model);
        this.player = new Player(player);
        this.schemeCards = new Board[4];
        for(int i=0; i<4; i++)
            this.schemeCards[i] = new Board(schemeCards[i]);
    }

    /**
     * Returns the addressee player of the  scheme cards
     * @return addressee player of the  scheme cards
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Returns the schemeCards associated to a fixed player
     * @return scheme cards associated to a fixed player
     */
    public Board[] getSchemeBoards()
    {
        return schemeCards;
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
