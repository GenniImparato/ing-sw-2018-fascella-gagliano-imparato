package it.polimi.se2018.game.toolcards;

import it.polimi.se2018.game.Player;
import it.polimi.se2018.game.ToolCard;

public class FluxRemover extends ToolCard
{
    public FluxRemover ()
    {
        super ("Flux Remover", "After drafting, return the die to the Dice Bag and pull 1 die from the bag. Choose a value and place the new die, obeying all placement restrictions, or return it to the Draft Pool", 11);
    }

    @Override
    public void use(Player player) {

    }
}