package it.polimi.se2018.controller.public_objective_cards;

import it.polimi.se2018.model.Board;

/**
 * Class used to represent the deep shade public objective card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class DeepShadesCard extends PublicObjectiveCard
{
    public DeepShadesCard ()
    {
        super ("Deep Shades", 2);
    }

    /**
     * This method is used to calculate the score of the Board only caring about the deep shades public objective card
     * @param board The Board to analyze to obtain the score from this card.
     * @return An integer representing the score of the Board relate to the deep shades public objective card.
     */
    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(5,6) * getPoints();
    }
}