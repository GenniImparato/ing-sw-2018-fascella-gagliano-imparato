package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.mvc_comunication.events.*;

public interface EventVisitor
{
    void visit(AddPlayerEvent event);
    void visit(StartGameEvent event);
    void visit(DraftDieEvent event);
    void visit(SelectDieFromBoardEvent event);
    void visit(AddDraftedDieEvent event);
    void visit(MoveDieEvent event);
    void visit(UseToolCardEvent event);
    void visit(IncrementDraftedDieEvent event);
}
