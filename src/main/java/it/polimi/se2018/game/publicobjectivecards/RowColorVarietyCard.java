package it.polimi.se2018.game.publicobjectivecards;
import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.BoardAnalyzer;
import it.polimi.se2018.game.PublicObjectiveCard;

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
