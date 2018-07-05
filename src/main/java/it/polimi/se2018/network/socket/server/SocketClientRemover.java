package it.polimi.se2018.network.socket.server;

public class SocketClientRemover extends Thread
{
    private SocketServer socketServer;

    public SocketClientRemover(SocketServer socketServer)
    {
        this.socketServer = socketServer;
        start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            socketServer.checkActivePlayers();
            socketServer.checkClientConnections();
        }
    }
}