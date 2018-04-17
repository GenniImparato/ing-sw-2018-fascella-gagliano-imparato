package it.polimi.se2018.game;

public class Player
{
    private Board                   board;              //player's board
    private PrivateObjectiveCard    card;               //player's private objective card
    private int                     tokens;             //player's number of favor token
    private Color                   color;              //color of the player's board and the score marker

    public Player()
    {
        board = new Board();
    }

    public Board getBoard() {return board;}
}
