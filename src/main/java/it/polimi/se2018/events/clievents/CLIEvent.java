package it.polimi.se2018.events.clievents;

import it.polimi.se2018.controller.CLIEventParser;
import it.polimi.se2018.events.Event;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.CLIState;
import it.polimi.se2018.view.cli.views.CLIView;

public abstract class CLIEvent implements Event
{
    private CLI           cli;
    private CLIView       cliView;

    public CLIEvent(CLI cli, CLIView cliView)
    {
        this.cli = cli;
        this.cliView = cliView;
    }

    public CLI getCLI()
    {
        return cli;
    }

    public CLIView getCLIView()
    {
        return cliView;
    }

    public abstract void beParsed(CLIEventParser parser);
}
