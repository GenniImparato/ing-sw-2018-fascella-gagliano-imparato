package it.polimi.se2018.view.gui.elements.frame_animation;


import it.polimi.se2018.view.gui.elements.animations.GUIAnimationActions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GUIFrameAnimation
{
    private     List<ImageIcon>         frames;
    protected   GUIFrameAnimatedElement element;
    protected   int                     currentFrame;
    private     boolean                 playing;
    private GUIAnimationActions         actions;

    public GUIFrameAnimation(GUIFrameAnimatedElement element)
    {
        frames = new ArrayList<>();
        this.element=element;
        playing = false;
    }

    public void addFrame(String fileName)       //add all the frames of the animation in the array
    {
        frames.add(new ImageIcon(fileName));
    }


    public void play(int startDelay, int speed)
    {
        currentFrame = 0;
        playing = true;
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                element.setCurrentFrame(frames.get(currentFrame));
                currentFrame++;

                if(currentFrame >= frames.size())
                {
                    if(actions  != null)
                        actions.ended();
                    playing = false;
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, startDelay, speed);

        if(actions != null)
            actions.started();
    }

    public void playReversed(int startDelay, int speed)
    {
        currentFrame = frames.size()-1;
        playing = true;
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                element.setCurrentFrame(frames.get(currentFrame));
                currentFrame--;

                if(currentFrame < 0)
                {
                    if(actions  != null)
                        actions.ended();
                    playing = false;
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, startDelay, speed);

        if(actions != null)
            actions.started();
    }

    public boolean isPlaying()
    {
        return playing;
    }

    public void setActions(GUIAnimationActions actions)
    {
        this.actions = actions;
    }
}
