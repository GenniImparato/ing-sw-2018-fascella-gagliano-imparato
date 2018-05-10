package it.polimi.se2018.events.clievents;

import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.CLIState;

public class CLIBeginGameEvent extends CLIEvent
{
    public CLIBeginGameEvent(CLI cli, CLIState state)
    {
        super(cli, state);
    }
}
