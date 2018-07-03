package it.polimi.se2018.controller;

import java.util.Timer;
import java.util.TimerTask;

public abstract class GameTimer
{
    private static final int    TIMER_PERIOD = 1000;

    private int         time;
    private int         startTime;
    private TimerTask   task;

    public GameTimer(int startTime)
    {
        this.startTime = startTime;
        reset();
    }

    public void start()
    {
        stop();

        task = new TimerTask()
        {
            @Override
            public void run()
            {
                if(time > 0)
                {
                    time--;
                    timeUpdated();
                }
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, TIMER_PERIOD);
    }

    public void stop()
    {
        if(task != null)
            task.cancel();
    }

    public void reset()
    {
        time = startTime;
    }

    public int getTime()
    {
        return time;
    }

    public abstract void timeUpdated();
}
