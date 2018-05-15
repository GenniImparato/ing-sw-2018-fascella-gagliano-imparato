package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.controller.BoardAnalyzer;

public class ColorDiagonalsCard extends PublicObjectiveCard
{
    public ColorDiagonalsCard ()
    {
        super ("Color Diagonals", "Count of diagonally adjacent same color dice", 1);
    }

    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}
