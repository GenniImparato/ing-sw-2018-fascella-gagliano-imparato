package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.EventVisitor;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.ViewInterface;

public class SelectSchemeCardEvent extends Event
{
    private int choice;
    private String nickname;

    public SelectSchemeCardEvent(ViewInterface view, String playerNickname, int choice)
    {
        super(view);
        this.choice = choice;
        this.nickname = playerNickname;
    }

    public int getChoice()
    {
        return choice;
    }

    public String getPLayerNickame()
    {
        return nickname;
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}
