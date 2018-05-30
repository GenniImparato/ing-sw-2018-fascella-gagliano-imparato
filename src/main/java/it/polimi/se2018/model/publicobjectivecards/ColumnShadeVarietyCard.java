package it.polimi.se2018.model.publicobjectivecards;


/**
 * Class used to represent the column shade variety public objective card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class ColumnShadeVarietyCard extends PublicObjectiveCard {

    public ColumnShadeVarietyCard ()
    {
        super ("Column Shade Variety", 4);
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