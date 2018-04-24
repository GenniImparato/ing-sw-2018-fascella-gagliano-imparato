package it.polimi.se2018.toolcards;

import it.polimi.se2018.game.Player;
import it.polimi.se2018.game.ToolCard;

public class RunningPliers extends ToolCard
{
    public RunningPliers ()
    {
        super ("Running Pliers", "After your first turn, immediately draft a die. Skip your next turn this round", 8);
    }

    @Override
    public void use(Player player) {

    }
}
