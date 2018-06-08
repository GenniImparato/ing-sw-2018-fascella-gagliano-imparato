package it.polimi.se2018.controller;


import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.network.server.VirtualView;

public class GameSetupEventParser implements EventVisitor
{
    private Controller controller;

    public GameSetupEventParser(Controller controller)
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