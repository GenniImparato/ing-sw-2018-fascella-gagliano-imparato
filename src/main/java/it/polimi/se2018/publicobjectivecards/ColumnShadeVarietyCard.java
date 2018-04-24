package it.polimi.se2018.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.PublicObjectiveCard;

public class ColumnShadeVarietyCard extends PublicObjectiveCard {

    public ColumnShadeVarietyCard ()
    {
        super ("Column Shade Variety", "Columns with no repeated values", 4);
    }

    @Override
    public int score(Board board) {
        return 0;
    }
}
