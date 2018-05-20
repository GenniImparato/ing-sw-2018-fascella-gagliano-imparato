package it.polimi.se2018.network.socket.server;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.view.ViewInterface;
import it.polimi.se2018.network.socket.NetworkMessage;
import it.polimi.se2018.utils.Observable;
import it.polimi.se2018.utils.Observer;

import java.io.*;
import java.net.Socket;

public class VirtualView extends Observable<Event> implements ViewInterface, Runnable, Observer<Message>
{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public VirtualView(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
        try
        {
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        catch(IOException e) { e.printStackTrace();}
    }

    @Override
    //This method waits for messages from Server
    public void run()
    {
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());

            while(true)
            {
                NetworkMessage message = (NetworkMessage)in.readObject();
                analyzeMessage(message);
            }
        }catch(IOException|ClassNotFoundException e){e.printStackTrace();}
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
        catch(IOException e) {e.printStackTrace();}
    }

    private synchronized void analyzeMessage(NetworkMessage message)
    {
        if(message.isEvent())
        {
            message.getEvent().setView(this);
        }
            notify(message.getEvent());
    }

    @Override

    public synchronized void update(Message message)
    {
        sendToClient(new NetworkMessage(message));
    }
}
