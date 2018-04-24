package it.polimi.se2018.game.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.PublicObjectiveCard;

public class RowShadeVarietyCard extends PublicObjectiveCard {

    public RowShadeVarietyCard ()
    {
        super ("Row Shade Variety", "Rows with no repeated values", 5);
    }

    @Override
    public int score(Board board) {
        return 0;
    }
}
