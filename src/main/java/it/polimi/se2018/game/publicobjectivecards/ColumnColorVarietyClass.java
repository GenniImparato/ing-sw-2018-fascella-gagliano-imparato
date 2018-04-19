package it.polimi.se2018.game.publicobjectivecards;

import it.polimi.se2018.game.Matrix;
import it.polimi.se2018.game.PublicObjectiveCard;

public class ColumnColorVarietyClass extends PublicObjectiveCard {

    public ColumnColorVarietyClass ()
    {
        super ("Column Color Variety", "Columns with no repeated colors", 5);
    }

    @Override
    public int score(Matrix matrix) {
        return 0;
    }
}
