package it.polimi.se2018.controller;

import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;

public class EventParser implements EventVisitor
{
    Controller controller;

    public EventParser(Controller controller)
    {
        this.controller = controller;
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

            if(!controller.isToolCardBeingUsed())
                event.getView().showAddDie();
            else
                controller.nextToolCardStep();
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
            event.getView().showTurn();
        }
        catch(ChangeModelStateException e)
        {
            event.getView().showErrorMessage(e.getMessage());
            event.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(MoveDieEvent event)
    {
        try
        {
            controller.moveSelectedDie(event.getRow(), event.getColumn());
            controller.nextToolCardStep();
        }
        catch(ChangeModelStateException e)
        {
            event.getView().showErrorMessage(e.getMessage());
            event.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(SelectDieFromBoardEvent event)
    {
        try
        {
            controller.selectDieFromBoard(event.getRow(), event.getColumn());
            controller.nextToolCardStep();
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
            controller.beginToolCardActions(event.getCardNum());
        }
        catch(ChangeModelStateException e)
        {
            event.getView().showErrorMessage(e.getMessage());
            event.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(IncrementDraftedDieEvent event)
    {
        try
        {
            if(event.isIncrement())
                controller.getModel().incrementDraftedDie();
            else
                controller.getModel().decrementDraftedDie();

            controller.nextToolCardStep();
        }
        catch (ChangeModelStateException e)
        {
            event.getView().showErrorMessage(e.getMessage());
            event.getView().reShowCurrentView();
        }
    }
}