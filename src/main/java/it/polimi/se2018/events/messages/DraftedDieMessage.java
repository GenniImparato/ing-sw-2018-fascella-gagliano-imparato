package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Game;

public class DraftedDieMessage extends Message
{
    private Die die;

    public DraftedDieMessage(Game game, Die die)
    {
        super(game);
        this.die = die;
    }

    public Die getDie()
    {
        return die;
    }
}
