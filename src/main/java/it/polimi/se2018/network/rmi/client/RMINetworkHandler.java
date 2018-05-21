package it.polimi.se2018.network.rmi.client;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.events.EndTurnEvent;
import it.polimi.se2018.network.client.NetworkHandler;
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
    private RMIClientInterface client;

    public RMINetworkHandler(View clientView)
    {
        super(clientView);

        try
        {
            serverServices = (RMIServerInterface) Naming.lookup("//localhost/RMIServer");

            client = new RMIClientServices(this);
            RMIClientInterface remoteClient = (RMIClientInterface) UnicastRemoteObject.exportObject(client, 0);
            serverServices.addClient(remoteClient);

        }
        catch (MalformedURLException e)
        {
            System.err.println("URL non trovato!");
        }
        catch (RemoteException e)
        {
            System.err.println("Errore di connessione: " + e.getMessage() + "!");
        }
        catch (NotBoundException e)
        {
            System.err.println("Il riferimento passato non è associato a nulla!");
        }
    }

    @Override
    protected void notifyController(Event event)
    {
        try
        {
            serverServices.notifyController(client, event);
        }
        catch(RemoteException e)
        {
            System.out.println("Connection error: " + e.getMessage());
        }

    }
}
