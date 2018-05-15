package it.polimi.se2018.view;


import it.polimi.se2018.events.*;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.utils.*;

//every concrete view has to extend this class
public abstract class View extends Observable<Event> implements Observer <Message>
{
    private Model model;

    public View ()
    {
    }

    public void setModel(Model model)
    {
        this.model = model;
    }
    public Model getModel()
    {
        return model;
    }

    public abstract void start();
    public abstract void showErrorMessage(String message);
    public abstract void showMessage(String message);

    public abstract void showMenu();
}
