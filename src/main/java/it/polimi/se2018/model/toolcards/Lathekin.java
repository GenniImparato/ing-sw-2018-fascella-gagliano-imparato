package it.polimi.se2018.model.toolcards;


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
