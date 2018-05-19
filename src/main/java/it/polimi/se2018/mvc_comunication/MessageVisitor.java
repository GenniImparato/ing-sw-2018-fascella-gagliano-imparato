package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.mvc_comunication.messages.*;

public interface MessageVisitor
{
    void visit(SelectedPlayerSchemeCardsMessage message);
    void visit(ChosenSchemeCardMessage message);
    void visit(AddedDieMessage message);
    void visit(AddedPlayerMessage message);
    void visit(DraftedDieMessage message);
    void visit(ReturnedDieMessage message);
    void visit(SelectedDieMessage message);
    void visit(StartedGameMessage message);
    void visit(UsingToolCardMessage message);
    void visit(ToolCardActionExecutedMessage message);
    void visit(ChangedDraftedDieMessage message);
    void visit(MovedDieMessage message);
    void visit(BegunTurnMessage message);
}
