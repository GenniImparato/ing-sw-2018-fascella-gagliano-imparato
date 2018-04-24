package it.polimi.se2018.game.publicobjectivecards;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.PublicObjectiveCard;

public class ColorDiagonalsCard extends PublicObjectiveCard {

    public ColorDiagonalsCard ()
    {
        super ("Color Diagonals", "Count of diagonally adjacent same color dice", 1);
    }

    @Override
    public int score(Board board) {
        return 0;
    }
}
