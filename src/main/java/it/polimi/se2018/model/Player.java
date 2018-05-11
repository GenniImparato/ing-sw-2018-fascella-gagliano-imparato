package it.polimi.se2018.model;

import it.polimi.se2018.files.SagradaSchemeCardFile;

import java.io.Serializable;

public class Player
{
    private Board                   board;              //player's board
    private PrivateObjectiveCard    card;               //player's private objective card
    private int                     favorTokens=3;           //player's number of favor token
    private int                     score=0;
    private Color                   color;              //color of the player's board and the score marker
    private String                  nickname;

    public Player(String nickname)
    {
        this.nickname = nickname;

        card = new PrivateObjectiveCard(Color.getRandomColor());
        board = new Board();
    }

    public Player(Player player)
    {
        this.board = new Board(player.board);
        this.card  = new PrivateObjectiveCard(player.card);
        this.favorTokens = player.favorTokens;
        this.score = player.score;
        this.color = player.color;
        this.nickname = player.nickname;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj.getClass() != this.getClass())
            return false;
        else
            return this.getNickname().equals(((Player)obj).getNickname());
    }

    public void setBoard (Board board)
    {
        this.board = board;
    }

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

    public String getNickname()
    {
        return nickname;
    }

    public Board getBoard()
    {
        return board;
    }

    public int getScore()
    {
        return score;
    }

    public int getTokens ()
    {
        return favorTokens;
    }

    public PrivateObjectiveCard getPrivateObjectiveCard()
    {
        return card;
    }

    public Color getColor()
    {
        return color;
    }

    public void incrementPrivateScore()
    {
        score += card.score(this.getBoard());
        score += favorTokens;
    }

    public void incrementScore(PublicObjectiveCard card)
    {
        score += card.score(this.getBoard());
    }


}
