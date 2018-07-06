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
        cli.reShowCurrentView();
    }

    @Override
    public void visit(AddedPlayerMessage message)
    {
        cli.showView(new CLILobbyView(cli));
    }

    @Override
    public void visit(RemovedPlayerMessage message)
    {
        cli.reShowCurrentView();
    }

    @Override
    public void visit(StartedGameMessage message)
    {
    }

    @Override
    public void visit(PlayerReadyMessage message)
    {
        cli.reShowCurrentView();
    }

    @Override
    public void visit(DraftedDieMessage message)
    {
        cli.reShowCurrentView();
    }

    @Override
    public void visit(AddedDieMessage message)
    {
        cli.reShowCurrentView();
    }

    @Override
    public void visit(MovedDieMessage message)
    {
        cli.reShowCurrentView();
    }

    @Override
    public void visit(ChangedDraftedDieMessage message)
    {
        cli.reShowCurrentView();
    }

    @Override
    public void visit(SelectedDieMessage message)
    {
        cli.reShowCurrentView();
    }

    @Override
    public void visit(ReturnedDieMessage message)
    {
        cli.reShowCurrentView();
    }

    @Override
    public void visit(UsingToolCardMessage message)
    {
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
        CLIView nextView;

        if(message.getPlayer().getNickname().equals(cli.getAssociatedPlayerNickname()))
            nextView = new CLIMainActionsView(cli);
        else
            nextView = new CLIOtherPlayersTurnView(cli);

        cli.showView(nextView);
    }

    @Override
    public void visit(ModifiedDieMessage message)
    {

    }

    @Override
    public void visit(ReRolledDraftPoolMessage message)
    {

    }

    @Override
    public void visit(UpdatedStartTimerMessage message)
    {

    }

    @Override
    public void visit(DisconnectedPlayerMessage message)
    {

    }

    @Override
    public void visit(UpdatedTurnTimerMessage message)
    {

    }

    @Override
    public void visit(EndGameMessage message)
    {
        cli.showFinalScore();
    }

    @Override
    public void visit(ReconnectedPlayerMessage message)
    {

    }
}
