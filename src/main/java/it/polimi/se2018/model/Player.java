package it.polimi.se2018.model;

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
        board = new Board();

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
            tokens = file.getDifficulty();
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
        return tokens;
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
