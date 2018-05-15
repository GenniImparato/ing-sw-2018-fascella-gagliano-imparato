package it.polimi.se2018.controller;

import it.polimi.se2018.events.events.AddPlayerEvent;
import it.polimi.se2018.events.EventVisitor;

public class EventParser implements EventVisitor
{
    Controller controller;

    public EventParser(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void visit(AddPlayerEvent event)
    {
        controller.addNewPlayer(event.getNickname());
    }
}