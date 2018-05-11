package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;

public class ColumnShadeVarietyCard extends PublicObjectiveCard {

    public ColumnShadeVarietyCard ()
    {
        super ("Column Shade Variety", "Columns with no repeated values", 4);
    }

    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countColumns(BoardAnalyzer.VALUE) * points;
    }
}