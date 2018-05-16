package it.polimi.se2018.view;


import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.utils.*;

//every concrete view has to extend this class
public abstract class View extends Observable<Event> implements Observer <Message>
{
    private Model       model;
    private String      playerNickname;

    public View ()
    {
    }

    public Model getModel()
    {
        return model;
    }

    public void setAssociatedPlayerNickname(String nickname)
    {
        this.playerNickname = nickname;
    }

    public String getAssociatedPlayerNickname()
    {
        return playerNickname;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }


    public abstract void start();
    public abstract void showErrorMessage(String message);
    public abstract void showMessage(String message);

    public abstract void showMenu();
    public abstract void showTurn();
    public abstract void showDraft();
    public abstract void showAddDie();
    public abstract void showSelectDieFromBoard();
    public abstract void showMoveDie();
    public abstract void showIncrementDie();
    public abstract void reShowCurrentView();
}
