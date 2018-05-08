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

public class ServerRequestHandler extends Thread
{
    private ObjectInputStream   input;
    private ObjectOutputStream  output;

    private Server              server;

    public ServerRequestHandler(Server server, Socket socket) throws IOException
    {
        this.server = server;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run()
    {
        while(true)
        {
            readRequestFromClient();
        }
    }

    private synchronized void readRequestFromClient()
    {
        try
        {
            ServerRequest request = (ServerRequest)input.readObject();
            request.executeOnServer(server, this);
        }
        catch(IOException e)
        {
            System.out.println("Server message: Cannot send game!");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public synchronized void sendGameInstance()
    {
        try
        {
            output.writeObject(server.getGameInstance());
            output.flush();
            System.out.println("game send!");
        }
        catch(IOException e)
        {
            System.out.println("Server message: Cannot send game!");
        }
    }
}
