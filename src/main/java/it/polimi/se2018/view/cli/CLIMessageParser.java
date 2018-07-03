package it.polimi.se2018.view.cli;

import it.polimi.se2018.mvc_comunication.MessageVisitor;
import it.polimi.se2018.mvc_comunication.messages.*;
import it.polimi.se2018.utils.Color;
import it.polimi.se2018.view.cli.views.*;

public class CLIMessageParser implements MessageVisitor
{
    private CLI cli;

    public CLIMessageParser(CLI cli)
    {
        this.cli = cli;
    }

    @Override
    public void visit(SelectedPlayerSchemeCardsMessage event)
    {
        if(event.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
        {
            cli.showView(new CLIChooseSchemeCardView(cli, event.getSchemeBoards()));
        }
    }

    @Override
    public void visit(ChosenSchemeCardMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You chosen ";
        else
            notification = message.getPlayer().getNickname() + " has chosen ";

        notification += message.getSchemeBoards().getSchemeCardName();

        cli.showNotification(notification);
        cli.reShowCurrentView();
    }

    @Override
    public void visit(AddedPlayerMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You joined the game!";
        else
            notification = message.getPlayer().getNickname() + " joined the game!";

        cli.showNotification(notification);
        cli.showView(new CLILobbyView(cli));
    }

    @Override
    public void visit(RemovedPlayerMessage message) {

    }

    @Override
    public void visit(StartedGameMessage message)
    {
        cli.showNotification("The game started!");
    }

    @Override
    public void visit(PlayerReadyMessage message) {

    }

    @Override
    public void visit(DraftedDieMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You drafted a ";
        else
            notification = message.getPlayer().getNickname() + " drafted a ";

        notification += (message.getDie().getColor().getConsoleString() + message.getDie().getColor() + Color.BLUE.getConsoleString()
                            + " die with value " + message.getDie().getValue() + "!");

        cli.showNotification(notification);
        cli.reShowCurrentView();
    }

    @Override
    public void visit(AddedDieMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You added a ";
        else
            notification = message.getPlayer().getNickname() + " added a ";

        notification += (message.getDie().getColor().getConsoleString() + message.getDie().getColor() + Color.BLUE.getConsoleString()
                + " die with value " + message.getDie().getValue()
                + " to position: (" + message.getRow() + ", " + message.getColumn() +")!");

        cli.showNotification(notification);
        cli.reShowCurrentView();
    }

    @Override
    public void visit(MovedDieMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You moved a ";
        else
            notification = message.getPlayer().getNickname() + " moved a ";

        notification += (message.getDie().getColor().getConsoleString() + message.getDie().getColor() + Color.BLUE.getConsoleString()
                + " die with value " + message.getDie().getValue()
                + " to position: (" + message.getRow() + ", " + message.getColumn() +")!");

        cli.showNotification(notification);
        cli.reShowCurrentView();
    }

    @Override
    public void visit(ChangedDraftedDieMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You changed the drafted die to (";
        else
            notification = message.getPlayer().getNickname() + " changed the drafted die to (";

        notification += (message.getDie().getColor().getConsoleString() + message.getDie().getColor() + Color.BLUE.getConsoleString()
                + ",  " + message.getDie().getValue() + ")!");

        cli.showNotification(notification);
        cli.reShowCurrentView();
    }

    @Override
    public void visit(SelectedDieMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You selected a ";
        else
            notification = message.getPlayer().getNickname() + " selected a ";

        notification += (message.getDie().getColor().getConsoleString() + message.getDie().getColor() + Color.BLUE.getConsoleString()
                + " die with value " + message.getDie().getValue() + "!");

        cli.showNotification(notification);
        cli.reShowCurrentView();
    }

    @Override
    public void visit(ReturnedDieMessage message)
    {
        cli.showNotification("CLI notified: " + message.getClass().getSimpleName()
                +" - Die: ( " + message.getDie().getValue() + " , " + message.getDie().getColor() + " )"
                +" - PLayer: " + message.getPlayer().getNickname());

        cli.reShowCurrentView();
    }

    @Override
    public void visit(UsingToolCardMessage message)
    {
        String notification;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            notification = "You are using a ";
        else
            notification = message.getPlayer().getNickname() + " is using a ";

        notification += message.getCard().getName() + " tool card!";

        cli.showNotification(notification);
        cli.reShowCurrentView();
    }

    @Override
    public void visit(ToolCardEndedMessage message)
    {
        cli.reShowCurrentView();
    }

    @Override
    public void visit(BegunTurnMessage message)
    {
        String notification;
        CLIView nextView;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
        {
            notification = "Your turn begun! ";
            nextView = new CLIMainActionsView(cli);
        }
        else
        {
            notification = message.getPlayer().getNickname() + "'s turn begun! ";
            nextView = new CLIOtherPlayersTurnView(cli);
        }

        cli.showNotification(notification);
        cli.showView(nextView);
    }

    @Override
    public void visit(ModifiedDieMessage message) {

    }

    @Override
    public void visit(ReRolledDraftPoolMessage message) {

    }

    @Override
    public void visit(UpdatedStartTimerMessage message) {

    }
}
