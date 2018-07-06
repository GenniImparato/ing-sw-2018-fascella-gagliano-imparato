package it.polimi.se2018.controller;


import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.exceptions.ActionNotPossibleException;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.network.server.VirtualView;

/**
 * This class is used to represent the parser that parses the event when a player is using a tool card.
 */
public class UsingToolCardEventParser implements EventVisitor
{
    private Controller controller;

    /**
     * Constructor
     * @param controller controller
     */
    public UsingToolCardEventParser(Controller controller)
    {
        this.controller = controller;
    }


    @Override
    public void visit(SelectSchemeCardEvent event)
    {
    }

    /**
     * Parses the AddPlayer event
     * @param event AddPlayerEvent that needs to be parsed
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
     * Parses ClientDisconnected event
     * @param event ClientDisconnectedEvent that needs to be parsed
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

    /**
     * Parses the SelectSameColorDie event
     * @param event SelectSameColorDieEvent that needs to be parsed
     */
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

    /**
     * Parses the ChooseDie event
     * @param event ChooseDieEvent that needs to be parsed
     */
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
    public void visit(PlayerReadyEvent event)
    {
    }

    /**
     * Parses the DraftDie event
     * @param event DraftDieEvent that needs to be parsed
     */
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

    /**
     * Parses the AddDieToBoard event
     * @param event AddDieToBoardEvent that needs to be parsed
     */
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

    /**
     * Parses the MoveSelectedDie event
     * @param event MoveSelectedDieEvent that needs to be parsed
     */
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

    /**
     * Parses the SelectDieFromBoard event
     * @param event SelectDieFromBoardEvent that needs to be parsed
     */
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

    /**
     * Parsesthe IncrementDraftedDieEvent
     * @param event IncrementDraftedDieEvent that needs to be parsed
     */
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
