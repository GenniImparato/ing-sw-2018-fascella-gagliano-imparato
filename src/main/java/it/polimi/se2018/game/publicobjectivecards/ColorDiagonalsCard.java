package it.polimi.se2018.game.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.BoardAnalyzer;
import it.polimi.se2018.game.PublicObjectiveCard;

public class ColorDiagonalsCard extends PublicObjectiveCard {

    public ColorDiagonalsCard ()
    {
        super ("Color Diagonals", "Count of diagonally adjacent same color dice", 1);
    }

    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countColorDiagonals();
    }
}
