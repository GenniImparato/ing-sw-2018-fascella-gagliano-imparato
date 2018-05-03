package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;
import it.polimi.se2018.model.PublicObjectiveCard;

public class ShadeVarietyCard extends PublicObjectiveCard
{

    public ShadeVarietyCard ()
    {
        super ("Shade Variety", "Sets of one of each value anywhere", 5);
    }

    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(BoardAnalyzer.VALUE) * points;
    }
}