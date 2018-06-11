package it.polimi.se2018.model.publicobjectivecards;

/**
 * Class used to represent the shade variety public objective card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class ShadeVarietyCard extends PublicObjectiveCard
{
    /**
     * Constructor that calls the constructor of the super class that saves the name
     * and the points associated to the shade variety card
     */
    public ShadeVarietyCard ()
    {
        super ("Shade Variety", 5);
    }

    /**
     * This method is needed in order to use the Visitor Pattern.
     * @param visitor This is the visitor who decides how to visit this card
     * @return An integer returned by the Visitor after visiting this card.
     */
    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}