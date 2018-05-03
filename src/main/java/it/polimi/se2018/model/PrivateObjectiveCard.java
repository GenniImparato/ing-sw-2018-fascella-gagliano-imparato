package it.polimi.se2018.model;

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
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        return boardAnalyzer.sumValuesOfColor(color);

    }
}
