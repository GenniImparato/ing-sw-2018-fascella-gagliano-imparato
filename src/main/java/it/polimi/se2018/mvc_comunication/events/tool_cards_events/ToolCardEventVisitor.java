package it.polimi.se2018.mvc_comunication.events.tool_cards_events;

public interface ToolCardEventVisitor
{
    void visit(ToolCardDraftDieEvent event);
}
