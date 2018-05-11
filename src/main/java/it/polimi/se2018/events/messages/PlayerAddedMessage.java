package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.view.cli.CLIMessageParser;

public class PlayerAddedMessage extends Message
{
    private Player player;

    public PlayerAddedMessage(Game game, Player player)
    {
        super(game);
        this.player = new Player(player);
    }

    public Player getPlayer()
    {
        return player;
    }

    @Override
    public void beParsed(CLIMessageParser parser)
    {
        parser.parseMessage(this);
    }
}