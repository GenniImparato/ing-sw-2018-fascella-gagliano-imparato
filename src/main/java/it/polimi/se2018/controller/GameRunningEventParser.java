package it.polimi.se2018.controller;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.network.server.VirtualView;
/**
 * This class is used to represent the parser that parses the events when the game is running.
 */
public class GameRunningEventParser implements EventVisitor
{
    private Controller controller;

    /**
     * Constructor
     * @param controller controller
     */
    public GameRunningEventParser(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void visit(SelectSchemeCardEvent event) {
    }

    /**
     * Parses the AddPlayer event
     * @param event AddPlayerEvent to parse
     */
    @Override
    public void visit(AddPlayerEvent event)
    {
        try
        {
            Player player = controller.getModel().findPlayer(event.getNickname());
            controller.getModel().setPlayerActive(player, true);

        }
        catch(NoElementException e)
        {
            controller.getView().showErrorMessage("Cannot join the game: already started! ");
            controller.getView().showErrorMessage("Disconnected from the Server!");
            controller.getView().disconnect();
        }
    }

    /**
     * Parses the ClientDisconnected event
     * @param event ClientDisconnectedEvent to parse
     */
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

    }

    @Override
    public void visit(ChooseDieEvent event)
    {

    }

    /**
     * Parses the EndTurn event
     * @param event EndTurnEvent to parse
     */
    @Override
    public void visit(EndTurnEvent event) {
        controller.endPlayerTurn();
    }

    @Override
    public void visit(PlayerReadyEvent event)
    {
    }

    /**
     * Parses the DraftDie event
     * @param event DraftDieEvent to parse
     */
    @Override
    public void visit(DraftDieEvent event)
    {
        if(controller.hasPlayerDrafted())
        {
            controller.getView().showErrorMessage("You cannot draft again in this turn!");
            controller.getView().showTurn();
        }
        else
        {
            try
            {
                controller.draftDie(event.getDieNum());
                controller.getView().showAddDie();
            }
            catch(ChangeModelStateException e)
            {
                controller.getView().showErrorMessage(e.getMessage());
                controller.getView().reShowCurrentView();
            }
        }
    }

    /**
     * Parses the AddDieToBoard event
     * @param event AddDieToBoardEvent to parse
     */
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

    /**
     * Parses the UseToolCard event
     * @param event UseToolCardEvent to parse
     */
    @Override
    public void visit(UseToolCardEvent event)
    {
        if(controller.hasPlayerUsedToolCard())
        {
            controller.getView().showErrorMessage("You cannot use another card in this turn!");
            controller.getView().showTurn();
        }
        else
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
    }

    @Override
    public void visit(IncrementDraftedDieEvent event)
    {
    }
}
