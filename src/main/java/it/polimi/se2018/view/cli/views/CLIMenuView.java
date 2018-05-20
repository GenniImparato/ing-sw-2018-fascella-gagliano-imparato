package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.StartGameEvent;
import it.polimi.se2018.network.socket.client.NetworkHandler;
import it.polimi.se2018.network.socket.server.Server;
import it.polimi.se2018.view.cli.CLI;

public class CLIMenuView extends CLIView
{
    private enum CLIMenuState
    {
        ASK_SERVER_CLIENT,
        ASK_NICKNAME
    }

    private CLIMenuState state;

    public CLIMenuView(CLI cli)
    {
        super(cli);
        state = CLIMenuState.ASK_SERVER_CLIENT;
    }

    @Override
    public void draw()
    {
        if(state == CLIMenuState.ASK_SERVER_CLIENT)
        {
            cli.showMessage("1) Start a New Game (Server):");
            cli.showMessage("2) Connect to a game (Client):");
            cli.readInputFromUser();
        }
        else if(state == CLIMenuState.ASK_NICKNAME)
        {
            cli.showMessage("Insert your nickname:");
            cli.readInputFromUser();
        }
    }

    @Override
    public void parseInput(String input)
    {
        if(state == CLIMenuState.ASK_SERVER_CLIENT)
        {
            if(input.equals("1"))
            {
                new Server();
                state = CLIMenuState.ASK_NICKNAME;
                cli.reShowCurrentView();
            }
            else if(input.equals("2"))
            {
                state = CLIMenuState.ASK_NICKNAME;
                cli.reShowCurrentView();
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
            new NetworkHandler("192.168.43.140", 1000, cli);
            cli.notify(new AddPlayerEvent(cli, input));
        }
    }
}
