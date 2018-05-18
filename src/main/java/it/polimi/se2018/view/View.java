package it.polimi.se2018.view;


import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.network.socket.ClientInterface;
import it.polimi.se2018.utils.*;

//every concrete view has to extend this class
public abstract class View extends Observable<Event> implements Observer <Message>, ClientInterface
{
    private Model       model;
    private String      playerNickname;

    protected Logger      logger;

    public View()
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

    public Logger getLogger()
    {
        return logger;
    }

}
