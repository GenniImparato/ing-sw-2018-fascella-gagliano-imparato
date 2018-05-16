package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Model;

public class Lathekin extends ToolCard
{
    public Lathekin ()
    {
        super ("Lathekin", "Move exactly two dice, obeying all placement restrictions", 4);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }
}
