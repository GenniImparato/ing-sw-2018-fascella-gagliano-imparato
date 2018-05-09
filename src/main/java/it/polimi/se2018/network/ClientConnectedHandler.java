package it.polimi.se2018.network;

import it.polimi.se2018.model.Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnectedHandler extends Thread
{
    private Server              server;
    private ObjectInputStream   input;
    private ObjectOutputStream  output;

    public ClientConnectedHandler(Server server, Socket socket) throws IOException
    {
        this.server = server;
        output = new ObjectOutputStream(socket.getOutputStream());
        output.write(0);
        input = new ObjectInputStream((socket.getInputStream()));
    }

    public synchronized void sendGameInstanceToClient()
    {
        try
        {
            output.writeObject(server.getGameInstance());
            output.flush();
            System.out.println("Sended game to client");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private synchronized void readGameInstanceFromClient()
    {
        try
        {
            Game game = (Game)input.readObject();
            server.updateGameInstance(game);
            System.out.println("readed game form client");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        readGameInstanceFromClient();
    }
}
