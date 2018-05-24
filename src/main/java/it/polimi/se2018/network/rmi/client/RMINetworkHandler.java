package it.polimi.se2018.network.rmi.client;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.events.EndTurnEvent;
import it.polimi.se2018.network.client.NetworkHandler;
import it.polimi.se2018.network.exceptions.CannotConnectToServerException;
import it.polimi.se2018.network.rmi.server.RMIServerInterface;
import it.polimi.se2018.network.rmi.server.RMIServerServices;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.CLI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RMINetworkHandler extends NetworkHandler
{
    private RMIServerInterface serverServices;
    private RMIClientInterface remoteClient;

    public RMINetworkHandler(String IP, View clientView) throws CannotConnectToServerException
    {
        super(clientView);

        try
        {
            serverServices = (RMIServerInterface) Naming.lookup("//" + IP + "/RMIServer");

            remoteClient = (RMIClientInterface) UnicastRemoteObject.exportObject(new RMIClientServices(this), 0);
            serverServices.addClient(remoteClient);

        }
        catch (MalformedURLException e)
        {
            throw new CannotConnectToServerException("Cannot find URL!");
        }
        catch (RemoteException e)
        {
            throw new CannotConnectToServerException(e.getMessage());
        }
        catch (NotBoundException e)
        {
            throw new CannotConnectToServerException("Remote element not bound!");
        }
    }

    @Override
    protected void notifyController(Event event)
    {
        try
        {
            serverServices.notifyController(remoteClient, event);
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void disconnect()
    {
        try
        {
            serverServices.removeClient(remoteClient);
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
