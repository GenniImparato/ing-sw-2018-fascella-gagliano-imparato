package it.polimi.se2018.game.publicobjectivecards;

import it.polimi.se2018.game.Matrix;
import it.polimi.se2018.game.PublicObjectiveCard;

public class LightShadesCard extends PublicObjectiveCard {

    public LightShadesCard ()
    {
        super ("Light Shades", "Sets of 1 & 2 values anywhere", 2);
    }

    @Override
    public int score(Matrix matrix) {
        return 0;
    }
}
