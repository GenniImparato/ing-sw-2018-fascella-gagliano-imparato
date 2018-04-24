package it.polimi.se2018.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.PublicObjectiveCard;

public class ShadeVarietyCard extends PublicObjectiveCard {

    public ShadeVarietyCard ()
    {
        super ("Shade Variety", "Sets of one of each value anywhere", 5);
    }

    @Override
    public int score(Board board) {
        return 0;
    }
}
