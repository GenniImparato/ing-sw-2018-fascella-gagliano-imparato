package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.animations.GUIAnimationActions;
import it.polimi.se2018.view.gui.elements.frame_animation.GUIFrameAnimation;
import it.polimi.se2018.view.gui.elements.frame_animation.GUIFrameAnimatedElement;
import it.polimi.se2018.view.gui.elements.move_animation.GUIMoveAnimatedElement;
import it.polimi.se2018.view.gui.elements.move_animation.GUIMoveAnimation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementDie extends JLabel implements GUIFrameAnimatedElement, GUIMoveAnimatedElement
{
    private Die                 die;
    private String              path;       //iconpath

    private boolean             selectable;
    private boolean             selected;

    private GUIDieActions       actions;

    private GUIElementDie       thisElement;

    private GUIFrameAnimation   generateAnimation;

    private GUIMoveAnimation    moveToRoundTrackAnimation;
    private GUIMoveAnimation    moveToBoardAnimation;

    private GUI                 gui;

    public GUIElementDie(Die die, boolean startWithBlankFrame, GUI gui)
    {
        path = "resources/images/elements/die/" + die.getColor().toString().toLowerCase() + "/";
        this.die=die;
        this.gui = gui;

        generateAnimation = new GUIFrameAnimation(this);

        if(startWithBlankFrame)
            setIcon(new ImageIcon(path + "blank.png"));
        else
            setIcon(new ImageIcon(path + die.getValue() + ".png"));

        for(int i=0; i<=11; i++)
            generateAnimation.addFrame(path+"generate_animation/" + i + ".png");

        path+=die.getValue();

        generateAnimation.addFrame(path+".png");

        moveToRoundTrackAnimation = new GUIMoveAnimation(this);
        moveToBoardAnimation = new GUIMoveAnimation(this);

        thisElement = this;




        addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            { }

            @Override
            public void mousePressed(MouseEvent e)
            {
                if(actions!=null && isSelected() &&!generateAnimation.isPlaying())
                    actions.clicked(thisElement);
            }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                if(actions != null)
                    actions.mouseEntered();

                if(selectable &&!generateAnimation.isPlaying())
                    showSelectedIcon();
                else
                    e.getComponent().getParent().dispatchEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                if(actions != null)
                    actions.mouseExited();

                if(selectable && !generateAnimation.isPlaying())
                    showNormalIcon();
                else
                    e.getComponent().getParent().dispatchEvent(e);
            }
        });
    }

    @Override
    public void setCurrentFrame(ImageIcon imageIcon)
    {
        this.setIcon(imageIcon);
        this.validate();
    }

    public void playGenerateAnimation(int startDelay)
    {
        generateAnimation.play(startDelay,30);
    }

    public void playMoveToRoundTrackAnimation(GUIElementRoundCell cell)
    {
        moveToRoundTrackAnimation.setActions(new GUIAnimationActions()
        {
            @Override
            public void started() {}

            @Override
            public void ended()
            {
                cell.addDie(thisElement);
                gui.getGlassPane().remove(thisElement);
                gui.refresh();
            }
        });

        moveToRoundTrackAnimation.play(gui.getWindowRealtiveX(this), gui.getWindowRealtiveY(this),
                gui.getWindowRealtiveX(cell), gui.getWindowRealtiveY(cell), 35);
    }

    public void playMoveToBoardAnimation(GUIElementBoard board)
    {
        moveToBoardAnimation.play(gui.getWindowRealtiveX(this), gui.getWindowRealtiveY(this),
                gui.getWindowRealtiveX(board) + 140, gui.getWindowRealtiveY(board) - 80, 35);
    }

    public void playMoveToBoardAnimation(GUIElementCell cell)
    {
        moveToBoardAnimation.setActions(new GUIAnimationActions()
        {
            @Override
            public void started() {}

            @Override
            public void ended()
            {
                cell.addDie(thisElement);
                gui.getGlassPane().remove(thisElement);
                gui.refresh();
            }
        });
        moveToBoardAnimation.play(gui.getWindowRealtiveX(this), gui.getWindowRealtiveY(this),
                gui.getWindowRealtiveX(cell), gui.getWindowRealtiveY(cell), 35);
    }

    public void showNormalIcon()
    {
        setIcon(new ImageIcon(path+".png"));
        selected = false;
    }

    public void showSelectedIcon()
    {
        setIcon(new ImageIcon(path+"selected.png"));
        selected = true;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelectable(boolean selectable)
    {
        this.selectable = selectable;
    }

    public void setActions(GUIDieActions actions)
    {
        this.actions = actions;
    }

    public Die getDie()
    {
        return die;
    }

    @Override
    public void moveAnimationStarted()
    {
        setSelectable(false);

        if(getParent() != null)
            getParent().remove(this);
        gui.getGlassPane().add(this);
    }

    @Override
    public void setCurrentPosition(int x, int y)
    {
        setBounds(x, y, 70, 70);
    }

    @Override
    public void moveAnimationEnded()
    {
        gui.refresh();
    }
}
