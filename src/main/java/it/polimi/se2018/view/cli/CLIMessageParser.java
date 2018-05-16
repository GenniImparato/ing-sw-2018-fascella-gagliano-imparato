package it.polimi.se2018.view.cli;

import it.polimi.se2018.mvc_comunication.MessageVisitor;
import it.polimi.se2018.mvc_comunication.messages.*;
import it.polimi.se2018.utils.Color;

public class CLIMessageParser implements MessageVisitor
{
    private CLI cli;

    public CLIMessageParser(CLI cli)
    {
        this.cli = cli;
    }

    public void visit(AddedPlayerMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You joined the game!";
        else
            notification = message.getPlayer().getNickname() + " joined the game!";

        cli.showNotification(notification, Color.BLUE);
    }

    public void visit(StartedGameMessage message)
    {
        cli.showNotification("The game started!", Color.BLUE);
        cli.showTurn();
    }

    public void visit(DraftedDieMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You drafted a ";
        else
            notification = message.getPlayer().getNickname() + " drafted a ";

        notification += (message.getDie().getColor().getConsoleString() + message.getDie().getColor() + Color.BLUE.getConsoleString()
                            + " die with value " + message.getDie().getValue() + "!");

        cli.showNotification(notification, Color.BLUE);
    }

    public void visit(AddedDieMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You added a ";
        else
            notification = message.getPlayer().getNickname() + " added a ";

        notification += (message.getDie().getColor().getConsoleString() + message.getDie().getColor() + Color.BLUE.getConsoleString()
                + " die with value " + message.getDie().getValue()
                + " to position: (" + message.getRow() + ", " + message.getCol() +")!");

        cli.showNotification(notification, Color.BLUE);
    }

    public void visit(ChangedDraftedDieMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You changed the drafted die to (";
        else
            notification = message.getPlayer().getNickname() + " changed the drafted die to (";

        notification += (message.getDie().getColor().getConsoleString() + message.getDie().getColor() + Color.BLUE.getConsoleString()
                + ",  " + message.getDie().getValue() + ")!");

        cli.showNotification(notification, Color.BLUE);
    }

    public void visit(SelectedDieMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                +" - Die: ( " + message.getDie().getValue() + " , " + message.getDie().getColor() + " )"
                +" - PLayer: " + message.getPlayer().getNickname(), Color.BLUE);
    }

    public void visit(ReturnedDieMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                +" - Die: ( " + message.getDie().getValue() + " , " + message.getDie().getColor() + " )"
                +" - PLayer: " + message.getPlayer().getNickname(), Color.BLUE);
    }

    public void visit(UsingToolCardMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You are using a ";
        else
            notification = message.getPlayer().getNickname() + " is using a ";

        notification += message.getCard().getName() + " tool card!";

        cli.showNotification(notification, Color.BLUE);
    }

    public void visit(ToolCardActionExecutedMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                +" - Message: " + message.getMessage(), Color.BLUE);
    }

}
