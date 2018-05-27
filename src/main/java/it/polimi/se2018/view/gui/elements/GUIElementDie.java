package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.gui.elements.animations.GUIFrameAnimation;
import it.polimi.se2018.view.gui.elements.animations.GuiAnimatedElement;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementDie extends JLabel implements GuiAnimatedElement
{
    private Die                 die;
    private String              path;       //iconpath

    private boolean             selectable;
    private boolean             selected;

    private GUIDieActions       actions;

    private GUIElementDie       thisElement;

    private GUIFrameAnimation   generateAnimation;

    public GUIElementDie(Die die, boolean startWithBlankFrame)
    {
        path = "resources/images/elements/die/" + die.getColor().toString().toLowerCase() + "/";
        this.die=die;

        generateAnimation = new GUIFrameAnimation(this);

        if(startWithBlankFrame)
            setIcon(new ImageIcon(path + "blank.png"));
        else
            setIcon(new ImageIcon(path + die.getValue() + ".png"));

        for(int i=0; i<=11; i++)
            generateAnimation.addFrame(path+"generate_animation/" + i + ".png");

        path+=die.getValue();

        generateAnimation.addFrame(path+".png");

        thisElement = this;




        addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(actions!=null && isSelected() &&!generateAnimation.isPlaying())
                    actions.clicked(thisElement);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                if(selectable &&!generateAnimation.isPlaying())
                    showSelectedIcon();
                else
                    e.getComponent().getParent().dispatchEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
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
}
