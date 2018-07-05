package it.polimi.se2018.controller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Generic countdown timer
 */
public abstract class GameTimer
{
    private static final int    TIMER_PERIOD = 1000;

    private int         time;
    private int         startTime;
    private TimerTask   task;

    /**
     * Constructor
     * @param startTime initial time
     */
    public GameTimer(int startTime)
    {
        this.startTime = startTime;
        reset();
    }

    /**
     * Starts the countdown
     */
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

    /**
     * Stops the countdown
     */
    public void stop()
    {
        if(task != null)
            task.cancel();
    }

    /**
     * Reset to initial time the countdown
     */
    public void reset()
    {
        time = startTime;
    }

    /**
     * Returns the current time
     * @return current time
     */
    public int getTime()
    {
        return time;
    }

    public abstract void timeUpdated();
}
