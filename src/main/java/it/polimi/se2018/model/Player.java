package it.polimi.se2018.model;

import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.utils.Color;

import java.io.Serializable;

/**
 * Class used to represent a Player.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class Player implements Serializable
{
    private Board                   board;              //player's board
    private PrivateObjectiveCard    card;               //player's private objective card
    private int                     favorTokens=3;           //player's number of favor token
    private int                     score=0;
    private Color                   color;              //color of the player's board and the score marker
    private String                  nickname;

    private boolean                 ready;

    private boolean                 schemeCardChosen = false;
    private int[]                   schemeCardIndices;

    /**
     * Constructor that creates a Player and sets his nickname
     * @param nickname nickname of the Player
     */
    public Player(String nickname, Color color)
    {
        this.nickname = nickname;
        this.color = color;
        this.schemeCardIndices = new int[4];
        this.ready = false;

        card = new PrivateObjectiveCard(Color.getRandomColor());
        board = new Board();
    }

    /**
     * Copy constructor
     * @param player source instance to be cloned
     */
    public Player(Player player)
    {
        this.board = new Board(player.board);
        this.card  = new PrivateObjectiveCard(player.card);
        this.favorTokens = player.favorTokens;
        this.score = player.score;
        this.color = player.color;
        this.nickname = player.nickname;
        this.ready = player.ready;
        this.schemeCardChosen = player.schemeCardChosen;
        this.schemeCardIndices = player.schemeCardIndices;
    }

    public boolean hasChoosenSchemeCard()
    {
        return schemeCardChosen;
    }

    public void setSchemeCardIndex(int num, int schemeIndex)
    {
        if(num >= 0 && num <= 3)
            this.schemeCardIndices[num] = schemeIndex;
    }

    public int getSchemeCardIndex(int num)
    {
        if(num >= 0 && num <=3)
            return this.schemeCardIndices[num];
        else return -1;
    }

    /**
     * Assigns a Board passed by parameter to the Player
     * @param board Board to associate to the Player
     */
    public void setBoard (Board board)
    {
        this.board = board;
        this.favorTokens = board.getDifficulty();
        schemeCardChosen = true;
    }

    public void setReady(boolean ready)
    {
        this.ready = ready;
    }

    public boolean isReady()
    {
        return ready;
    }

    /**
     * Returns the nickname of the Player
     * @return a String that is the nickname of the Player
     */
    public String getNickname()
    {
        return nickname;
    }

    /**
     * Returns the Board of the Player
     * @return Board of the Player
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Returns the score of the Player
     * @return score of the Player
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Returns the favor tokens available of the Player
     * @return an Integer representing the number of favor tokens
     */
    public int getTokens ()
    {
        return favorTokens;
    }

    /**
     * Returns the PrivateObjectiveCard of the Player
     * @return PrivateObjectiveCard owned by the Player
     */
    public PrivateObjectiveCard getPrivateObjectiveCard()
    {
        return card;
    }

    /**
     * Returns the color of the private objective card owned by the Player
     * @return Color associated to the Player
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * It increments the score of the Player due to his PrivateObjectiveCard.
     * The increment of the score depends exclusively from the color associated to the private objective card
     * of the Player.
     */
    public void incrementPrivateScore()
    {
        score += card.score(this.getBoard());
        score += favorTokens;
    }


}
