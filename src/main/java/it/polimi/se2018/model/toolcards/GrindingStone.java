package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Player;

public class GrindingStone extends ToolCard
{
    public GrindingStone ()
    {
        super ("Grinding Stone", "After drafting, flip the die to its opposite side. 6 flips to 1, 5 to 2, 4 to 3, etc.", 10);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public void use(Player player) {

    }
}
