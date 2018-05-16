package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.StartGameEvent;
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
            cli.showMessage("1) Start a New Game:");
            parseInput(cli.readInputFromUser());
        }
        else if(state == CLIMenuState.ASK_NICKNAME)
        {
            cli.showMessage("Insert your nickname:");
            parseInput(cli.readInputFromUser());
        }
    }

    @Override
    public void parseInput(String input)
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
            cli.setAssociatedPlayerNickname(input);
            cli.notify(new AddPlayerEvent(cli, input));
            cli.notify(new StartGameEvent(cli));
        }
    }
}
