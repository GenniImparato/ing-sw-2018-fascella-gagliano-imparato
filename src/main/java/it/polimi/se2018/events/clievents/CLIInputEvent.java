package it.polimi.se2018.events.clievents;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.CLIState;

import java.util.ArrayList;

public class CLIInputEvent extends CLIEvent
{
    private ArrayList<String>   inputs;

    public CLIInputEvent(CLI cli, CLIState state, String input)
    {
        super(cli, state);
        inputs = new ArrayList<>();
        this.inputs.add(input);
    }

    public String getInput()
    {
        return inputs.get(0);
    }
}