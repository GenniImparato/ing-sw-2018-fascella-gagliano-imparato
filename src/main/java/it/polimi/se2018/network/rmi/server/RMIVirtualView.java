package it.polimi.se2018.network.rmi.server;

import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.network.rmi.client.RMIClientInterface;
import it.polimi.se2018.network.server.VirtualView;
import it.polimi.se2018.network.socket.NetworkMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

public class RMIVirtualView extends VirtualView
{
    RMIClientInterface remoteclient;

    public RMIVirtualView(RMIClientInterface remoteClient)
    {
        super();
        this.remoteclient = remoteClient;
    }

    @Override
    public void start()
    {
        try
        {
            remoteclient.start();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorMessage(String message)
    {
        try
        {
            remoteclient.showErrorMessage(message);
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message)
    {
        try
        {
            remoteclient.showMessage(message);
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showMenu()
    {
        try
        {
            remoteclient.showMenu();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showLobby()
    {
        try
        {
            remoteclient.showLobby();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showTurn()
    {
        try
        {
            remoteclient.showTurn();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showDraft()
    {
        try
        {
            remoteclient.showDraft();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showAddDie()
    {
        try
        {
            remoteclient.showAddDie();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public  void showSelectDieFromBoard()
    {
        try
        {
            remoteclient.showSelectDieFromBoard();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showSelectDieFromDraftPool() {

    }

    @Override
    public void showReDrawDie()
    {

    }

    @Override
    public  void showMoveDie()
    {
        try
        {
            remoteclient.showMoveDie();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showIncrementDie()
    {
        try
        {
            remoteclient.showIncrementDie();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void reShowCurrentView()
    {
        try
        {
            remoteclient.reShowCurrentView();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void notifyView(Message message)
    {
        try
        {
            remoteclient.notifyView(message);
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect()
    {
        try
        {
            remoteclient.disconnect();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
