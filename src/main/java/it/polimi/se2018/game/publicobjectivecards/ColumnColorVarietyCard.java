package it.polimi.se2018.game.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.BoardAnalyzer;
import it.polimi.se2018.game.PublicObjectiveCard;

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