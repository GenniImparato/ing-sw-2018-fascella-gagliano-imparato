package it.polimi.se2018.mvc_comunication.events.tool_cards_events;

import it.polimi.se2018.mvc_comunication.events.Event;
import it.polimi.se2018.mvc_comunication.events.EventVisitor;
import it.polimi.se2018.view.View;

public abstract class ToolCardEvent extends Event implements ToolCardEventVisitable
{
    public ToolCardEvent(View view)
    {
        super(view);
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }
}
