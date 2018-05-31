package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

public class DraftDieAction extends ToolCardAction
{
    public DraftDieAction(ToolCardParameters parameters)
    {
        super(parameters);
    }

    @Override
    public void execute(Controller controller)

    {
        controller.getView().showDraft();
    }

}
