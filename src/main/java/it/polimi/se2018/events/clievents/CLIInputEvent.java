package it.polimi.se2018.events.clievents;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.CLIState;

import java.util.ArrayList;

public class CLIInputEvent extends CLIEvent
{
    private String  input;

    public CLIInputEvent(CLI cli, CLIState state, String input)
    {
        super(cli, state);
        this.input = input;
    }

    public String getInput()
    {
        return input;
    }
}