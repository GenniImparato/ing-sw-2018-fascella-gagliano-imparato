package it.polimi.se2018.controller;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;
import it.polimi.se2018.view.View;
import network.serverrequests.DownloadGameInstanceRequest;

public abstract class Controller implements Observer<Event>
{
    protected Game game;
    protected View view;

    public Controller (View view)
    {
        this.view = view;
        view.attach(this);
    }

    public void downloadLocalGameInstanceFromServer()
    {
        view.getClient().sendRequestToServer(new DownloadGameInstanceRequest());
        game = view.getClient().getLocalGameInstance();
        view.setLocalGameInstance(game);
    }
}

