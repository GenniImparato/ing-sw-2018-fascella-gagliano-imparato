package it.polimi.se2018.events.clievents;

import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.CLIState;

public class CLIBeginRoundEvent extends CLIEvent
{
    public CLIBeginRoundEvent(CLI cli, CLIState state)
    {
        super(cli, state);
    }
}
