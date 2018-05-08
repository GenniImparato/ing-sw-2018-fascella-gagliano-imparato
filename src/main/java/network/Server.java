package network;

import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.model.Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server extends Thread
{
    private Game                game;
    private ServerSocket        serverSocket;
    private ObjectInputStream   input;
    private ObjectOutputStream  output;

    public Server() throws CannotCreateServerException
    {
        game = new Game();

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
                    new ServerRequestHandler(this, socket).start();
                }
                catch (IOException e)
                {
                    System.out.println("error");
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

    public Game getGameInstance()
    {
        return game;
    }

    public void addNewPlayer(String nickname) throws CannotAddPlayerException
    {
        game.addNewPlayer(nickname);
    }
}
