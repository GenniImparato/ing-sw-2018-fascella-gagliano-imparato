package it.polimi.se2018.mvc_comunication;

import it.polimi.se2018.mvc_comunication.messages.*;

/**
 * This interface is part of the Visitor design pattern.
 * A message visitor can parse a specific message thanks to the overridden visit method.
 */
public interface MessageVisitor
{
    void visit(SelectedPlayerSchemeCardsMessage message);
    void visit(ChosenSchemeCardMessage message);
    void visit(AddedDieMessage message);
    void visit(AddedPlayerMessage message);
    void visit(RemovedPlayerMessage message);
    void visit(DraftedDieMessage message);
    void visit(ReturnedDieMessage message);
    void visit(SelectedDieMessage message);
    void visit(StartedGameMessage message);
    void visit(PlayerReadyMessage message);
    void visit(UsingToolCardMessage message);
    void visit(ToolCardEndedMessage message);
    void visit(ChangedDraftedDieMessage message);
    void visit(MovedDieMessage message);
    void visit(BegunTurnMessage message);
    void visit(ModifiedDieMessage message);
    void visit(ReRolledDraftPoolMessage message);
    void visit(UpdatedStartTimerMessage message);
    void visit(DisconnectedPlayerMessage message);
    void visit(UpdatedTurnTimerMessage message);
    void visit(EndGameMessage message);
    void visit(ReconnectedPlayerMessage message);
}
