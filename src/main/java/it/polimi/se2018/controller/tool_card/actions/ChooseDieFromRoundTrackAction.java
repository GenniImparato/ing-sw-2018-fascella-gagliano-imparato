package it.polimi.se2018.controller.tool_card.actions;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.tool_card.ToolCardParameters;

/**
 * This class is used to represent the action of choosing a die from the roundtrack
 */
public class ChooseDieFromRoundTrackAction extends ToolCardAction
{
    /**
     * Constructor
     * @param parameters tool card parameters needed to execute the action
     */
    public ChooseDieFromRoundTrackAction(ToolCardParameters parameters)
    {
        super(parameters, false);
    }

    /**
     * Executes the action of choosing a die from the roundtrack
     * @param controller the action is executed by the controller
     */
    @Override
    public void execute(Controller controller)
    {
        controller.getView().showSelectDieFromRoundTrack();
    }
}
