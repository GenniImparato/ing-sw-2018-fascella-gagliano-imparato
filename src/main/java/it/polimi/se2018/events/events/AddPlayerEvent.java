package it.polimi.se2018.events.events;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.events.EventVisitor;
import it.polimi.se2018.view.View;

public class AddPlayerEvent extends Event
{
    private String nickname;

    public AddPlayerEvent(View view, String nickname)
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
