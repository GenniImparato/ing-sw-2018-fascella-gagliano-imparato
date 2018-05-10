package it.polimi.se2018.controller;

import it.polimi.se2018.events.clievents.CLIEndTurnEvent;
import it.polimi.se2018.events.clievents.CLIInputEvent;
import it.polimi.se2018.events.clievents.CLIStartGameEvent;
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
        controller.getGame().executeAction(new StartGameAction());
    }

    public void parseEvent(CLIEndTurnEvent event)
    {
        controller.getGame().executeAction(new StartGameAction());
    }

    public void parseEvent(CLIInputEvent event)
    {
        event.getCLIView().control(event.getInput());
    }
}
