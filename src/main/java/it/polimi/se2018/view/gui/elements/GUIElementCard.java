package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Card;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.animations.GUIAnimationActions;
import it.polimi.se2018.view.gui.elements.frame_animation.GUIFrameAnimatedElement;
import it.polimi.se2018.view.gui.elements.frame_animation.GUIFrameAnimation;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class GUIElementCard extends JLabel implements GUIFrameAnimatedElement
{
    protected   Card                card;
    private     GUI                 gui;
    private     JLabel              zoomedImage;
    protected   GUICardPopup        popup;

    private     GUIFrameAnimation   generateAnimation;
    protected   GUIFrameAnimation   turnAnimation;

    public GUIElementCard(Card card, String typePath, GUI gui)
    {
        this.card = card;
        this.gui = gui;

        this.setIcon(new ImageIcon("resources/images/elements/cards/blank.png"));

        zoomedImage = new JLabel();
        zoomedImage.setIcon(new ImageIcon("resources/images/elements/cards/" + card.getName() + "/zoomed.png"));

        popup = new GUICardPopup(this, gui);

        generateAnimation = new GUIFrameAnimation(this);
        for(int i=0; i<=9; i++)
            generateAnimation.addFrame("resources/images/elements/cards/" +typePath+ "/generated_animation/" + i + ".png");

        generateAnimation.addFrame("resources/images/elements/cards/" +typePath+ "/card.png");

        turnAnimation = new GUIFrameAnimation(this);

        for(int i=0; i<=9; i++)
            turnAnimation.addFrame("resources/images/elements/cards/" +typePath+ "/turn_animation/" + i + ".png");

        for(int i=0; i<=7; i++)
            turnAnimation.addFrame("resources/images/elements/cards/" + card.getName() + "/turn_animation/" + i + ".png");
        turnAnimation.addFrame("resources/images/elements/cards/" + card.getName() + "/card.png");

        generateAnimation.setActions(new GUIAnimationActions() {
            @Override
            public void started() {}

            @Override
            public void ended()
            {
                turnAnimation.play(500, 50);
            }
        });

        turnAnimation.setActions(new GUIAnimationActions()
        {
            @Override
            public void started() {}

            @Override
            public void ended()
            {
                addMouseListener(new MouseListener()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {}

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e)
                    {
                        popup.show();
                    }

                    @Override
                    public void mouseExited(MouseEvent e)
                    {
                        popup.hide();
                    }
                });
            }
        });
    }

    public JLabel getZoomedImage()
    {
        return zoomedImage;
    }

    public void playGeneratedAnimation(int startDelay)
    {
        generateAnimation.play(startDelay, 50);
    }

    @Override
    public void setCurrentFrame(ImageIcon imageIcon)
    {
        setIcon(imageIcon);
    }
}
