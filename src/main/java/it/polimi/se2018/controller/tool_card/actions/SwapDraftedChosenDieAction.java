package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

/**
 * This class is used to represent the action of swapping a drafted die with a chosen one
 */
public class SwapDraftedChosenDieAction extends ToolCardAction
{
    /**
     * Constructor
     * @param parameters tool card parameters needed to execute the action
     */
    public SwapDraftedChosenDieAction(ToolCardParameters parameters)
    {
        super(parameters, true);
    }

    /**
     * Executes the action of swapping the dice
     * @param controller the action is executed by the controller
     */
    @Override
    public void execute(Controller controller)
    {
        controller.getModel().swapDraftedChosenDie();
    }
}