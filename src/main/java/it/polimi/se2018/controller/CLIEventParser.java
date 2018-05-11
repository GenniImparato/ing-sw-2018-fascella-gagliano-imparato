package it.polimi.se2018.controller;

import it.polimi.se2018.events.clievents.CLIEndTurnEvent;
import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.events.clievents.CLIStartGameEvent;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.gameactions.EndTurnAction;
import it.polimi.se2018.model.gameactions.StartGameAction;

public class CLIEventParser
{
    private CLIController controller;

    public CLIEventParser(CLIController controller)
    {
        this.controller = controller;
    }

    public void parseEvent(CLIStartGameEvent event)
    {
        controller.getCli().showNotification("controller notified: " + event.getClass().getSimpleName(), Color.GREEN);
        controller.getGame().executeAction(new StartGameAction());
    }

    public void parseEvent(CLIEndTurnEvent event)
    {
        controller.getCli().showNotification("controller notified: " + event.getClass().getSimpleName(), Color.GREEN);
        controller.getGame().executeAction(new EndTurnAction());
    }

    public void parseEvent(CLIInputEvent event)
    {
        controller.getCli().showNotification("controller notified: " + event.getClass().getSimpleName() + " - Input: " + event.getInput(), Color.GREEN);
        event.getCLIView().control(controller.getGame(), event.getInput());
    }
}
