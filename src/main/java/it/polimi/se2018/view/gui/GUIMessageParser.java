package it.polimi.se2018.view.gui;

import it.polimi.se2018.mvc_comunication.MessageVisitor;
import it.polimi.se2018.mvc_comunication.messages.*;

public class GUIMessageParser implements MessageVisitor
{
    private GUI gui;

    public GUIMessageParser(GUI gui)
    {
        this.gui=gui;
    }

    @Override
    public void visit(SelectedPlayerSchemeCardsMessage message) {

    }

    @Override
    public void visit(ChosenSchemeCardMessage message) {

    }

    @Override
    public void visit(AddedDieMessage message) {

    }

    @Override
    public void visit(AddedPlayerMessage message)
    {
        gui.reShowCurrentView();

        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            notification = "You joined the game!";
        else
            notification = message.getPlayer().getNickname() + " joined the game!";

        gui.showNotification(notification);
    }

    @Override
    public void visit(DraftedDieMessage message) {

    }

    @Override
    public void visit(ReturnedDieMessage message) {

    }

    @Override
    public void visit(SelectedDieMessage message) {

    }

    @Override
    public void visit(StartedGameMessage message) {

    }

    @Override
    public void visit(UsingToolCardMessage message) {

    }

    @Override
    public void visit(ToolCardActionExecutedMessage message) {

    }

    @Override
    public void visit(ChangedDraftedDieMessage message) {

    }

    @Override
    public void visit(MovedDieMessage message) {

    }

    @Override
    public void visit(BegunTurnMessage message) {

    }
}

