package it.polimi.se2018.controller;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.events.clievents.CLIEvent;
import it.polimi.se2018.events.clievents.CLIInputParsedEvent;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.CLI;

public class CLIController extends Controller<CLIEvent>
{
    CLI view;

    public CLIController(Game game, CLI view)
    {
        super(game);
        this.view = view;
    }

    @Override
    public void update(CLIEvent event)
    {
        if(event instanceof CLIInputParsedEvent)
            handleEvent((CLIInputParsedEvent)event);
    }

    private void handleEvent(CLIInputParsedEvent event)
    {
        switch(event.getState())
        {
            case MENU_CLIENT_SERVER:
                if(event.getInput().equals("1"))
                {
                    view.showMessage("New game started!\n");
                    view.askPLayerNickname();
                }
                else if(event.getInput().equals("2"))
                {
                    view.showErrorMessage("Cannot connect to a game!");
                    view.menuClientServer();
                }
                else
                {
                    view.showErrorMessage("Input not valid!");
                    view.menuClientServer();
                }
                break;

            case MENU_ASKING_PLAYER_NICKNAME:
                if(!game.addNewPlayer(event.getInput()))
                    view.showErrorMessage("Cannot add a new player");
                else
                {
                    view.showMessage("Player " + event.getInput() + " added!");
                    view.askPLayerNickname();
                }
                break;
        }
    }
}
