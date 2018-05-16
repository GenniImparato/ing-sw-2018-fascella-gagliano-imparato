package it.polimi.se2018.mvc_comunication.events;

import it.polimi.se2018.mvc_comunication.events.tool_cards_events.ToolCardEvent;

public interface EventVisitor
{
    void visit(AddPlayerEvent event);
    void visit(StartGameEvent event);
    void visit(DraftDieEvent event);
    void visit(AddDraftedDieEvent event);
    void visit(UseToolCardEvent event);
    void visit(ToolCardEvent event);
}
