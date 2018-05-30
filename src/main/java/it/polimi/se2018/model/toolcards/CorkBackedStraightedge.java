package it.polimi.se2018.model.toolcards;

public class CorkBackedStraightedge extends ToolCard
{
    public CorkBackedStraightedge ()
    {
        super ("Corck Backed Straightedge", "After drafting, place the die in a spot that is not adjacent to another die", 9);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }
}
