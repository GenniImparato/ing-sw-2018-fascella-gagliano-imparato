package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;
import it.polimi.se2018.model.PublicObjectiveCard;

public class ColorVarietyCard extends PublicObjectiveCard{

    public ColorVarietyCard ()
    {
        super ("Color Variety", "Sets of one of each other anywhere", 4);
    }

    @Override
    public int score(Board board)
    {

        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(BoardAnalyzer.COLOR) * points;
    }
}
