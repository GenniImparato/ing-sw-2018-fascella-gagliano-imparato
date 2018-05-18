package it.polimi.se2018.network.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientManager extends Thread
{
    private Server server;

    public ClientManager(Server server)
    {
        this.server = server;
        start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Socket clientSocket = server.getServerSocket().accept();
                server.addClient(clientSocket);
            }
            catch(IOException e){e.printStackTrace();}

        }
    }
}
