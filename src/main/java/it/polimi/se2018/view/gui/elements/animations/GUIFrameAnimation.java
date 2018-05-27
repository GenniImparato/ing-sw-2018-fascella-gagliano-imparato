package it.polimi.se2018.view.gui.elements.animations;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GUIFrameAnimation
{
    private     List<ImageIcon>         frames;
    protected   GuiAnimatedElement      element;
    protected   int                     currentFrame;
    private     boolean                 playing;

    public GUIFrameAnimation(GuiAnimatedElement element)
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

                if(currentFrame>=frames.size())
                {
                    timer.cancel();
                    playing = false;
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, startDelay, speed);
    }

    public boolean isPlaying()
    {
        return playing;
    }
}
