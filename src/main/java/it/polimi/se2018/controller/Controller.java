package it.polimi.se2018.controller;

import it.polimi.se2018.events.clievents.CLIEvent;
import it.polimi.se2018.model.gameactions.GameAction;
import it.polimi.se2018.events.Event;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;
import it.polimi.se2018.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class Controller<T> implements Observer<T>
{
    protected                       Game game;
    protected                       View view;

    public Controller (View view, Game game)
    {
        this.view = view;
        this.game = game;
    }

    public Game getGame()
    {
        return game;
    }

    public View getView()
    {
        return view;
    }
}

