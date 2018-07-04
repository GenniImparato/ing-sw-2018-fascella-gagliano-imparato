package it.polimi.se2018.network.socket.server;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.mvc_comunication.events.ClientDisconnectedEvent;
import it.polimi.se2018.network.server.VirtualView;
import it.polimi.se2018.view.ViewInterface;
import it.polimi.se2018.network.socket.NetworkMessage;
import it.polimi.se2018.utils.Observable;
import it.polimi.se2018.utils.Observer;

import java.io.*;
import java.net.Socket;

public class SocketVirtualView extends VirtualView implements Runnable
{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String associatedNickname;

    public SocketVirtualView(Socket clientSocket)
    {
        super();
        this.clientSocket = clientSocket;
        try
        {
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        new Thread(this).start();
    }

    @Override
    //This method waits for messages from SocketServer
    public void run()
    {
        try
        {
            in = new ObjectInputStream(clientSocket.getInputStream());

            while(connected)
            {
                NetworkMessage message = (NetworkMessage)in.readObject();
                analyzeMessage(message);
            }
        }
        catch(IOException|ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start()
    {
        sendToClient(new NetworkMessage("start"));
    }

    @Override
    public synchronized void showErrorMessage(String message)
    {
        sendToClient(new NetworkMessage("showErrorMessage", message));
    }

    @Override
    public synchronized void showMessage(String message)
    {
        sendToClient(new NetworkMessage("showMessage", message));
    }

    @Override
    public void showNotification(String message)
    {

    }

    @Override
    public synchronized void showMenu()
    {
        sendToClient(new NetworkMessage("showMenu"));
    }

    @Override
    public void showLobby()
    {
        sendToClient(new NetworkMessage("showLobby"));
    }

    @Override
    public synchronized void showTurn()
    {
        sendToClient(new NetworkMessage("showTurn"));
    }

    @Override
    public synchronized void showDraft()
    {
        sendToClient(new NetworkMessage("showDraft"));
    }

    @Override
    public synchronized void showAddDie()
    {
        sendToClient(new NetworkMessage("showAddDie"));
    }

    @Override
    public synchronized  void showSelectDieFromBoard()
    {
        sendToClient(new NetworkMessage("showSelectDieFromBoard"));
    }

    @Override
    public void showSelectDieFromRoundTrack()
    {
        sendToClient(new NetworkMessage("showSelectDieFromRoundTrack"));
    }

    @Override
    public void showReDrawDie() {

    }

    @Override
    public synchronized  void showMoveDie()
    {
        sendToClient(new NetworkMessage("showMoveDie"));
    }

    @Override
    public synchronized void showIncrementDie()
    {
        sendToClient(new NetworkMessage("showIncrementDie"));
    }

    @Override
    public void showSelectSameColorDie()
    {
            sendToClient(new NetworkMessage("showSelectSameColorDie"));
    }

    @Override
    public synchronized void reShowCurrentView()
    {

        sendToClient(new NetworkMessage("reShowCurrentView"));
    }

    //Writes a message to the client socket
    private synchronized void sendToClient(NetworkMessage message)
    {
        try
        {
            out.writeObject(message);
        }
        catch(IOException e)
        {
            notify(new ClientDisconnectedEvent(this, associatedNickname));
        }
    }

    private synchronized void analyzeMessage(NetworkMessage message)
    {
        if(message.isEvent())
        {
            message.getEvent().setView(this);

            //intercept AddPLayerEvent to set the virtual view associated player nickname
            if(message.getEvent() instanceof AddPlayerEvent)
                setAssociatedNickname(((AddPlayerEvent)message.getEvent()).getNickname());

            notify(message.getEvent());
        }
        else if(message.isDisconnectMessage())
            disconnect();

    }

    @Override
    protected void notifyView(Message message)
    {
        sendToClient(new NetworkMessage(message));
    }

    @Override
    public synchronized void disconnect()
    {
        connected = false;
        sendToClient(new NetworkMessage()); //send a disconnect message
        notify(new ClientDisconnectedEvent(this, associatedNickname));
    }
}
