package it.polimi.se2018.model.publicobjectivecards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.controller.BoardAnalyzer;

public class ColumnColorVarietyCard extends PublicObjectiveCard {

    public ColumnColorVarietyCard()
    {
        super ("Column Color Variety", "Columns with no repeated colors", 5);
    }

    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}