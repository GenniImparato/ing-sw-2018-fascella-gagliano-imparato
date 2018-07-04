package it.polimi.se2018.controller;


import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.network.server.VirtualView;

public class UsingToolCardEventParser implements EventVisitor
{
    private Controller controller;

    public UsingToolCardEventParser(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void visit(SelectSchemeCardEvent event)
    {
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
            controller.getView().disconnect();
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
    public void visit(SelectSameColorDieEvent event)
    {
        try
        {
            controller.selectSameColorDieFromBoard(event.getRow(), event.getColumn());
            controller.nextToolCardStep();
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(ChooseDieEvent event)
    {
        try
        {
            controller.getModel().chooseDieFromRoundTrack(event.getRound());
            controller.nextToolCardStep();
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(EndTurnEvent event)
    {
    }

    @Override
    public void visit(StartGameEvent event)
    {
    }

    @Override
    public void visit(PlayerReadyEvent event)
    {
    }

    @Override
    public void visit(DraftDieEvent event)
    {
        try
        {
            controller.draftDie(event.getDieNum());
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
            ToolCardParameters params = controller.getCurrentToolCardParameters();
            controller.getModel().addDraftedDieToBoard(event.getRow(), event.getColumn(), params.isIgnoreValue(), params.isIgnoreColor(), params.isIgnoreAdjacent());
            controller.nextToolCardStep();
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
            }

            controller.endToolCardActions();
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
