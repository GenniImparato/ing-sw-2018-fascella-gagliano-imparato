package it.polimi.se2018.network.server;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.network.exceptions.CannotCreateServerException;
import it.polimi.se2018.network.rmi.server.RMIServer;
import it.polimi.se2018.network.socket.server.SocketServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This is the class to represent the server of the game.
 */
public class Server
{
    private SocketServer    socketServer;
    private RMIServer       rmiServer;

    private Model           model;
    private Controller      controller;


    /**
     * Constructor that creates a Server that can handle socket and rmi connections; creates the model and the controller
     * @throws CannotCreateServerException if the server has already been created
     */
    public Server() throws CannotCreateServerException
    {
        socketServer = new SocketServer(this);
        rmiServer    = new RMIServer(this);
        model = new Model();
        controller = new Controller(model);
    }

    /**
     * Returns the model
     * @return current model
     */
    public Model getModel() {
        return model;
    }

    /**
     * Returns the controller
     * @return controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Returns the IP of the server
     * @return IP of the server
     */
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

