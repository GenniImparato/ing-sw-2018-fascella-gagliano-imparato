package it.polimi.se2018.controller.tool_card.actions;


import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

/**
 * This class is used to represent the action of moving a selected die
 */
public class MoveSelectedDieAction extends ToolCardAction
{
    public MoveSelectedDieAction(ToolCardParameters parameters)
    {
        super(parameters, false);
    }

    /**
     * Executes the action of selecting a die from a board
     * @param controller the action is executed by the controller
     */
    @Override
    public void execute(Controller controller)
    {
        controller.getView().showMoveDie();
    }
}