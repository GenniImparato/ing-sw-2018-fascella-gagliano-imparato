package it.polimi.se2018.view.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIReaderTask extends Thread
{
    CLI cli;
    boolean running;

    public CLIReaderTask(CLI cli)
    {
        this.cli = cli;
        running = true;
        start();
    }

    public void cancelReading()
    {
        interrupt();
    }

    @Override
    public void run()
    {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String msg;

        System.out.println("Started");
        while(!isInterrupted())
        {
            try
            {
                if(stdin.ready())
                {
                    msg = stdin.readLine();
                    cli.currentView.parseInput(msg);
                    cancelReading();
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Ended");
    }
}
