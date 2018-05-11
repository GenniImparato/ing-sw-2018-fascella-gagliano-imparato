package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;

public class ColumnColorVarietyCard extends PublicObjectiveCard {

    public ColumnColorVarietyCard()
    {
        super ("Column Color Variety", "Columns with no repeated colors", 5);
    }

    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countColumns(BoardAnalyzer.COLOR) * points;
    }
}