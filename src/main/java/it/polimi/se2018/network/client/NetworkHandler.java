package it.polimi.se2018.network.client;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.utils.Observable;
import it.polimi.se2018.utils.Observer;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

public abstract class NetworkHandler extends Observable<Message> implements Observer<Event>
{
    private View clientView;

    public NetworkHandler(View clientView)
    {
        this.clientView = clientView;

        this.clientView.attach(this);   //The network handler is observer of the view
        this.attach(clientView);                 //the network handler is observable from the view

    }
    public void update(Event event)
    {
        notifyController(event);
    }

    public ViewInterface getView()
    {
        return clientView;
    }

    protected abstract void notifyController(Event event);
    public abstract void disconnect();
}
