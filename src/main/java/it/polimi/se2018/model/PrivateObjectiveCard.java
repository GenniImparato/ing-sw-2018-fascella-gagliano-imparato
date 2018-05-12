package it.polimi.se2018.model;

import java.io.Serializable;

/**
 * Class used to represent a PrivateObjectiveCard
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class PrivateObjectiveCard extends Card
{
    private Color color;

    /**
     * Constructor that creates a PrivateObjectiveCard of a given color
     * @param color color associated to the PrivateObjectiveCard
     */
    public PrivateObjectiveCard (Color color)
    {
        super ("Private card", "--" );

        this.color=color;
    }

    /**
     * Copy constructor
     * @param card source instance to be cloned
     */
    public PrivateObjectiveCard(PrivateObjectiveCard card)
    {
        super(card);
        this.color = card.getColor();
    }

    /**
     * Calculates the score due to the Board passed by parameter
     * and to the color of the PrivateObjectiveCard.
     * @param board Board needed to calculate the score
     * @return an Integer representing the score related to the Private Objective Card
     * and associated to a Board passed by parameter
     */
    public int score (Board board)
    {
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);

        return boardAnalyzer.sumValuesOfColor(color);

    }

    /**
     * Returns the color associated to the PrivateObjectiveCard
     * @return color of the PrivateObjectiveCard
     */
    public Color getColor ()
    {
        return color;
    }
}
