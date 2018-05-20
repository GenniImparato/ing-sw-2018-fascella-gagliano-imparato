package it.polimi.se2018.view.cli;

import it.polimi.se2018.view.cli.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class CLIReaderTask extends Thread
{
    BufferedReader  reader;
    boolean         reading;
    CLI             cli;

    public CLIReaderTask(CLI cli)
    {
        this.reader = cli.reader;
        this.cli    = cli;
    }

    public void cancel()
    {
        reading = false;
    }

    @Override
    public void run()
    {
        reading = true;

        try
        {
            while(reading)
            {
                if(reader.ready())
                {
                    if(cli.currentView != null)
                        cli.currentView.parseInput(reader.readLine());
                    cancel();
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
