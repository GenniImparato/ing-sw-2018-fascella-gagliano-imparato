package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Game;

public class FluxBrush extends ToolCard
{
    public FluxBrush ()
    {
        super ("Flux Brush", "After drafting, re-roll the drafted die. If it cannot be placed return it to the Draft Pool", 6);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public String action(Game game, int param1, int param2)
    {
        game.getDraftPool().getAllDice().get(param1).roll();
        return "Rerolled a die!";
    }
}
