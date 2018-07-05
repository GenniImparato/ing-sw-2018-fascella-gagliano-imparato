package it.polimi.se2018.network.rmi.client;

import it.polimi.se2018.mvc_comunication.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote
{
    void notifyView(Message message)        throws RemoteException;

    void start()                            throws RemoteException;
    void showErrorMessage(String message)   throws RemoteException;
    void showMessage(String message)        throws RemoteException;

    void showMenu()                         throws RemoteException;
    void showLobby()                        throws RemoteException;
    void showTurn()                         throws RemoteException;
    void showDraft()                        throws RemoteException;
    void showAddDie()                       throws RemoteException;
    void showSelectDieFromBoard()           throws RemoteException;
    void showMoveDie()                      throws RemoteException;
    void showReDrawDie()                    throws RemoteException;
    void showIncrementDie()                 throws RemoteException;
    void reShowCurrentView()                throws RemoteException;
    void showSelectDieFromRoundTrack()      throws RemoteException;
    void showSelectSameColorDie()           throws RemoteException;
    void showFinalScore()                   throws RemoteException;

    void disconnect()                       throws RemoteException;
}
