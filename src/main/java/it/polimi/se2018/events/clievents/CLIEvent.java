package it.polimi.se2018.events.clievents;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.CLIState;

public abstract class CLIEvent implements Event
{
    protected CLI           cli;
    protected CLIState      state;

    public CLIEvent(CLI cli, CLIState state)
    {
        this.cli = cli;
        this.state = state;
    }

    public CLI getCli()
    {
        return cli;
    }

    public CLIState getState()
    {
        return state;
    }
}
