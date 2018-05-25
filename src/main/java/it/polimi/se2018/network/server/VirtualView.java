package it.polimi.se2018.network.server;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.network.socket.NetworkMessage;
import it.polimi.se2018.utils.Observable;
import it.polimi.se2018.utils.Observer;
import it.polimi.se2018.view.ViewInterface;

public abstract class VirtualView extends Observable<Event> implements ViewInterface,  Observer<Message>
{
    protected boolean   connected ;
    private   String    associatedNickname;

    public VirtualView()
    {
        connected=true;
    }

    public void update(Message message)
    {
        notifyView(message);
    }

    protected abstract void notifyView(Message message);
    public    abstract void disconnect();

    public boolean isConnected()
    {
        return connected;
    }

    public void setAssociatedNickname(String nickname)
    {
        this.associatedNickname = nickname;
    }

    public String getAssociatedNickname()
    {
        return associatedNickname;
    }
}
