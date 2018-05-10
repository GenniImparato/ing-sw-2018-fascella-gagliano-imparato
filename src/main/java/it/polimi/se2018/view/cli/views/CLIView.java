package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.view.cli.CLI;

public abstract class CLIView
{
    protected CLI cli;

    public CLIView(CLI cli)
    {
        this.cli = cli;
    }

    public CLI getCLI()
    {
        return cli;
    }

    public abstract void draw();

    //method called by the controller in response to an event from this view
    public abstract void control(String input);

}
