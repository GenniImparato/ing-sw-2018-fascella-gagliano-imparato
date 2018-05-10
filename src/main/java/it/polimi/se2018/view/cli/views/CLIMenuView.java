package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.model.gameactions.AddNewPlayerAction;
import it.polimi.se2018.model.gameactions.GameAction;
import it.polimi.se2018.model.gameactions.StartGameAction;
import it.polimi.se2018.view.cli.CLI;

public class CLIMenuView extends CLIView
{
    private enum CLIMenuState
    {
        ASK_NEWGAME,
        ASK_NICKNAME
    }

    private CLIMenuState state;

    public CLIMenuView(CLI cli)
    {
        super(cli);
        state = CLIMenuState.ASK_NEWGAME;
    }

    @Override
    public void draw()
    {
        if(state == CLIMenuState.ASK_NEWGAME)
        {
            System.out.println("1) Start a New Game:");
            cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
        }
        else if(state == CLIMenuState.ASK_NICKNAME)
        {
            System.out.println("Insert your nickname:");
            cli.notify(new CLIInputEvent(cli, this, cli.readInputFromUser()));
        }
    }

    @Override
    public void control(String input)
    {
        if(state == CLIMenuState.ASK_NEWGAME)
        {
            if(input.equals("1"))
            {
                state = CLIMenuState.ASK_NICKNAME;
                draw();
            }
            else
            {
                cli.showErrorMessage("Not valid input");
                draw();
            }
        }
        else if(state == CLIMenuState.ASK_NICKNAME)
        {
            GameAction action = new AddNewPlayerAction(input);
            cli.getGameInstance().executeAction(action);    //tries to add the player to the current game

            if(action.hasBeenExecuted())    //checks if the player has been added
            {
                action = new StartGameAction();
                cli.getGameInstance().executeAction(action);    //start the game

                cli.requestView(new CLIPlayerActionsMainView(cli));
            }
            else
            {
                cli.showErrorMessage(action.getErrorMessage());
                draw();
            }
        }
    }
}
