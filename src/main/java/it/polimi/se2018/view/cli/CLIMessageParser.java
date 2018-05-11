package it.polimi.se2018.view.cli;

import it.polimi.se2018.events.messages.AddedDieMessage;
import it.polimi.se2018.events.messages.DraftedDieMessage;
import it.polimi.se2018.events.messages.GameStartedMessage;
import it.polimi.se2018.events.messages.PlayerAddedMessage;
import it.polimi.se2018.model.Color;

public class CLIMessageParser
{
    private CLI cli;

    public CLIMessageParser(CLI cli)
    {
        this.cli = cli;
    }

    public void parseMessage(AddedDieMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                                +" - Die: ( " + message.getDie().getValue() + " , " + message.getDie().getColor() + " )"
                                +" - PLayer: " + message.getPlayer().getNickname()
                                +" - Coordinates: ( " + message.getRow() + " , " + message.getCol() + " ) ", Color.BLUE);
    }

    public void parseMessage(DraftedDieMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                +" - Die: ( " + message.getDie().getValue() + " , " + message.getDie().getColor() + " )"
                +" - PLayer: " + message.getPlayer().getNickname(), Color.BLUE);
    }

    public void parseMessage(GameStartedMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName(), Color.BLUE);
    }

    public void parseMessage(PlayerAddedMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                                +" - PLayer: " + message.getPlayer().getNickname(), Color.BLUE);
    }

}
