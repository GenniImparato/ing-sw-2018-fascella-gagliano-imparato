package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

/**
 * Generic action that a tool card can execute
 */
public abstract class  ToolCardAction
{
    private     ToolCardParameters  params;
    private     boolean             instant;

    /**
     * Constructor that sets if an action is instant or not and the parameters for that action
     * @param parameters parameters needed to perform the action
     * @param instant tells if the action can be done instantly (without the player interaction)
     */
    ToolCardAction(ToolCardParameters parameters, boolean instant)
    {
        this.params = parameters;
        this.instant = instant;
    }

    /**
     * Executes the tool card action; it will be overridden by subclasses in order to execute the action properly
     * @param controller the action is executed by the controller
     */
    public abstract void execute(Controller controller);

    /**
     * Returns the tool card parameters
     * @return tool card parameters
     */
    public ToolCardParameters getParameters()
    {
        return params;
    }

    /**
     * Tells if the action is instant or not
     * @return true if the action is instant, false otherwise
     */
    public boolean isInstant()
    {
        return instant;
    }
}
