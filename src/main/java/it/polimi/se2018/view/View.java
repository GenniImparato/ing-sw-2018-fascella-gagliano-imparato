package it.polimi.se2018.view;


import it.polimi.se2018.events.*;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.observer.*;

//every concrete view has to extend this class
public abstract class View<T> extends Observable<T> implements Observer <Message>
{
    private   Game    game;

    public View (Game game)
    {
        setGameInstance(game);
    }

    public void setGameInstance(Game game)
    {
        this.game = game;
    }

    public Game getGameInstance()
    {
        return game;
    }

    public abstract void start();
    public abstract void showErrorMessage(String message);
    public abstract void showMessage(String message);
}
