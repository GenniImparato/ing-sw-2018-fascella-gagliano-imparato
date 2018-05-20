package it.polimi.se2018.network.socket.client;

public interface SocketClientInterface
{
    void start();
    void showErrorMessage(String message);
    void showMessage(String message);

    void showMenu();
    void showLobby();
    void showTurn();
    void showDraft();
    void showAddDie();
    void showSelectDieFromBoard();
    void showMoveDie();
    void showIncrementDie();
    void reShowCurrentView();

}
