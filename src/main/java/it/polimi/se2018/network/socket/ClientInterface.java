package it.polimi.se2018.network.socket;

import it.polimi.se2018.mvc_comunication.Message;

import java.util.Observer;

public interface ClientInterface
{
     void start();
     void showErrorMessage(String message);
     void showMessage(String message);

     void showMenu();
     void showTurn();
     void showDraft();
     void showAddDie();
     void showSelectDieFromBoard();
     void showMoveDie();
     void showIncrementDie();
     void reShowCurrentView();

}
