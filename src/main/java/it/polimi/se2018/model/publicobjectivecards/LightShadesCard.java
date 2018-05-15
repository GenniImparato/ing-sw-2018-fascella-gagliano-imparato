package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.controller.BoardAnalyzer;

/**
 * Class used to represent the light shades public objective card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class LightShadesCard extends PublicObjectiveCard
{

    public LightShadesCard()
    {
        super("Light Shades", "Sets of 1 & 2 values anywhere", 2);
    }

    /**
     * Calculates the score related to this public objective card and to the Board
     * @param board Board where to calculate the score associated to the PublicObjectiveCard
     * @return score of the card depended on the Board passed by parameter
     */
    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}
