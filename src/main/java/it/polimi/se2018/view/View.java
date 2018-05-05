package it.polimi.se2018.view;


import it.polimi.se2018.events.*;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;

//every concrete view has to extend this class
public abstract class View extends Observable implements Observer <Message>
{
    protected Game game;

    public View (Game game)
    {
        this.game=game;
    }
    public abstract void start();
    public abstract void showErrorMessage(String message);
    public abstract void showMessage(String message);
}
