package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.ToolCard;

public class LensCutter extends ToolCard
{
    public LensCutter ()
    {
        super ("Lens Cutter", "After drafting, swap the drafted die with a die from the Round Track", 5);
    }

    @Override
    public void use(Player player) {

    }
}
