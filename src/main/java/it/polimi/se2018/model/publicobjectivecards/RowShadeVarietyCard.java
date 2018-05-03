package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;
import it.polimi.se2018.model.PublicObjectiveCard;

public class RowShadeVarietyCard extends PublicObjectiveCard {

    public RowShadeVarietyCard ()
    {
        super ("Row Shade Variety", "Rows with no repeated values", 5);
    }

    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countRows(BoardAnalyzer.VALUE) * points;
    }
}
