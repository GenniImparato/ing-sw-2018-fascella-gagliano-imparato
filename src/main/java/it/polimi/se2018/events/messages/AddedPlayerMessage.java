package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.view.cli.CLIMessageParser;

public class AddedPlayerMessage extends Message
{
    private Player player;

    public AddedPlayerMessage(Model model, Player player)
    {
        super(model);
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