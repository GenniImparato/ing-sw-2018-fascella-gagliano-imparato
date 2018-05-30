package it.polimi.se2018.controller.tool_card_action;

import it.polimi.se2018.controller.Controller;

public class AddDieToBoardAction implements ToolCardAction
{
    @Override
    public void execute(Controller controller)
    {
        controller.getView().showAddDie();
    }
}
