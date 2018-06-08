package it.polimi.se2018.controller;

import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.network.server.VirtualView;

public class GameRunningEventParser implements EventVisitor
{
    private Controller controller;

    public GameRunningEventParser(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void visit(SelectSchemeCardEvent event) {
    }

    @Override
    public void visit(AddPlayerEvent event)
    {
        controller.getView().showErrorMessage("Cannot join the game: already started! ");
        controller.getView().showErrorMessage("Disconnected from the Server!");
        controller.getView().disconnect();
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
    public void visit(EndTurnEvent event) {
        controller.endPlayerTurn();
    }

    @Override
    public void visit(StartGameEvent event) {
    }

    @Override
    public void visit(PlayerReadyEvent event) {
    }

    @Override
    public void visit(DraftDieEvent event)
    {
        try
        {
            controller.draftDie(event.getDieNum());
            controller.getView().showAddDie();
        }
        catch (ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(AddDieToBoardEvent event) {

        try
        {
            controller.getModel().addDraftedDieToBoard(event.getRow(), event.getColumn(), false, false, false);
            controller.getView().showTurn();

        }
        catch (ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
        catch (ActionNotPossibleException e)
        {
            controller.getView().showErrorMessage("The drafted die cannot be added in any position on your board!");

            try
            {
                controller.getModel().returnDraftedDie();
            }
            catch (ChangeModelStateException ex)
            {
            }

            controller.getView().showTurn();
        }
    }

    @Override
    public void visit(MoveSelectedDieEvent event)
    {
    }

    @Override
    public void visit(SelectDieFromBoardEvent event)
    {
    }

    @Override
    public void visit(UseToolCardEvent event)
    {
        try
        {
            controller.startToolCardActions(event.getCardNum());
        }
        catch (ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }

    @Override
    public void visit(IncrementDraftedDieEvent event)
    {
    }
}
