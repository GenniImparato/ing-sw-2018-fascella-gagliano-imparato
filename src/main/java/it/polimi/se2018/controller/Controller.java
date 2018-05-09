package it.polimi.se2018.controller;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;
import it.polimi.se2018.view.View;

public abstract class Controller implements Observer<Event>
{
    protected Game game;
    protected View view;

    public Controller (View view, Game game)
    {
        this.view = view;
        this.game = game;
    }
}

