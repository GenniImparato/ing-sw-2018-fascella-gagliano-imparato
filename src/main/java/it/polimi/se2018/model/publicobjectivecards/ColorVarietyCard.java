package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.controller.BoardAnalyzer;

public class ColorVarietyCard extends PublicObjectiveCard{

    public ColorVarietyCard ()
    {
        super ("Color Variety", "Sets of one of each other anywhere", 4);
    }

    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}
