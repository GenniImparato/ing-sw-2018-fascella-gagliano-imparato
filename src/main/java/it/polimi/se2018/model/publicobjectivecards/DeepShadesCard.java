package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;
import it.polimi.se2018.model.PublicObjectiveCard;

public class DeepShadesCard extends PublicObjectiveCard {

    public DeepShadesCard ()
    {
        super ("Deep Shades", "Sets of 5 & 6 values anywhere", 2);
    }

    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(5,6) * points;
    }
}