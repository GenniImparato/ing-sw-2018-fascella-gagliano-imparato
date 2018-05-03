package it.polimi.se2018.view;


import it.polimi.se2018.events.*;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;


public abstract class View extends Observable <Event> implements Observer <Message>
{
    protected Game game;

    public View (Game game)
    {
        this.game=game;
    }
}
