package it.polimi.se2018.controller;

import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.network.server.VirtualView;

public class EventParser implements EventVisitor
{
    Controller controller;

    public EventParser(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void visit(SelectSchemeCardEvent event)
    {
        try
        {
            Player player = controller.getModel().findPlayer(event.getPLayerNickame());
            controller.chosePlayerSchemeCard(player, event.getChoice());

            if(controller.getModel().hasEveryPlayerChosenSchemeCard())
                controller.startGame();
        }
        catch(NoElementException e)
        {
            controller.getView().showErrorMessage("Player is not present in the game");
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(AddPlayerEvent event)
    {
        try
        {
            controller.addNewPlayer(event.getNickname());
            controller.getView().showLobby();
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage("Disconnected from the Server: " +e.getMessage());
            if(controller.getView() instanceof  VirtualView)
                ((VirtualView)controller.getView()).disconnect();
        }
    }

    @Override
    public void visit(ClientDisconnectedEvent event)
    {
        try
        {
            controller.removePlayer(event.getNickname());
        }
        catch (ChangeModelStateException e)
        {

        }

    }

    @Override
    public void visit(EndTurnEvent event)
    {
        controller.endPlayerTurn();
    }

    @Override
    public void visit(StartGameEvent event)
    {
        try
        {
            controller.startGameSetup();
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void visit(PlayerReadyEvent event)
    {
        try
        {
            controller.setPlayerReady(controller.getModel().findPlayer(event.getNickname()), event.isReady());
            if(controller.getModel().isEveryPlayerReady())
                controller.startGameSetup();
        }
        catch(NoElementException e)
        {
            controller.getView().showErrorMessage("Cannot find player: " + event.getNickname());
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
        }
    }

    @Override
    public void visit(DraftDieEvent event)
    {
        try
        {
            controller.draftDie(event.getDieNum());

            if(!controller.isToolCardBeingUsed())
                controller.getView().showAddDie();
            else
                controller.nextToolCardStep();
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(AddDieToBoardEvent event)
    {
        try
        {
            if(!controller.isToolCardBeingUsed())
            {
                controller.getModel().addDraftedDieToBoard(event.getRow(), event.getColumn(), false, false, false);
                controller.getView().showTurn();
            }
            else
            {
                ToolCardParameters params = controller.getCurrentToolCardParameters();
                controller.getModel().addDraftedDieToBoard(event.getRow(), event.getColumn(), params.isIgnoreValue(), params.isIgnoreColor(), params.isIgnoreAdjacent());
                controller.nextToolCardStep();
            }

        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
        catch(ActionNotPossibleException e)
        {
            controller.getView().showErrorMessage("The drafted die cannot be added in any position on your board!");

            try
            {
                controller.getModel().returnDraftedDie();
            }
            catch (ChangeModelStateException ex)
            {
                controller.getView().showErrorMessage(ex.getMessage());
                controller.getView().reShowCurrentView();
            }
        }
    }

    @Override
    public void visit(MoveSelectedDieEvent event)
    {
        try
        {
            ToolCardParameters params = controller.getCurrentToolCardParameters();
            controller.getModel().moveSelectedDie(event.getRow(), event.getColumn(), params.isIgnoreValue(), params.isIgnoreColor(), params.isIgnoreAdjacent());
            controller.nextToolCardStep();
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
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
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(UseToolCardEvent event)
    {
        try
        {
            controller.startToolCardActions(event.getCardNum());
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
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
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }
}