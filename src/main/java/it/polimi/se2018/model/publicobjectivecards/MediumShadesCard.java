package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;

/**
 * Class used to represent the medium shades public objective card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class MediumShadesCard extends PublicObjectiveCard
{


    public MediumShadesCard ()
    {
        super ("Medium Shades", "Sets of 3 & 4 values anywhere", 2);
    }

    /**
     * Calculates the score related to this public objective card and to the Board
     * @param board Board where to calculate the score associated to the PublicObjectiveCard
     * @return score of the card depended on the Board passed by parameter
     */
    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(3,4) * points;
    }
}
