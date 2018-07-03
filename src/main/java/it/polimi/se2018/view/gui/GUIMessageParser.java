package it.polimi.se2018.view.gui;

import it.polimi.se2018.mvc_comunication.MessageVisitor;
import it.polimi.se2018.mvc_comunication.messages.*;
import it.polimi.se2018.view.gui.views.GUIChooseSchemeCardView;
import it.polimi.se2018.view.gui.views.GUIGameView;

public class GUIMessageParser implements MessageVisitor
{
    private GUI gui;

    public GUIMessageParser(GUI gui)
    {
        this.gui=gui;
    }

    @Override
    public void visit(SelectedPlayerSchemeCardsMessage message)
    {
        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
        {
            gui.showView(new GUIChooseSchemeCardView(gui, message.getSchemeBoards()));

            ((GUIChooseSchemeCardView) gui.getCurrentView()).setCardChosen(false);
        }
    }

    @Override
    public void visit(ChosenSchemeCardMessage message)
    {
        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            ((GUIChooseSchemeCardView)gui.getCurrentView()).setCardChosen(true);
    }

    @Override
    public void visit(AddedDieMessage message)
    {
        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            gui.showTurn();
        else
            gui.reShowCurrentView();
    }

    @Override
    public void visit(AddedPlayerMessage message)
    {
        gui.reShowCurrentView();
    }

    @Override
    public void visit(RemovedPlayerMessage message)
    {
        gui.reShowCurrentView();
    }

    @Override
    public void visit(DraftedDieMessage message)
    {
        if(!message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            ((GUIGameView)gui.getCurrentView()).moveDraftedDieToBoardAnimation(message.getDie(), message.getPlayer());

        gui.reShowCurrentView();
    }

    @Override
    public void visit(ReturnedDieMessage message)
    {
        if(!message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            ((GUIGameView)gui.getCurrentView()).moveDraftedDieBackToDraftPoolAnimation(message.getDie(), message.getPlayer());

        gui.reShowCurrentView();
    }

    @Override
    public void visit(SelectedDieMessage message)
    {

    }

    @Override
    public void visit(StartedGameMessage message)
    {
        gui.showView(new GUIGameView(gui));
        gui.getMainWindow().pack();
        gui.getMainWindow().setLocationRelativeTo(null);
    }

    @Override
    public void visit(PlayerReadyMessage message)
    {
        gui.reShowCurrentView();
    }

    @Override
    public void visit(UsingToolCardMessage message)
    {
        gui.reShowCurrentView();
        ((GUIGameView)gui.getCurrentView()).highlightToolCard(message.getCard(), true);
    }

    @Override
    public void visit(ToolCardEndedMessage message)
    {
        ((GUIGameView)gui.getCurrentView()).highlightToolCard(message.getCard(), false);
    }

    @Override
    public void visit(ChangedDraftedDieMessage message)
    {

    }

    @Override
    public void visit(MovedDieMessage message)
    {
        gui.reShowCurrentView();
    }

    @Override
    public void visit(BegunTurnMessage message)
    {
        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            gui.showTurn();
        else
            gui.showOtherPlayersTurn();
    }

    @Override
    public void visit(ModifiedDieMessage message)
    {
        gui.reShowCurrentView();
    }

    @Override
    public void visit(ReRolledDraftPoolMessage message)
    {
        gui.reShowCurrentView();
    }

    @Override
    public void visit(UpdatedStartTimerMessage message)
    {
        gui.showNotification("Game starts in " + message.getTime() + " seconds!");
    }
}

