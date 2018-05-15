package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Model;

public class CorkBackedStraightedge extends ToolCard
{
    public CorkBackedStraightedge ()
    {
        super ("Corck-backed Straightedge", "After drafting, place the die in a spot that is not adjacent to another die", 9);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public String action(Model model, int param1, int param2)
    {
        return "";
    }
}
