package it.polimi.se2018.network.socket.server;
import it.polimi.se2018.network.socket.ClientInterface;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server
{
    private static final int PORT = 1111;
    private ServerSocket serverSocket;
    private List<ClientInterface> clients;

    public Server()
    {
        clients = new ArrayList<>();

        try
        {
            serverSocket = new ServerSocket(PORT);
        }
        catch (IOException e){e.printStackTrace();}

        //Creates a ClientManager on this server and
        //runs it in a different thread
        new ClientManager(this);
    }


    public synchronized ServerSocket getServerSocket()
    {
        return serverSocket;
    }

    public synchronized void addClient(Socket clientSocket)
    {
        VirtualView vView = new VirtualView(clientSocket);
        clients.add(vView);

    }





}
