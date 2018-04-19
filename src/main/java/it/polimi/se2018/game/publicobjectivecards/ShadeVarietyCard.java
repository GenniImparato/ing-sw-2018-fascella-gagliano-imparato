package it.polimi.se2018.game.publicobjectivecards;

import it.polimi.se2018.game.Matrix;
import it.polimi.se2018.game.PublicObjectiveCard;

public class ShadeVarietyCard extends PublicObjectiveCard {

    public ShadeVarietyCard ()
    {
        super ("Shade Variety", "Sets of one of each value anywhere", 5);
    }

    @Override
    public int score(Matrix matrix) {
        return 0;
    }
}
