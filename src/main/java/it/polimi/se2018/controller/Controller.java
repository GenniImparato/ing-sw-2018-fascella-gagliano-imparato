package it.polimi.se2018.controller;

import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;

public abstract class Controller<T> implements Observer<T>
{
    protected Game game;

    public Controller (Game game)
    {
        this.game = game;
    }
}

