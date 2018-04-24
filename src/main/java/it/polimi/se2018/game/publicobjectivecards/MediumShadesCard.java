package it.polimi.se2018.game.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.PublicObjectiveCard;

public class MediumShadesCard extends PublicObjectiveCard {


    public MediumShadesCard ()
    {
        super ("Medium Shades", "Sets of 3 & 4 values anywhere", 2);
    }

    @Override
    public int score(Board board) {
        return 0;
    }
}
