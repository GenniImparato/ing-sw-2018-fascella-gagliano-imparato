package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.network.exceptions.CannotCreateServerException;
import it.polimi.se2018.network.rmi.client.RMINetworkHandler;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.socket.client.SocketNetworkHandler;
import it.polimi.se2018.network.socket.server.SocketServer;
import it.polimi.se2018.view.cli.CLI;

public class CLIMenuView extends CLIView
{
    private enum CLIMenuState
    {
        ASK_SERVER_CLIENT,
        ASK_CONNECTION_TYPE,
        ASK_NICKNAME
    }

    private CLIMenuState    state;
    private boolean         connectionType;

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
        else if(state == CLIMenuState.ASK_CONNECTION_TYPE)
        {
            cli.showMessage("Select connection type:");
            cli.showMessage("1) Socket:");
            cli.showMessage("2) RMI:");

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
                try
                {
                    new Server();
                }
                catch(CannotCreateServerException e)
                {
                    cli.showErrorMessage(e.getMessage1() + e.getMessage2());
                }
                state = CLIMenuState.ASK_CONNECTION_TYPE;
                cli.reShowCurrentView();
            }
            else if(input.equals("2"))
            {
                state = CLIMenuState.ASK_CONNECTION_TYPE;
                cli.reShowCurrentView();
            }
            else
            {
                cli.showErrorMessage("Not valid input");
                cli.reShowCurrentView();
            }
        }
        else if(state == CLIMenuState.ASK_CONNECTION_TYPE)
        {
            if(input.equals("1"))
            {
                state = CLIMenuState.ASK_NICKNAME;
                connectionType = true;
                cli.reShowCurrentView();
            }
            else if(input.equals("2"))
            {
                state = CLIMenuState.ASK_NICKNAME;
                connectionType = false;
                cli.reShowCurrentView();
            }
            else
            {
                cli.showErrorMessage("Not valid input");
                cli.reShowCurrentView();
            }
        }
        else if(state == CLIMenuState.ASK_NICKNAME)
        {
            cli.setAssociatedPlayerNickname(input);
            if(connectionType)
                new SocketNetworkHandler("localhost", 1999, cli);
            else
                new RMINetworkHandler(cli);

            cli.notify(new AddPlayerEvent(cli, input));
        }
    }
}
