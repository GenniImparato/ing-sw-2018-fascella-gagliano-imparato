package it.polimi.se2018.controller;

import it.polimi.se2018.controller.gameactions.GameAction;
import it.polimi.se2018.events.Event;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;
import it.polimi.se2018.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class Controller implements Observer<Event>
{
    protected                       Game game;
    protected                       View view;

    private List<GameAction>        actionsCrhonology;

    public Controller (View view, Game game)
    {
        actionsCrhonology = new ArrayList<>();
        this.view = view;
        this.game = game;
    }

    public void saveAction(GameAction action)
    {
        actionsCrhonology.add(action);
    }
}

