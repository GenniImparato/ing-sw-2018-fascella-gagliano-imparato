package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;

/**
 * This class is used to represent the action of rolling a die
 */
public class RollDieAction extends ToolCardAction
{
    /**
     * Constructor
     * @param parameters tool card parameters needed to execute the action
     */
    public RollDieAction(ToolCardParameters parameters)
    {
        super(parameters, true);
    }

    /**
     * Executes the action of rolling the die
     * @param controller the action is executed by the controller
     */
    @Override
    public void execute(Controller controller)
    {
        try
        {
            if (getParameters().isDraftedDie())
                controller.getModel().rollDie(Model.DRAFTET_DIE);
            else if (getParameters().isSelectedDie())
                controller.getModel().rollDie(Model.SELECTED_DIE);
            else if (getParameters().isChosenDie())
                controller.getModel().rollDie(Model.CHOOSEN_DIE);
        }
        catch(ChangeModelStateException e)
        {
            controller.getView().showErrorMessage(e.getMessage());
            controller.getView().reShowCurrentView();
        }
    }
}
