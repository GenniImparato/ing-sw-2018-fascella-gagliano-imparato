package it.polimi.se2018.model;

import java.io.Serializable;

public class PrivateObjectiveCard extends Card implements Serializable
{
    private Color color;

    public PrivateObjectiveCard (Color color)
    {
        super ("Private card", "--" );

        this.color=color;
    }

    public int score (Board board)
    {
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        return boardAnalyzer.sumValuesOfColor(color);

    }

    public Color getColor ()
    {
        return color;
    }
}
