package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.controller.tool_card.actions.ToolCardAction;

/**
 * This class is used to represent the action of incrementing or decrementing a die
 */
public class IncrementDecrementDieAction extends ToolCardAction
{
    /**
     * Constructor
     * @param parameters tool card parameters needed to execute the action
     */
    public IncrementDecrementDieAction(ToolCardParameters parameters)
    {
        super(parameters, false);
    }

    /**
     * Executes the action of increment or decrement a die
     * @param controller the action is executed by the controller
     */
    @Override
    public void execute(Controller controller)
    {
        controller.getView().showIncrementDie();
    }
}
