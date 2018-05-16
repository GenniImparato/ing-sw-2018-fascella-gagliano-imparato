package it.polimi.se2018.view;


import it.polimi.se2018.mvc_comunication.events.Event;
import it.polimi.se2018.mvc_comunication.messages.Message;
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
    public abstract void showPlayerTurn();
    public abstract void showPlayerDraft();
    public abstract void showPlayerAddDie();
    public abstract void showPlayerToolCardDraftDie();
    public abstract void reShowCurrentView();
}
