package it.polimi.se2018.view.gui.elements.animations;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GUIFrameAnimation
{
    private List<ImageIcon> frames;
    protected GuiAnimatedElement element;
    protected int currentFrame;

    public GUIFrameAnimation(GuiAnimatedElement element)
    {
        frames = new ArrayList<>();
        this.element=element;
    }

    public void addFrame(String fileName)       //add all the frames of the animation in the array
    {
        frames.add(new ImageIcon(fileName));

    }

    public void play(int speed)
    {
        currentFrame = 0;
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                element.setCurrentFrame(frames.get(currentFrame));
                currentFrame++;

                if(currentFrame>=frames.size())
                    currentFrame=0;

            }
        };

        timer.scheduleAtFixedRate(timerTask,0,speed);

    }

}
