package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.Player;

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

    @Override
    public String action(Game game, int param1, int param2)
    {
        return "";
    }
}
