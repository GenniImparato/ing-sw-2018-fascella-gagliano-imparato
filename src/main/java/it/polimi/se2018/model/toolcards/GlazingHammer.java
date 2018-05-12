package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.Player;

public class GlazingHammer extends ToolCard
{

    public GlazingHammer ()
    {
        super ("Glazing Hammer", "Re-roll all dice in the Draft Pool. This may only be used on your second turn before drafting", 7);
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
