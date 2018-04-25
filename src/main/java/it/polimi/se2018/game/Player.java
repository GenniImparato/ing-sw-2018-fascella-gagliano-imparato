package it.polimi.se2018.game;

import it.polimi.se2018.files.SagradaSchemeCardFile;

public class Player
{
    private Board                   board;              //player's board
    private PrivateObjectiveCard    card;               //player's private objective card
    private int                     tokens=0;           //player's number of favor token
    private int                     score=0;
    private Color                   color;              //color of the player's board and the score marker
    private String                  nickname;

    public Player(String nickname)
    {
        this.nickname = nickname;

        card = new PrivateObjectiveCard(Color.getRandomColor());

        try
        {
            board = new SagradaSchemeCardFile("resources/schemecards/3-Water of Life.sagradaschemecard").generateBoard();
        }
        catch(Exception e)
        {
            board = new Board();
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
        score += tokens;
    }

    public void incrementScore(PublicObjectiveCard card)
    {
        score += card.score(this.getBoard());
    }
}
