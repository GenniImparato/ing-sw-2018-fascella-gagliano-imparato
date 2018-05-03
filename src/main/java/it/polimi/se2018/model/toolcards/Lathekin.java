package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.ToolCard;

public class Lathekin extends ToolCard
{
    public Lathekin ()
    {
        super ("Lathekin", "Move exactly two dice, obeying all placement restrictions", 4);
    }

    @Override
    public void use(Player player) {

    }
}
