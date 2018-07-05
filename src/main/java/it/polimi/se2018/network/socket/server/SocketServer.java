package it.polimi.se2018.network.socket.server;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.network.exceptions.CannotCreateServerException;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.VirtualView;
import it.polimi.se2018.view.ViewInterface;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SocketServer
{
    public static final int PORT = 1999;
    private     ServerSocket        serverSocket;
    protected   List<VirtualView>   virtualClients;

    private Server server;

    public SocketServer(Server server) throws CannotCreateServerException
    {
        this.server = server;
        virtualClients = new CopyOnWriteArrayList();

        try
        {
            serverSocket = new ServerSocket(PORT);
        }
        catch (IOException e)
        {
            throw new CannotCreateServerException(e.getMessage());
        }

        //Creates a SocketClientAdder on this server and
        //runs it in a different thread
        new SocketClientAdder(this);
        new SocketClientRemover(this);

    }

    public ServerSocket getServerSocket()
    {
        return serverSocket;
    }

    public void addClient(Socket clientSocket)
    {
        SocketVirtualView vView = new SocketVirtualView(clientSocket);

        vView.attach(server.getController());   //register the controller as an observer of the created virtual view
        server.getModel().attach((vView));      //register the virtual view as an observer of the model

        virtualClients.add(vView);
    }

    public void removeClient(VirtualView virtualClient)
    {
        server.getModel().detach(virtualClient);
        virtualClients.remove(virtualClient);
    }

    public void checkClientConnections()
    {

        for(VirtualView virtualClient : virtualClients)
        {
            if(!virtualClient.isConnected())
                removeClient(virtualClient);
        }
    }

    public void checkActivePlayers()
    {

        for(VirtualView virtualClient : virtualClients)
        {
            for(Player player: server.getModel().getPlayers())
            {
                if(virtualClient.getAssociatedNickname() != null
                        &&     !player.isActive()
                        &&     player.getNickname().equals(virtualClient.getAssociatedNickname()))
                    virtualClient.disconnect();
            }
        }
    }
}
