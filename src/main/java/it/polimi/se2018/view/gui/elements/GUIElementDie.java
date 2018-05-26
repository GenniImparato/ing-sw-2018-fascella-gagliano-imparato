package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.gui.elements.animations.GUIFrameAnimation;
import it.polimi.se2018.view.gui.elements.animations.GuiAnimatedElement;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementDie extends JLabel implements GuiAnimatedElement
{
    private Die die;
    private GUIFrameAnimation animation;
    private String path;
    private boolean selectable;

    public GUIElementDie(Die die)
    {
        path = "resources/images/elements/die/"+die.getColor().toString().toLowerCase()+"/";
        this.die=die;
        animation = new GUIFrameAnimation(this);

        this.setIcon(new ImageIcon(path + die.getValue() +".png"));

        path+=die.getValue();

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
        this.setIcon(new ImageIcon(path+".png"));
    }

    public void showSelectedIcon()
    {
        this.setIcon(new ImageIcon(path+"selected.png"));
    }

    public void setSelectable(boolean selectable)
    {
        if(!selectable)
            showNormalIcon();

        this.selectable=selectable;
    }

}
