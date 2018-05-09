package it.polimi.se2018.network;

import it.polimi.se2018.model.Game;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Server extends Thread
{
    private Game                                game;
    private ServerSocket                        serverSocket;
    private ArrayList<ClientConnectedHandler>   handlers;

    public Server() throws CannotCreateServerException
    {
        handlers = new ArrayList<>();

        try
        {
            serverSocket = new ServerSocket(0);
            start();
        }
        catch(IOException e)
        {
            throw new CannotCreateServerException();
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Socket socket = serverSocket.accept();

                try
                {
                    new ClientConnectedHandler(this, socket).start();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            catch (IOException e)
            {
                System.out.println("error");
                e.printStackTrace();
            }
        }
    }

    public synchronized void updateGameInstance(Game game)
    {
        this.game = game;

        for(ClientConnectedHandler handler : handlers)      //the instance has been upted by some client
            handler.sendGameInstanceToClient();             //needs to send the updated one to every client
    }

    public Game getGameInstance()
    {
        return game;
    }

    public String getIP()
    {
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch(UnknownHostException e)
        {
            return " ";
        }
    }

    public int getPort()
    {
        return serverSocket.getLocalPort();
    }
}
