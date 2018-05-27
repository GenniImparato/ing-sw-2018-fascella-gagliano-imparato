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
    private GUIFrameAnimation   animation;
    private String              path;       //iconpath

    private boolean             selectable;
    private boolean             selected;

    private GUIDieActions       actions;

    private GUIElementDie       thisElement;

    public GUIElementDie(Die die)
    {
        path = "resources/images/elements/die/"+die.getColor().toString().toLowerCase()+"/";
        this.die=die;
        animation = new GUIFrameAnimation(this);

        setIcon(new ImageIcon(path + die.getValue() +".png"));

        path+=die.getValue();

        thisElement = this;

        addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(actions!=null && isSelected())
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
                if(selectable)
                    showSelectedIcon();
                else
                    e.getComponent().getParent().dispatchEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                if(selectable)
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
    }

    public void playAnimation()
    {
        animation.play(100);
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
        if(!selectable)
            showNormalIcon();

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
