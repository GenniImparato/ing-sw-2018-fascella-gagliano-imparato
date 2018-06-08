package it.polimi.se2018.controller.tool_card.actions;


import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;
import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.exceptions.ChangeModelStateException;

public class ReRollDraftPoolAction extends ToolCardAction
{
    public ReRollDraftPoolAction(ToolCardParameters parameters)
    {
        super(parameters, true);
    }

    @Override
    public void execute(Controller controller)
    {
        controller.getModel().rollDraftPool();
    }
}