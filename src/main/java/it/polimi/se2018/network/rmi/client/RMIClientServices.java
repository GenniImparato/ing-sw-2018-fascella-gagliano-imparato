package it.polimi.se2018.network.rmi.client;

import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.network.rmi.server.RMIServerServices;

import java.rmi.RemoteException;

public class RMIClientServices implements RMIClientInterface
{
    RMINetworkHandler handler;

    public RMIClientServices(RMINetworkHandler handler)
    {
        this.handler = handler;
    }

    @Override
    public void notifyView(Message message)
    {
        handler.notify(message);
    }

    @Override
    public void start() throws RemoteException
    {
        handler.getView().start();
    }

    @Override
    public void showErrorMessage(String message) throws RemoteException
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                handler.getView().showErrorMessage(message);
            }
        }).start();

    }

    @Override
    public void showMessage(String message) throws RemoteException
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                handler.getView().showMessage(message);
            }
        }).start();
    }

    @Override
    public void showMenu() throws RemoteException
    {
        handler.getView().showMenu();
    }

    @Override
    public void showLobby() throws RemoteException
    {
        handler.getView().showLobby();
    }

    @Override
    public void showTurn() throws RemoteException
    {
        handler.getView().showTurn();
    }

    @Override
    public void showDraft() throws RemoteException
    {
        handler.getView().showDraft();
    }

    @Override
    public void showAddDie() throws RemoteException
    {
        handler.getView().showAddDie();
    }

    @Override
    public void showSelectDieFromBoard() throws RemoteException
    {
        handler.getView().showSelectDieFromBoard();
    }

    @Override
    public void showMoveDie() throws RemoteException
    {
        handler.getView().showMoveDie();
    }

    @Override
    public void showIncrementDie() throws RemoteException
    {
        handler.getView().showIncrementDie();
    }

    @Override
    public void reShowCurrentView() throws RemoteException
    {
        handler.getView().reShowCurrentView();
    }

    @Override
    public void disconnect() throws RemoteException
    {
        handler.disconnect();
    }
}