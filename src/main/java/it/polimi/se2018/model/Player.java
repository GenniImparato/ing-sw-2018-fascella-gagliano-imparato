package it.polimi.se2018.model;

import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.utils.Color;

/**
 * Class used to represent a Player.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class Player
{
    private Board                   board;              //player's board
    private PrivateObjectiveCard    card;               //player's private objective card
    private int                     favorTokens=3;           //player's number of favor token
    private int                     score=0;
    private Color color;              //color of the player's board and the score marker
    private String                  nickname;

    /**
     * Constructor that creates a Player and sets his nickname
     * @param nickname nickname of the Player
     */
    public Player(String nickname)
    {
        this.nickname = nickname;

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
    }

    /**
     * If the parameter obj is a Player and his nickname is the same of this instance of Player
     * returns true, false otherwise
     * @param obj instance to compare to this Player
     * @return true if two nicknames are the same, false if two object are not comparable
     * or the nicknames are different.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj.getClass() != this.getClass())
            return false;
        else
            return this.getNickname().equals(((Player)obj).getNickname());
    }

    /**
     * Assigns a Board passed by parameter to the Player
     * @param board Board to associate to the Player
     */
    public void setBoard (Board board)
    {
        this.board = board;
    }

    /**
     * Loads a Board that has been previously saved in a file
     * @param filename name of the file of the Board to load
     */
    public void loadBoardFromFile (String filename)
    {
        try
        {
            SagradaSchemeCardFile file = new SagradaSchemeCardFile(filename);
            board = file.generateBoard();
            favorTokens = file.getDifficulty();
        }
        catch(Exception e)
        {

        }
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
