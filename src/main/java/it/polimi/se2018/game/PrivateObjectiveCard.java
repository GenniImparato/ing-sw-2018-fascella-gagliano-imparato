package it.polimi.se2018.game;

public class PrivateObjectiveCard extends Card
{
    private Color color;

    public PrivateObjectiveCard (Color color)
    {
        super ("Private card", "--" );

        this.color=color;
    }

    public int score (Board board)
    {
        return 0;
    }
}
