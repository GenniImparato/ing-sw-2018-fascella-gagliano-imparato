package it.polimi.se2018.view;


import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.network.client.NetworkHandler;
import it.polimi.se2018.utils.*;

import java.io.Serializable;

//every concrete view has to extend this class
public abstract class View extends Observable<Event> implements Observer <Message>, ViewInterface, Serializable
{
    private transient   Model           model;
    private             String          playerNickname;
    private transient   NetworkHandler  networkHandler;


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

    public NetworkHandler getNetworkHandler()
    {
        return networkHandler;
    }

    public void setNetworkHandler(NetworkHandler networkHandler)
    {
        this.networkHandler = networkHandler;
    }

    public abstract String getStartNotificationString();
    public abstract String getEndNotificationString();
    public abstract String getColorString(Color color);
    public abstract String getColorEndString();
}
