package it.polimi.se2018.view.gui.elements.move_animation;

import it.polimi.se2018.view.gui.elements.animations.GUIAnimationActions;

import java.util.Timer;
import java.util.TimerTask;

public class GUIMoveAnimation
{
    private GUIMoveAnimatedElement  element;
    private GUIAnimationActions     actions;
    private boolean playing;
    private int currentFrame;

    public GUIMoveAnimation(GUIMoveAnimatedElement element)
    {
        this.element = element;
        playing = false;
    }

    public void setActions(GUIAnimationActions actions)
    {
        this.actions = actions;
    }

    public void play(int startX, int startY, int endX, int endY, int speed)
    {
        currentFrame = 0;
        int framesNumber = 20;
        float totalMoveX = (endX - startX);
        float totalMoveY = (endY - startY);

        playing = true;
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                float currentMoveX = totalMoveX*((float)(currentFrame)/framesNumber);
                float currentMoveY = totalMoveY*((float)(currentFrame)/framesNumber);
                int currentX = (int)(startX + currentMoveX);
                int currentY = (int)(startY + currentMoveY);

                element.setCurrentPosition(currentX, currentY);
                currentFrame++;

                if(currentFrame>framesNumber)
                {
                    element.moveAnimationEnded();
                    if(actions != null)
                        actions.ended();
                    playing = false;
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, speed);

        element.moveAnimationStarted();
        if(actions != null)
            actions.started();
    }

    public boolean isPlaying()
    {
        return playing;
    }
}
