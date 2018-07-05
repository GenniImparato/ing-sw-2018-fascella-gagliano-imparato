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
     void showSelectDieFromRoundTrack();
     void showReDrawDie();
     void showMoveDie();
     void showIncrementDie();
     void showSelectSameColorDie();
     void reShowCurrentView();
     void showFinalScore();

     void disconnect();

}
