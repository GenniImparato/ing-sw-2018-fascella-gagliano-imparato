package it.polimi.se2018.model.publicobjectivecards;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.controller.BoardAnalyzer;

public class RowColorVarietyCard extends PublicObjectiveCard {

    public RowColorVarietyCard ()
    {
        super ("Row Color Variety", "Rows with no repeated colors", 6 );
    }

    @Override
    public int acceptVisitor(PublicObjectiveCardVisitor visitor)
    {
        return visitor.visit(this);
    }
}
