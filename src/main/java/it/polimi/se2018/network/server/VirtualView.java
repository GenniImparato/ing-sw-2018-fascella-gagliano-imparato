package it.polimi.se2018.network.server;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;
import it.polimi.se2018.network.socket.NetworkMessage;
import it.polimi.se2018.utils.Observable;
import it.polimi.se2018.utils.Observer;
import it.polimi.se2018.view.ViewInterface;

/**
 * Class used to represent the generic virtual view. It interacts with the controller, the model (in the server) and
 * with the network handler (of a client) respecting the MVC pattern.
 */
public abstract class VirtualView extends Observable<Event> implements ViewInterface,  Observer<Message>
{
    protected boolean   connected ;
    private   String    associatedNickname;

    /**
     * Constructor
     */
    public VirtualView()
    {
        connected=true;
    }

    /**
     * Notifies the view
     * @param message message
     */
    public void update(Message message)
    {
        notifyView(message);
    }


    protected abstract void notifyView(Message message);


    public abstract void disconnect();

    /**
     * Tells if the virtual view is connected to a local client view
     * @return true if the virtual view is connected to a local client view
     */
    public boolean isConnected()
    {
        return connected;
    }

    /**
     * Associates a nickname of a player to a virtual view
     * @param nickname nickname of the player
     */
    public void setAssociatedNickname(String nickname)
    {
        this.associatedNickname = nickname;
    }

    /**
     * Returns the nickname of the player associated to this virtual view
     * @return nickname of the player associated to this virtual view
     */
    public String getAssociatedNickname()
    {
        return associatedNickname;
    }
}
