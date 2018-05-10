package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.view.cli.CLI;

public abstract class CLIView
{
    private CLI cli;

    public CLIView(CLI cli)
    {
        this.cli = cli;
    }

    public CLI getCLI()
    {
        return cli;
    }

}
