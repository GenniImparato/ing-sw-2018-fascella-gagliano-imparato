package it.polimi.se2018.network.socket.server;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.mvc_comunication.messages.AddedDieMessage;
import it.polimi.se2018.mvc_comunication.messages.StartedGameMessage;
import it.polimi.se2018.view.ViewInterface;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server
{
    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private List<ViewInterface> clients;

    private Model       model;
    private Controller  controller;

    public Server()
    {
        clients = new ArrayList<>();

        try
        {
            serverSocket = new ServerSocket(PORT);
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        }
        catch (IOException e){e.printStackTrace();}

        //Creates a ClientManager on this server and
        //runs it in a different thread
        new ClientManager(this);

        model = new Model();
        controller = new Controller(model);
    }

    public synchronized ServerSocket getServerSocket()
    {
        return serverSocket;
    }

    public synchronized void addClient(Socket clientSocket)
    {
        VirtualView vView = new VirtualView(clientSocket);
        vView.attach(controller);   //register the controller as an observer of the created virtual view
        model.attach((vView));      //register the virtual view as an observer of the model
        new Thread(vView).start();
        clients.add(vView);
    }
}
