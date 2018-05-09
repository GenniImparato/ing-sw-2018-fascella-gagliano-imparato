package it.polimi.se2018.network;

import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.model.Game;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Serializable
{
    Socket      socket;
    Game        game;
    private     ObjectInputStream input;
    private     ObjectOutputStream output;

    public Client(Game game, String IP, int port, String nickname) throws CannotConnectToServerException, CannotAddPlayerException
    {
        this.game = game;

        try
        {
            socket = new Socket(InetAddress.getByName(IP), port);
            output = new ObjectOutputStream(socket.getOutputStream());
            output.write(0);
            input = new ObjectInputStream(socket.getInputStream());

            new Thread("ReaderFromServerThread")
            {
                public void run()
                {
                    readGameInstanceFromServer();
                }
            }.start();
        }
        catch(IOException e)
        {
            throw new CannotConnectToServerException();
        }
    }

    public synchronized void sendGameInstanceToServer()
    {
        try
        {
            output.writeObject(game);
            output.flush();
            System.out.println("Sended game to server");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public synchronized void readGameInstanceFromServer()
    {
        try
        {
            game = (Game)input.readObject();
            System.out.println("Readed game from server");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
