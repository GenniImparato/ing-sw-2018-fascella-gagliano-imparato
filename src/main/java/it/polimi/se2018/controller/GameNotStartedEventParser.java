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
 * This class is used to represent the parser that parses the events when the game has not started yet.
 */
public class GameNotStartedEventParser implements EventVisitor
{
    private Controller controller;

    /**
     * Constructor
     * @param controller controller
     */
    public GameNotStartedEventParser(Controller controller)
    {
        this.controller = controller;
    }

    /**
     * Parses the SelectSchemeCard event
     * @param event SelectSchemeCardEvent to parse
     */
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

    /**
     * Parses the AddPlayer event
     * @param event AddPlayerEvent to parse
     */
    @Override
    public void visit(AddPlayerEvent event)
    {
        try
        {
            controller.addNewPlayer(event.getNickname());
            controller.manageStartTime();
            controller.getView().showLobby();
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage("Disconnected from the Server: " +e.getMessage());
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
            controller.manageStartTime();
        }
        catch (ChangeModelStateException e)
        {

        }

    }

    @Override
    public void visit(SelectSameColorDieEvent event) {

    }

    @Override
    public void visit(ChooseDieEvent event) {

    }

    @Override
    public void visit(EndTurnEvent event)
    {
    }

    /**
     * Parses the PlayerReady event
     * @param event PlayerReadyEvent to parse
     */
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
    }

    @Override
    public void visit(AddDieToBoardEvent event)
    {
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
    }

    @Override
    public void visit(IncrementDraftedDieEvent event)
    {
    }
}