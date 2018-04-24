package it.polimi.se2018.toolcards;

import it.polimi.se2018.game.Player;
import it.polimi.se2018.game.ToolCard;

public class FluxBrush extends ToolCard
{
    public FluxBrush ()
    {
        super ("Flux Brush", "After drafting, re-roll the drafted die. If it cannot be placed return it to the Draft Pool", 6);
    }

    @Override
    public void use(Player player) {

    }
}
