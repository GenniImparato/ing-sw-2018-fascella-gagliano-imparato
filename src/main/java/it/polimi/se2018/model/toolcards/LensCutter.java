package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.Player;

public class LensCutter extends ToolCard
{
    public LensCutter ()
    {
        super ("Lens Cutter", "After drafting, swap the drafted die with a die from the Round Track", 5);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public String action(Game game, int param1, int param2)
    {
        return "";
    }
}
