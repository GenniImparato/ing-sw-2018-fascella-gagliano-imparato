package it.polimi.se2018.events.messages;

import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.view.cli.CLIMessageParser;

public class DraftedDieMessage extends Message
{
    private Die die;
    private Player player;

    public DraftedDieMessage(Game game, Die die, Player player)
    {
        super(game);
        this.die = new Die(die);
        this.player = new Player(player);
    }

    public Player getPlayer()
    {
        return player;
    }

    public Die getDie()
    {
        return die;
    }

    @Override
    public void beParsed(CLIMessageParser parser)
    {
        parser.parseMessage(this);
    }
}
