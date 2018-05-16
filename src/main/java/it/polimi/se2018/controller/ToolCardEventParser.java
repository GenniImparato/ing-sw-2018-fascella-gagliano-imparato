package it.polimi.se2018.controller;

import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.mvc_comunication.events.tool_cards_events.ToolCardDraftDieEvent;
import it.polimi.se2018.mvc_comunication.events.tool_cards_events.ToolCardEventVisitor;

public class ToolCardEventParser implements ToolCardEventVisitor
{
    Controller controller;

    public ToolCardEventParser(Controller controller)
    {
        this.controller = controller;
    }

    public void visit(ToolCardDraftDieEvent event)
    {
        try
        {
            controller.draftDie(event.getDieNum());
            controller.nextToolCardStep();
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }
}
