package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.mvc_comunication.messages.*;

public interface MessageVisitor
{
    void visit(AddedDieMessage message);
    void visit(AddedPlayerMessage message);
    void visit(DraftedDieMessage message);
    void visit(ReturnedDieMessage message);
    void visit(SelectedDieMessage message);
    void visit(StartedGameMessage message);
    void visit(UsingToolCardMessage message);
    void visit(ToolCardActionExecutedMessage message);
    void visit(ChangedDraftedDieMessage message);
}
