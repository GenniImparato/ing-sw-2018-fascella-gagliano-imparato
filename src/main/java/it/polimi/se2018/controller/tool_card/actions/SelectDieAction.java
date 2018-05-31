package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

public class SelectDieAction extends ToolCardAction
{
    public SelectDieAction(ToolCardParameters parameters)
    {
        super(parameters);
    }

    @Override
    public void execute(Controller controller)
    {
        controller.getView().showAddDie();
    }
}