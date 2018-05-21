package it.polimi.se2018.network.server;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.network.socket.NetworkMessage;
import it.polimi.se2018.utils.Observable;
import it.polimi.se2018.utils.Observer;
import it.polimi.se2018.view.ViewInterface;

public abstract class VirtualView extends Observable<Event> implements ViewInterface,  Observer<Message>
{
    public void update(Message message)
    {
        notifyView(message);
    }

    protected abstract void notifyView(Message message);
}
