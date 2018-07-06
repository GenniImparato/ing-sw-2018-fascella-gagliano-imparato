package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.network.exceptions.CannotConnectToServerException;
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
        ASK_IP,
        ASK_NICKNAME
    }

    private CLIMenuState    state;
    private boolean         connectionType;
    private String          ip;

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
        else if(state == CLIMenuState.ASK_IP)
        {
            cli.showMessage("Insert IP of the Server:");

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
                    cli.showErrorMessage("Cannot create server: " + e.getMessage());
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
                state = CLIMenuState.ASK_IP;
                connectionType = true;
                cli.reShowCurrentView();
            }
            else if(input.equals("2"))
            {
                state = CLIMenuState.ASK_IP;
                connectionType = false;
                cli.reShowCurrentView();
            }
            else
            {
                cli.showErrorMessage("Not valid input");
                cli.reShowCurrentView();
            }
        }
        else if(state == CLIMenuState.ASK_IP)
        {
            state = CLIMenuState.ASK_NICKNAME;
            ip = input;
            cli.reShowCurrentView();
        }

        else if(state == CLIMenuState.ASK_NICKNAME)
        {
            cli.setAssociatedPlayerNickname(input);

            try
            {
                if (connectionType)
                    new SocketNetworkHandler(ip, cli);
                else
                    new RMINetworkHandler(ip, cli);

                cli.notify(new AddPlayerEvent(cli, input));
            }
            catch(CannotConnectToServerException e)
            {
                cli.showErrorMessage("Cannot connect to Server: " + e.getMessage());
                state = CLIMenuState.ASK_SERVER_CLIENT;
            }
        }
    }
}
