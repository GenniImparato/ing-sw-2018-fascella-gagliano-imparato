package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.animations.GUIAnimationActions;
import it.polimi.se2018.view.gui.elements.frame_animation.GUIFrameAnimation;
import it.polimi.se2018.view.gui.elements.frame_animation.GUIFrameAnimatedElement;
import it.polimi.se2018.view.gui.elements.move_animation.GUIMoveAnimatedElement;
import it.polimi.se2018.view.gui.elements.move_animation.GUIMoveAnimation;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementDie extends JLabel implements GUIFrameAnimatedElement, GUIMoveAnimatedElement
{
    private static final int    GENERATED_ANIMATION_FRAMES  = 11;
    private static final String IMAGES_ROOT  = "resources/images/elements/die/";

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
        this.die=die;
        this.gui = gui;

        moveToRoundTrackAnimation = new GUIMoveAnimation(this);
        moveToBoardAnimation = new GUIMoveAnimation(this);

        thisElement = this;

        this.path = IMAGES_ROOT + die.getColor().toString().toLowerCase() + "/";

        generateAnimation = new GUIFrameAnimation(this);

        if(startWithBlankFrame)
            setIcon(new ImageIcon(path + "blank.png"));
        else
            setIcon(new ImageIcon(path + die.getValue() + ".png"));

        generateAnimation.addFrame(path + "blank.png");
        for(int i=0; i<=GENERATED_ANIMATION_FRAMES; i++)
            generateAnimation.addFrame(path + "generate_animation/" + i + ".png");

        generateAnimation.addFrame(path + die.getValue() + ".png");

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
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                if(actions != null)
                    actions.mouseExited();

                if(selectable && !generateAnimation.isPlaying())
                    showNormalIcon();
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

        moveToRoundTrackAnimation.play(gui.getWindowRelativeX(this), gui.getWindowRelativeY(this),
                gui.getWindowRelativeX(cell), gui.getWindowRelativeY(cell), 35);
    }

    public void playMoveToBoardAnimation(GUIElementBoard board)
    {
        moveToBoardAnimation.play(gui.getWindowRelativeX(this), gui.getWindowRelativeY(this),
                gui.getWindowRelativeX(board) + 140, gui.getWindowRelativeY(board) - 80, 35);
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
        moveToBoardAnimation.play(gui.getWindowRelativeX(this), gui.getWindowRelativeY(this),
                gui.getWindowRelativeX(cell), gui.getWindowRelativeY(cell), 35);
    }

    public void playMoveBackToDraftPoolAnimation(GUIElementDraftPool draftPool)
    {
        moveToBoardAnimation.setActions(new GUIAnimationActions()
        {
            @Override
            public void started() {}

            @Override
            public void ended()
            {
                gui.getGlassPane().remove(thisElement);
                gui.refresh();
            }
        });
        moveToBoardAnimation.play(gui.getWindowRelativeX(this), gui.getWindowRelativeY(this),
                gui.getWindowRelativeX(draftPool), gui.getWindowRelativeY(draftPool), 60);

        generateAnimation.playReversed(0, 20);
    }

    public void showNormalIcon()
    {
        setIcon(new ImageIcon(path + die.getValue() +".png"));
        selected = false;
    }

    public void showSelectedIcon()
    {
        setIcon(new ImageIcon(path + die.getValue() + "selected.png"));
        selected = true;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelectable(boolean selectable)
    {
        this.selectable = selectable;
        if(!selectable)
            showNormalIcon();
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

    public void refresh(Die die)
    {
        //refresh the die only if it's value or color has changed
        if(die.getColor() != this.die.getColor()   ||  die.getValue() != this.die.getValue())
        {
            String newPath = IMAGES_ROOT + die.getColor().toString().toLowerCase() + "/";

            GUIFrameAnimation changeAnimation = new GUIFrameAnimation(this);

            setIcon(new ImageIcon(path + die.getValue() + ".png"));

            for (int i = GENERATED_ANIMATION_FRAMES; i >= 0; i--)
                changeAnimation.addFrame(path  +"generate_animation/" + i + ".png");
            for (int i = 0; i <= GENERATED_ANIMATION_FRAMES; i++)
                changeAnimation.addFrame(newPath + "generate_animation/" + i + ".png");

            changeAnimation.addFrame(newPath + die.getValue() + ".png");

            path = newPath;
            this.die = die;

            changeAnimation.play(0, 30);
        }
    }
}
