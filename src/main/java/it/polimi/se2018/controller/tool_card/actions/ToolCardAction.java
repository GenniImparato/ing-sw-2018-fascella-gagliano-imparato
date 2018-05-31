package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

public abstract class  ToolCardAction
{
    private ToolCardParameters params;

    ToolCardAction(ToolCardParameters parameters)
    {
        this.params = parameters;
    }

    public abstract void execute(Controller controller);

    public ToolCardParameters getParameters()
    {
        return params;
    }
}
