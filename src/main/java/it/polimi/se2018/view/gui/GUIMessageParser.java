package it.polimi.se2018.view.gui;

import it.polimi.se2018.mvc_comunication.MessageVisitor;
import it.polimi.se2018.mvc_comunication.messages.*;
import it.polimi.se2018.view.gui.views.GUIChooseSchemeCardView;
import it.polimi.se2018.view.gui.views.GUIGameView;

import javax.swing.*;
import java.awt.*;

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
        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
        {
            notification = "<html>You have chosen ";
            ((GUIChooseSchemeCardView)gui.getCurrentView()).setCardChosen(true);
        }

        else
            notification = "<html><font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>"
                    + message.getPlayer().getNickname() + "</font> has chosen ";

        notification += message.getSchemeBoards().getSchemeCardName() +"!</html>";

        gui.showNotification(notification);

    }

    @Override
    public void visit(AddedDieMessage message)
    {
        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            gui.showTurn();
        else
            gui.reShowCurrentView();

        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            notification = "<html>You added a ";
        else
            notification = "<html><font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>"
                    + message.getPlayer().getNickname() + "</font> added a ";

        notification += "<font color='"+ message.getDie().getColor().toString().toLowerCase() +"'>"
                + message.getDie().getColor() + "</font> die with value " + message.getDie().getValue() + " to the board!";

        gui.showNotification(notification);
    }

    @Override
    public void visit(AddedPlayerMessage message)
    {
        gui.reShowCurrentView();

        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            notification = "<html>You joined the game!</html>";
        else
            notification = "<html><font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>"
                    +message.getPlayer().getNickname() + "</font> joined the game!</html>";

        gui.showNotification(notification);
    }

    @Override
    public void visit(RemovedPlayerMessage message)
    {
        String notification;

        notification = "<html><font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>" +
                message.getPlayer().getNickname() + "</font> disconnected from the game!</html>";
        gui.reShowCurrentView();

        gui.showNotification(notification);
    }

    @Override
    public void visit(DraftedDieMessage message)
    {
        if(!message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            ((GUIGameView)gui.getCurrentView()).moveDraftedDieToBoardAnimation(message.getDie(), message.getPlayer());

        gui.reShowCurrentView();

        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            notification = "<html>You drafted a ";
        else
            notification = "<html><font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>"
                    + message.getPlayer().getNickname() + "</font> drafted a ";

        notification += "<font color='"+ message.getDie().getColor().toString().toLowerCase() +"'>"
                + message.getDie().getColor() + "</font> die with value " + message.getDie().getValue() + "!";


        gui.showNotification(notification);
    }

    @Override
    public void visit(ReturnedDieMessage message)
    {
        if(!message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            ((GUIGameView)gui.getCurrentView()).moveDraftedDieBackToDraftPoolAnimation(message.getDie(), message.getPlayer());

        gui.reShowCurrentView();

        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            notification = "<html>You returned a ";
        else
            notification = "<html><font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>"
                    + message.getPlayer().getNickname() + "</font> returned a ";

        notification += "<font color='"+ message.getDie().getColor().toString().toLowerCase() +"'>"
                + message.getDie().getColor() + "</font> die with value " + message.getDie().getValue() + " back to the draft pool!";


        gui.showNotification(notification);
    }

    @Override
    public void visit(SelectedDieMessage message) {

    }

    @Override
    public void visit(StartedGameMessage message)
    {
        gui.showNotification("Game started!");
        gui.showView(new GUIGameView(gui));
        gui.getMainWindow().pack();
        gui.getMainWindow().setLocationRelativeTo(null);
    }

    @Override
    public void visit(PlayerReadyMessage message)
    {
        gui.reShowCurrentView();

        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            notification = "<html>You are ";
        else
            notification = "<html><font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>"
                    + message.getPlayer().getNickname() + "</font> is ";

        if(message.isReady())
            notification += "ready to play!</html>";
        else
            notification += "not ready to play!</html>";

        gui.showNotification(notification);
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
    public void visit(MovedDieMessage message)
    {
        gui.reShowCurrentView();

        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            notification = "<html>You moved a ";
        else
            notification = "<html><font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>"
                    + message.getPlayer().getNickname() + "</font> moved a ";

        notification += "<font color='"+ message.getDie().getColor().toString().toLowerCase() +"'>"
                + message.getDie().getColor() + "</font> die with value " + message.getDie().getValue() + " on the board!";

        gui.showNotification(notification);
    }

    @Override
    public void visit(BegunTurnMessage message)
    {
        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            gui.showTurn();
        else
            gui.showOtherPlayersTurn();

        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
        {
            notification = "<html>It's your turn!";
        }
        else
            notification = "<html>It's <font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>"
                    + message.getPlayer().getNickname() + "</font>'s turn!";

        gui.showNotification(notification);
    }

    @Override
    public void visit(ModifiedDieMessage message)
    {
        gui.reShowCurrentView();

        String notification;

        if(message.getPlayer().getNickname().equals(gui.getAssociatedPlayerNickname()))
            notification = "<html>You changed a die to ";
        else
            notification = "<html><font color='"+ message.getPlayer().getColor().toString().toLowerCase() +"'>"
                    + message.getPlayer().getNickname() + "</font> changed a die to ";

        notification += "<font color='"+ message.getDie().getColor().toString().toLowerCase() +"'>"
                + message.getDie().getColor() + "</font> die with value " + message.getDie().getValue() + "!";

        //if the modified die is the drafted die cursor should be updated
        if(message.getDie().isSameDie(gui.getModel().getDraftedDie()))

        gui.showNotification(notification);
    }
}

