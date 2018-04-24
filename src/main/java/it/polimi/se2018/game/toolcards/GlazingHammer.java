package it.polimi.se2018.game.toolcards;

import it.polimi.se2018.game.Player;
import it.polimi.se2018.game.ToolCard;

public class GlazingHammer extends ToolCard
{

    public GlazingHammer ()
    {
        super ("Glazing Hammer", "Re-roll all dice in the Draft Pool. This may only be used on your second turn before drafting", 7);
    }

    @Override
    public void use(Player player) {

    }
}
