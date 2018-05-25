package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.ViewInterface;

public class PlayerReadyEvent extends Event
{
    private String nickname;
    private boolean ready;

    public PlayerReadyEvent(ViewInterface view, String nickname, boolean ready)
    {
        super(view);
        this.nickname = nickname;
        this.ready = ready;
    }

    public String getNickname()
    {
        return nickname;
    }

    public boolean isReady()
    {
        return ready;
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}
