package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.network.exceptions.CannotCreateServerException;
import it.polimi.se2018.network.rmi.server.RMIServer;
import it.polimi.se2018.network.socket.server.SocketServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server
{
    private SocketServer    socketServer;
    private RMIServer       rmiServer;

    private Model           model;
    private Controller      controller;


    public Server() throws CannotCreateServerException
    {
        socketServer = new SocketServer(this);
        rmiServer    = new RMIServer(this);
        model = new Model();
        controller = new Controller(model);
    }

    public Model getModel() {
        return model;
    }

    public Controller getController() {
        return controller;
    }

    public String getIP()
    {
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch(UnknownHostException e)
        {
            return "Unknown host IP";
        }
    }
}

