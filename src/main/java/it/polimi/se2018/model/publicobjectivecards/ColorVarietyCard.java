package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;

/**
 * Class used to represent the color variety public objective card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class ColorVarietyCard extends PublicObjectiveCard{

    public ColorVarietyCard ()
    {
        super ("Color Variety", "Sets of one of each other anywhere", 4);
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
        return boardAnalyzer.countSets(BoardAnalyzer.COLOR) * points;
    }
}
