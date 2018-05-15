package it.polimi.se2018.model.toolcards;
import it.polimi.se2018.model.Model;

public class GrozingPliers extends ToolCard
{

    public GrozingPliers()
    {
        super ("Grozing Pliers", "After drafting, increase or decrease the value of the drafted die by 1. I may not change to 6, or 6 to 1", 1);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    //param1 = 0  -> decrement die value by 1
    //param1 = 1  -> increment die value by 1
    //param2 not used
    public String action(Model model, int param1, int param2)
    {
        return "";
    }
}
