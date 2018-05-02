package it.polimi.se2018.game.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.BoardAnalyzer;
import it.polimi.se2018.game.PublicObjectiveCard;

public class MediumShadesCard extends PublicObjectiveCard
{


    public MediumShadesCard ()
    {
        super ("Medium Shades", "Sets of 3 & 4 values anywhere", 2);
    }

    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(3,4) * points;
    }
}
