package it.polimi.se2018.controller;

import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.mvc_comunication.events.EventVisitor;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.mvc_comunication.events.tool_cards_events.ToolCardEvent;

public class EventParser implements EventVisitor
{
    Controller controller;
    ToolCardEventParser toolCardEventParser;

    public EventParser(Controller controller)
    {
        this.controller = controller;
        toolCardEventParser = new ToolCardEventParser(controller);
    }

    @Override
    public void visit(AddPlayerEvent event)
    {
        try
        {
            controller.addNewPlayer(event.getNickname());
        }
        catch(ChangeModelStateException e)
        {
            event.getView().showErrorMessage(e.getMessage());
            event.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(StartGameEvent event)
    {
        try
        {
            controller.startGame();
        }
        catch(ChangeModelStateException e)
        {
            event.getView().showErrorMessage(e.getMessage());
            event.getView().showMenu();
        }
    }

    @Override
    public void visit(DraftDieEvent event)
    {
        try
        {
            controller.draftDie(event.getDieNum());
            event.getView().showPlayerAddDie();
        }
        catch(ChangeModelStateException e)
        {
            event.getView().showErrorMessage(e.getMessage());
            event.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(AddDraftedDieEvent event)
    {
        try
        {
            controller.addDraftedDie(event.getRow(), event.getColumn());
            event.getView().showPlayerTurn();
        }
        catch(ChangeModelStateException e)
        {
            event.getView().showErrorMessage(e.getMessage());
            event.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(UseToolCardEvent event)
    {
        try
        {
            controller.useToolCard(event.getCardNum(), event.getView());
        }
        catch(ChangeModelStateException e)
        {
            event.getView().showErrorMessage(e.getMessage());
            event.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(ToolCardEvent event)
    {
        event.acceptVisitor(toolCardEventParser);
    }
}