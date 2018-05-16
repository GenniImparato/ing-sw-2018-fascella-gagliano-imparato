package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Model;

public class CopperFoilBurnisher extends ToolCard
{
    public CopperFoilBurnisher()
    {
        super ("Copper Foil Burnisher", "Move any one die in your window ignoring value restrictions. You must obey all other placement restrictions", 3);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }
}