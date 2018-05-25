package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.mvc_comunication.events.*;
import it.polimi.se2018.mvc_comunication.messages.SelectedPlayerSchemeCardsMessage;

public interface EventVisitor
{
    void visit(SelectSchemeCardEvent event);
    void visit(AddPlayerEvent event);
    void visit(StartGameEvent event);
    void visit(PlayerReadyEvent event);
    void visit(DraftDieEvent event);
    void visit(SelectDieFromBoardEvent event);
    void visit(AddDraftedDieEvent event);
    void visit(MoveDieEvent event);
    void visit(UseToolCardEvent event);
    void visit(IncrementDraftedDieEvent event);
    void visit(EndTurnEvent event);
    void visit(ClientDisconnectedEvent event);
}
