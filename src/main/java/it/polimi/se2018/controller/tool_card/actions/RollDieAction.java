package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;

public class RollDieAction extends ToolCardAction
{
    public RollDieAction(ToolCardParameters parameters)
    {
        super(parameters);
    }

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
