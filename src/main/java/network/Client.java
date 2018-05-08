package network;

import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.model.Game;
import network.serverrequests.AddNewPlayerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Serializable
{
    Socket      socket;
    Game        localGameInstance;
    private     ObjectInputStream input;
    private     ObjectOutputStream output;

    public Client(String IP, int port, String nickname) throws CannotConnectToServerException, CannotAddPlayerException
    {
        try
        {
            socket = new Socket(InetAddress.getByName(IP), port);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            sendRequestToServer(new AddNewPlayerRequest(nickname));
        }
        catch(IOException e)
        {
            throw new CannotConnectToServerException();
        }
    }

    public void sendRequestToServer(ServerRequest request)
    {
        try
        {
            output.writeObject(request);
            output.flush();
            request.executeOnClient(this);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public Game getLocalGameInstance()
    {
        return localGameInstance;
    }

    public void downloadGameInstanceFromServer()
    {
        try
        {
            localGameInstance = (Game) input.readObject();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
        }
    }


}
