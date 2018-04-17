package it.polimi.se2018.game.publicobjectivecards;
import it.polimi.se2018.game.Matrix;
import it.polimi.se2018.game.PublicObjectiveCard;

public class RowColorVarietyCard extends PublicObjectiveCard {

    public RowColorVarietyCard ()
    {
        super ("Row Variety Card", "Rows with no repeated colors", 6 );
    }

    @Override
    public int score(Matrix matrix) {
        return 0;
    }
}
