package it.polimi.se2018.view.cli;

import it.polimi.se2018.utils.Color;
import it.polimi.se2018.utils.Logger;

public class CLILogger implements Logger
{
    private CLI cli;
    private boolean active;

    public CLILogger(CLI cli, boolean active)
    {
        this.cli = cli;
        this.active = active;
    }

    public boolean isActive()
    {
        return  active;
    }

    @Override
    public void logMessage(String message)
    {
        if(isActive())
            cli.showMessage(message);
    }

    @Override
    public void logErrorMessage(String message)
    {
        if(isActive())
        cli.showMessage(message, Color.RED);
    }
}
