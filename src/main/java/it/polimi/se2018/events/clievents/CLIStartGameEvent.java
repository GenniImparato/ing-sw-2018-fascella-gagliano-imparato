package it.polimi.se2018.events.clievents;

import it.polimi.se2018.controller.CLIEventParser;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.views.CLIView;

public class CLIStartGameEvent extends CLIEvent
{
    public CLIStartGameEvent(CLI cli, CLIView cliView)
    {
        super(cli, cliView);
    }

    @Override
    public void beParsed(CLIEventParser parser)
    {
        parser.parseEvent(this);
    }
}
