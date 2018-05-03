package it.polimi.se2018.controller;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;
import it.polimi.se2018.view.*;

public class Controller implements Observer <Event>
{
    private Game game;
    private View view;

    public Controller (Game game, View view)
    {
        this.game = game;
        this.view = view;
    }

    @Override
    public void update(Event event)
    {

    }
}
