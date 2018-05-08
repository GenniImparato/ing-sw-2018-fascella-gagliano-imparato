package it.polimi.se2018.events.clievents;

import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.CLIState;

public class CLIRequestGameInstanceEvent extends CLIEvent
{
    public CLIRequestGameInstanceEvent(CLI cli, CLIState state)
    {
        super(cli, state);
    }
}
