package it.polimi.se2018.network.rmi.server;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.network.rmi.client.RMIClientInterface;
import it.polimi.se2018.network.server.VirtualView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote
{
    void addClient(RMIClientInterface client) throws RemoteException;
    void removeClient(RMIClientInterface client) throws RemoteException;
    void notifyController(RMIClientInterface client, Event event) throws RemoteException;
    void checkActivePlayers() throws RemoteException;
}