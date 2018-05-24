package it.polimi.se2018.network.socket.server;

import java.io.IOException;
import java.net.Socket;

public class SocketClientAdder extends Thread
{
    private SocketServer socketServer;

    public SocketClientAdder(SocketServer socketServer)
    {
        this.socketServer = socketServer;
        start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Socket clientSocket = socketServer.getServerSocket().accept();

                socketServer.addClient(clientSocket);
            }
            catch(IOException e){e.printStackTrace();}

        }
    }
}
