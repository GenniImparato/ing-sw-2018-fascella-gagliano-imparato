package it.polimi.se2018.controller.tool_card.actions;


import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

/**
 * This class is used to represent the action of skipping a turn
 */
public class SkipTurnAction extends ToolCardAction
{
    /**
     * Constructor
     * @param parameters tool card parameters needed to execute the action
     */
    public SkipTurnAction(ToolCardParameters parameters)
    {
        super(parameters, true);
    }

    /**
     * Executes the action of skipping the next player turn
     * @param controller the action is executed by the controller
     */
    @Override
    public void execute(Controller controller)
    {
        controller.skipNextPlayerTurn();
        controller.endPlayerTurn();
    }
}