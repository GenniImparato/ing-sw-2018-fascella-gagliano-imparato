package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.BoardAnalyzer;

/**
 * class used to represent the color diagonals public objective card
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class ColorDiagonalsCard extends PublicObjectiveCard {

    /**
     * Constructor that set the name, the description and the points of the Card
     */
    public ColorDiagonalsCard ()
    {
        super ("Color Diagonals", "Count of diagonally adjacent same color dice", 1);
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
        return boardAnalyzer.countColorDiagonals();
    }
}
