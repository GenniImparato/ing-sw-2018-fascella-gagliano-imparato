package it.polimi.se2018.controller;

import it.polimi.se2018.events.Event;
import it.polimi.se2018.events.clievents.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.cli.CLI;

public class CLIController extends Controller<CLIEvent>
{
    private CLI                 cli;
    private CLIEventParser      parser;

    public CLIController(CLI cli, Game game)
    {
        super(cli, game);
        this.cli = cli;
        parser = new CLIEventParser(this);
    }

    public CLI getCli()
    {
        return cli;
    }

    @Override
    //handle events from the view
    public void update(CLIEvent event)
    {
        event.beParsed(parser);
    }
}
