package it.polimi.se2018.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.PublicObjectiveCard;

public class LightShadesCard extends PublicObjectiveCard {

    public LightShadesCard ()
    {
        super ("Light Shades", "Sets of 1 & 2 values anywhere", 2);
    }

    @Override
    public int score(Board board) {
        return 0;
    }
}
