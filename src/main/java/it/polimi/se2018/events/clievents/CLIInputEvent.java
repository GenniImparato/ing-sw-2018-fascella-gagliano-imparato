package it.polimi.se2018.events.clievents;

import it.polimi.se2018.controller.CLIEventParser;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.views.CLIView;

public class CLIInputEvent extends CLIEvent
{
    private String  input;

    public CLIInputEvent(CLI cli, CLIView cliView, String input)
    {
        super(cli, cliView);
        this.input = input;
    }

    public String getInput()
    {
        return input;
    }

    @Override
    public void beParsed(CLIEventParser parser)
    {
        parser.parseEvent(this);
    }
}