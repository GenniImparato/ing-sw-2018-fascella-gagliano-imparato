package it.polimi.se2018.view;


import it.polimi.se2018.events.*;
import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;
import network.CannotConnectToServerException;
import network.Client;
import network.serverrequests.DownloadGameInstanceRequest;

//every concrete view has to extend this class
public abstract class View extends Observable<Event> implements Observer <Message>
{
    protected   Game    game;
    protected   Client  client;

    public View ()
    {
    }

    public void connectToServer(String IP, int port, String nickname) throws CannotConnectToServerException, CannotAddPlayerException
    {
        client = new Client(IP, port, nickname);
    }

    public void setLocalGameInstance(Game game)
    {
        this.game = game;
        game.attach(this);
        refresh();
    }

    public Client getClient()
    {
        return client;
    }

    public abstract void start();
    public abstract void showErrorMessage(String message);
    public abstract void showMessage(String message);
    public abstract void refresh();
}
