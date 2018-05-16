package it.polimi.se2018.mvc_comunication.events.tool_cards_events;

import it.polimi.se2018.mvc_comunication.events.DraftDieEvent;
import it.polimi.se2018.mvc_comunication.events.EventVisitor;
import it.polimi.se2018.view.View;

public class ToolCardDraftDieEvent extends ToolCardEvent
{
    private int dieNum;

    public ToolCardDraftDieEvent(View view, int dieNum)
    {
        super(view);
        this.dieNum = dieNum;
    }

    public int getDieNum()
    {
        return dieNum;
    }

    @Override
    public void acceptVisitor(EventVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public void acceptVisitor(ToolCardEventVisitor visitor)
    {
        visitor.visit(this);
    }
}