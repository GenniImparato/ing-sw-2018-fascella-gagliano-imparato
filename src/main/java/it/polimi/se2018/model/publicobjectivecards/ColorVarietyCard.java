package it.polimi.se2018.model.publicobjectivecards;

/**
 * Class used to represent the color variety public objective card.
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class ColorVarietyCard extends PublicObjectiveCard{

    /**
     * Constructor that calls the constructor of the super class that saves the name
     * and the points associated to the color variety card
     */
    public ColorVarietyCard ()
    {
        super ("Color Variety", 4);
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
