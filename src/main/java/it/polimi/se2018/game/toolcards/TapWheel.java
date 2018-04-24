package it.polimi.se2018.game.toolcards;

import it.polimi.se2018.game.Player;
import it.polimi.se2018.game.ToolCard;

public class TapWheel extends ToolCard
{
    public TapWheel ()
    {
        super ("TapWheel", "Move up to two dice of the same color that match the color of a die on the Round Track. You must obey all placement restrictions", 12);
    }

    @Override
    public void use(Player player) {

    }
}
