package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.ViewInterface;

public class ClientDisconnectedEvent extends Event
{
    private String nickname;

    public ClientDisconnectedEvent(ViewInterface view, String nickname)
    {
        super(view);
        this.nickname = nickname;
    }

    public String getNickname()
    {
        return nickname;
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}
