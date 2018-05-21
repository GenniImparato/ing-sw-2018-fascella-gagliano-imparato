package it.polimi.se2018.network.socket.server;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.view.ViewInterface;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class SocketServer
{
    private static final int PORT = 1000;
    private ServerSocket serverSocket;
    private List<ViewInterface> virtualClients;

    private Server server;

    public SocketServer(Server server)
    {
        this.server = server;
        virtualClients = new ArrayList<>();

        try
        {
            serverSocket = new ServerSocket(PORT);
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        }
        catch (IOException e){e.printStackTrace();}

        //Creates a SocketClientManager on this server and
        //runs it in a different thread
        new SocketClientManager(this);

    }

    public synchronized ServerSocket getServerSocket()
    {
        return serverSocket;
    }

    public synchronized void addClient(Socket clientSocket)
    {
        SocketVirtualView vView = new SocketVirtualView(clientSocket);

        vView.attach(server.getController());   //register the controller as an observer of the created virtual view
        server.getModel().attach((vView));      //register the virtual view as an observer of the model

        new Thread(vView).start();
        virtualClients.add(vView);
    }
}
