package it.polimi.se2018.network.socket.client;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.view.ViewInterface;
import it.polimi.se2018.network.socket.NetworkMessage;
import it.polimi.se2018.utils.Observable;
import it.polimi.se2018.utils.Observer;
import it.polimi.se2018.view.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkHandler extends Observable<Message> implements Observer<Event>, Runnable
{
    private Socket socketToServer;
    private ViewInterface clientView;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ClientNetworkMessageAnalyzer messageAnalyzer;

    public NetworkHandler(String host, int port, View clientView)
    {
        this.clientView = clientView;
        clientView.attach(this);
        this.attach(clientView);
        messageAnalyzer = new ClientNetworkMessageAnalyzer(this);

        try
        {
            socketToServer = new Socket(host,port);
            this.in = new ObjectInputStream(socketToServer.getInputStream());
            this.out = new ObjectOutputStream(socketToServer.getOutputStream());
        }
        catch(IOException e){e.printStackTrace();}

        new Thread(this).start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                NetworkMessage message = (NetworkMessage)in.readObject();
                messageAnalyzer.analyzeMessage(message);
            }
            catch (IOException|ClassNotFoundException e) {e.printStackTrace();}
        }
    }

    private synchronized void sendToServer(Event event)
    {
        try
        {
            out.writeObject(new NetworkMessage(event));
        }
        catch(IOException e) {e.printStackTrace();}
    }

    public synchronized ViewInterface getClient() {
        return clientView;
    }

    @Override
    public synchronized void update(Event event)
    {
        sendToServer(event);
    }
}
