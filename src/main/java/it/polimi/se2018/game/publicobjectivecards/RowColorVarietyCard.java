package it.polimi.se2018.game.publicobjectivecards;
import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.PublicObjectiveCard;

public class RowColorVarietyCard extends PublicObjectiveCard {

    public RowColorVarietyCard ()
    {
        super ("Row Color Variety", "Rows with no repeated colors", 6 );
    }

    @Override
    public int score(Board board) {
        return 0;
    }
}
