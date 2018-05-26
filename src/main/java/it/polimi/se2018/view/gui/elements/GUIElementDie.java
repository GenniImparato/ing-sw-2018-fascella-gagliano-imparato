package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.view.gui.elements.animations.GUIFrameAnimation;
import it.polimi.se2018.view.gui.elements.animations.GuiAnimatedElement;

import javax.swing.*;

public class GUIElementDie extends JLabel implements GuiAnimatedElement
{
    private Die die;
    private GUIFrameAnimation animation;
    private String path;

    public GUIElementDie(Die die)
    {
        path = "resources/images/elements/die/"+die.getColor().toString().toLowerCase()+"/";
        this.die=die;
        animation = new GUIFrameAnimation(this);

        this.setIcon(new ImageIcon(path + die.getValue() +".png"));

        path+=die.getValue()+".png";



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
        this.setIcon(new ImageIcon(path));
    }

}
