package it.polimi.se2018.game.toolcards;
import it.polimi.se2018.game.Player;
import it.polimi.se2018.game.ToolCard;

public class GrozingPliers extends ToolCard
{

    public GrozingPliers ()
    {
        super ("Grozing Pliers", "After drafting, increase or decrease the value of the drafted die by 1. I may not change to 6, or 6 to 1", 1);
    }

    @Override
    public void use(Player player) {

    }
}
