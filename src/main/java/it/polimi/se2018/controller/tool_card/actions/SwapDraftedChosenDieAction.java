package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

public class SwapDraftedChosenDieAction extends ToolCardAction
{
    public SwapDraftedChosenDieAction(ToolCardParameters parameters)
    {
        super(parameters, true);
    }

    @Override
    public void execute(Controller controller)
    {
        controller.getModel().swapDraftedChosenDie();
    }
}