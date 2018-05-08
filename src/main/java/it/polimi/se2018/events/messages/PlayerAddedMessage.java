package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.Player;

public class PlayerAddedMessage extends Message
{
    private Player player;

    public PlayerAddedMessage(Game game, Player player)
    {
        super(game);
        this.player = player;
    }

    public Player getPLayer()
    {
        return player;
    }
}