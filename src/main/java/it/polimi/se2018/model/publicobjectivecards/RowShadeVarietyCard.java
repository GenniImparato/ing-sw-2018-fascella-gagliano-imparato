package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.controller.BoardAnalyzer;

public class RowShadeVarietyCard extends PublicObjectiveCard {

    public RowShadeVarietyCard ()
    {
        super ("Row Shade Variety", "Rows with no repeated values", 5);
    }

    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}
