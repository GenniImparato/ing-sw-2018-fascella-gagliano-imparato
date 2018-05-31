package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.controller.tool_card.actions.ToolCardAction;

public class IncrementDecrementDieAction extends ToolCardAction
{
    IncrementDecrementDieAction(ToolCardParameters parameters)
    {
        super(parameters);
    }

    @Override
    public void execute(Controller controller)
    {
        controller.getView().showIncrementDie();
    }
}
