package it.polimi.se2018.controller.tool_card.actions;


import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;

/**
 * This class is used to represent the action of rerolling the draftpool
 */
public class ReRollDraftPoolAction extends ToolCardAction
{
    /**
     * Constructor
     * @param parameters tool card parameters needed to execute the action
     */
    public ReRollDraftPoolAction(ToolCardParameters parameters)
    {
        super(parameters, true);
    }

    /**
     * Executes the action of rerolling the draftpool
     * @param controller the action is executed by the controller
     */
    @Override
    public void execute(Controller controller)
    {
        controller.getModel().rollDraftPool();
    }
}