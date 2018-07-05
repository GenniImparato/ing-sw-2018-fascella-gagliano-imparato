package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

public class ReDrawDieAction extends ToolCardAction
{
    public ReDrawDieAction(ToolCardParameters parameters)
    {
        super(parameters, false);
    }

    @Override
    public void execute(Controller controller)
    {
        controller.getModel().reDrawDraftedDie();
        controller.getView().showReDrawDie();
    }
}