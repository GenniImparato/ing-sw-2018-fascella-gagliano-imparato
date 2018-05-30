package it.polimi.se2018.model.publicobjectivecards;

public class ColorDiagonalsCard extends PublicObjectiveCard
{
    public ColorDiagonalsCard ()
    {
        super ("Color Diagonals", 1);
    }

    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}
