package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

public abstract class  ToolCardAction
{
    private     ToolCardParameters  params;
    private     boolean             instant;

    ToolCardAction(ToolCardParameters parameters, boolean instant)
    {
        this.params = parameters;
        this.instant = instant;
    }

    public abstract void execute(Controller controller);

    public ToolCardParameters getParameters()
    {
        return params;
    }

    public boolean isInstant()
    {
        return instant;
    }
}
