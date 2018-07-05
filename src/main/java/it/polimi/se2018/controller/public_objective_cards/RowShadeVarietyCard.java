package it.polimi.se2018.controller.public_objective_cards;

import it.polimi.se2018.model.Board;

/**
 * Class used to represent the row shade variety public objective card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class RowShadeVarietyCard extends PublicObjectiveCard
{
    public RowShadeVarietyCard ()
    {
        super ("Row Shade Variety", 5);
    }

    /**
     * This method is used to calculate the score of the Board only caring about the row shade variety public objective card
     * @param board The Board to analyze to obtain the score from this card.
     * @return An integer representing the score of the Board relate to the row shade variety public objective card.
     */
    @Override
    public int score(Board board)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countRows(BoardAnalyzer.VALUE) * getPoints();
    }
}
