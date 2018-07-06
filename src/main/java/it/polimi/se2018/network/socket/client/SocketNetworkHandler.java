package it.polimi.se2018.network.socket.client;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.network.client.NetworkHandler;
import it.polimi.se2018.network.exceptions.CannotConnectToServerException;
import it.polimi.se2018.network.socket.server.SocketServer;
import it.polimi.se2018.view.ViewInterface;
import it.polimi.se2018.network.socket.NetworkMessage;
import it.polimi.se2018.utils.Observable;
import it.polimi.se2018.utils.Observer;
import it.polimi.se2018.view.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketNetworkHandler extends NetworkHandler implements Runnable
{
    private Socket socketToServer;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private SocketNetworkMessageAnalyzer messageAnalyzer;

    private boolean connected;

    public SocketNetworkHandler(String ip, View clientView) throws CannotConnectToServerException
    {
        super(clientView);
        connected = true;

        messageAnalyzer = new SocketNetworkMessageAnalyzer(this);

        InetAddress address;

        if(ip == null || ip.equals(""))
            throw new CannotConnectToServerException("Invalid IP!");
        try
        {
            address = InetAddress.getByName(ip);
        }
        catch (UnknownHostException e)
        {
            throw new CannotConnectToServerException("Invalid IP: " + ip);
        }

        try
        {
            address = InetAddress.getByName(ip);
            socketToServer = new Socket(address , SocketServer.PORT);
            this.in = new ObjectInputStream(socketToServer.getInputStream());
            this.out = new ObjectOutputStream(socketToServer.getOutputStream());
        }
        catch(IOException e)
        {
            throw new CannotConnectToServerException("Cannot reach server!");
        }

        new Thread(this).start();
    }

    @Override
    public void run()
    {
        while(connected)
        {
            try
            {
                NetworkMessage message = (NetworkMessage)in.readObject();
                messageAnalyzer.analyzeMessage(message);
            }
            catch (IOException|ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }

    protected synchronized void notifyController(Event event)
    {
        try
        {
            out.writeObject(new NetworkMessage(event));
        }
        catch(IOException e) {e.printStackTrace();}
    }

    @Override
    public void disconnect()
    {
        connected = false;
        getView().showMenu();
        try
        {
            out.writeObject(new NetworkMessage());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
