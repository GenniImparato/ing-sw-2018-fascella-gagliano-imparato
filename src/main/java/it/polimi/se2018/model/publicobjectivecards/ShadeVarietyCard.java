package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.controller.BoardAnalyzer;

public class ShadeVarietyCard extends PublicObjectiveCard
{
    public ShadeVarietyCard ()
    {
        super ("Shade Variety", "Sets of one of each value anywhere", 5);
    }

    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}