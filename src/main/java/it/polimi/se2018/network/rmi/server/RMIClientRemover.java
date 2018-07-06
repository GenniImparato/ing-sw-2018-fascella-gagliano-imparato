package it.polimi.se2018.network.rmi.server;

import it.polimi.se2018.network.socket.server.SocketServer;

import java.rmi.RemoteException;

public class RMIClientRemover extends Thread
{
    private RMIServer rmiServer;

    public RMIClientRemover(RMIServer rmiServer)
    {
        this.rmiServer = rmiServer;
        start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            rmiServer.checkActivePlayers();
            //socketServer.checkClientConnections();
        }
    }
}