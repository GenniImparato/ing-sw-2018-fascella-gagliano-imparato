package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Model;

public class RunningPliers extends ToolCard
{
    public RunningPliers ()
    {
        super ("Running Pliers", "After your first turn, immediately draft a die. Skip your next turn this round", 8);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }
}
