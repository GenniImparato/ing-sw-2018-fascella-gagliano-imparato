package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;
import it.polimi.se2018.model.PublicObjectiveCard;

public class LightShadesCard extends PublicObjectiveCard
{

    public LightShadesCard()
    {
        super("Light Shades", "Sets of 1 & 2 values anywhere", 2);
    }

    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);
        return boardAnalyzer.countSets(1, 2) * points;
    }
}
