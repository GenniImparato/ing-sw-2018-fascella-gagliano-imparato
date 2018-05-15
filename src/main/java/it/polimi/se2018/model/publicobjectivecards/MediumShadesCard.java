package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.controller.BoardAnalyzer;

public class MediumShadesCard extends PublicObjectiveCard
{
    public MediumShadesCard ()
    {
        super ("Medium Shades", "Sets of 3 & 4 values anywhere", 2);
    }

    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}
