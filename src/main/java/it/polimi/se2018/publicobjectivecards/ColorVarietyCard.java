package it.polimi.se2018.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.PublicObjectiveCard;

public class ColorVarietyCard extends PublicObjectiveCard{

    public ColorVarietyCard ()
    {
        super ("Color Variety", "Sets of one of each other anywhere", 4);
    }

    @Override
    public int score(Board board) {
        return 0;
    }
}
