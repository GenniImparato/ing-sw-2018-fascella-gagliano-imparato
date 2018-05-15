package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.controller.BoardAnalyzer;

public class DeepShadesCard extends PublicObjectiveCard {

    public DeepShadesCard ()
    {
        super ("Deep Shades", "Sets of 5 & 6 values anywhere", 2);
    }

    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}