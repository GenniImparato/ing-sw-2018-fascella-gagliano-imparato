package it.polimi.se2018.view;

import it.polimi.se2018.mvc_comunication.Message;

import java.util.Observer;

public interface ViewInterface
{
     void start();
     void showErrorMessage(String message);
     void showMessage(String message);
     void showNotification(String message);

     void showMenu();
     void showLobby();
     void showTurn();
     void showDraft();
     void showAddDie();
     void showSelectDieFromBoard();
     void showSelectDieFromDraftPool();
     void showReDrawDie();
     void showMoveDie();
     void showIncrementDie();
     void reShowCurrentView();

     void disconnect();

}
