package it.polimi.se2018.model.publicobjectivecards;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;

public class RowColorVarietyCard extends PublicObjectiveCard {

    public RowColorVarietyCard ()
    {
        super ("Row Color Variety", "Rows with no repeated colors", 6 );
    }

    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countRows(BoardAnalyzer.COLOR) * points;
    }
}
