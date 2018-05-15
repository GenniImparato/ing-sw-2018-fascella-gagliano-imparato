package it.polimi.se2018.view.cli;

import it.polimi.se2018.events.messages.*;
import it.polimi.se2018.utils.Color;

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

    public void parseMessage(SelectedDieMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                +" - Die: ( " + message.getDie().getValue() + " , " + message.getDie().getColor() + " )"
                +" - PLayer: " + message.getPlayer().getNickname(), Color.BLUE);
    }

    public void parseMessage(ReturnedDieMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                +" - Die: ( " + message.getDie().getValue() + " , " + message.getDie().getColor() + " )"
                +" - PLayer: " + message.getPlayer().getNickname(), Color.BLUE);
    }

    public void parseMessage(GameStartedMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName(), Color.BLUE);
    }

    public void parseMessage(AddedPlayerMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                                +" - PLayer: " + message.getPlayer().getNickname(), Color.BLUE);
    }

    public void parseMessage(StartedUsingToolCardMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                +" - PLayer: " + message.getCard().getName(), Color.BLUE);
    }

    public void parseMessage(ToolCardActionExecutedMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                +" - Message: " + message.getMessage(), Color.BLUE);
    }

}
