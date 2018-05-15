package it.polimi.se2018.events;

import it.polimi.se2018.events.events.AddPlayerEvent;

public interface EventVisitor
{
    void visit(AddPlayerEvent event);
}
