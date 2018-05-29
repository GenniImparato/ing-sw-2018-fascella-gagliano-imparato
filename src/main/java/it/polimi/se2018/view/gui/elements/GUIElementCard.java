package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.model.Card;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.model.toolcards.ToolCard;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.elements.animations.GUIAnimationActions;
import it.polimi.se2018.view.gui.elements.frame_animation.GUIFrameAnimatedElement;
import it.polimi.se2018.view.gui.elements.frame_animation.GUIFrameAnimation;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementCard extends JLabel implements GUIFrameAnimatedElement
{
    private Card                card;
    private GUI                 gui;
    private JLabel              zoomedImage;
    private GUICardPopup        popup;

    private GUIFrameAnimation   generateAnimation;
    private GUIFrameAnimation   turnAnimation;

    public GUIElementCard(PublicObjectiveCard card, GUI gui)
    {
        this.card = card;
        this.gui = gui;

        this.setIcon(new ImageIcon("resources/images/elements/cards/blank.png"));

        zoomedImage = new JLabel();
        zoomedImage.setIcon(new ImageIcon("resources/images/elements/cards/" + card.getName() + "/zoomed.png"));

        popup = new GUICardPopup(this, gui);

        setUpAnimations("public_card");

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

        setUpPublicAnimationActions();
    }

    public GUIElementCard(ToolCard card, GUI gui)
    {
        this.card = card;
        this.gui = gui;

        this.setIcon(new ImageIcon("resources/images/elements/cards/blank.png"));

        zoomedImage = new JLabel();
        zoomedImage.setIcon(new ImageIcon("resources/images/elements/cards/" + card.getName() + "/zoomed.png"));

        popup = new GUICardPopup(this, gui);

        setUpAnimations("tool_card");

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

        setUpPublicAnimationActions();
    }

    private void setUpAnimations(String cardType)
    {
        generateAnimation = new GUIFrameAnimation(this);
        for(int i=0; i<=9; i++)
            generateAnimation.addFrame("resources/images/elements/cards/" +cardType+ "/generated_animation/" + i + ".png");

        generateAnimation.addFrame("resources/images/elements/cards/" +cardType+ "/card.png");

        turnAnimation = new GUIFrameAnimation(this);

        for(int i=0; i<=9; i++)
            turnAnimation.addFrame("resources/images/elements/cards/" +cardType+ "/turn_animation/" + i + ".png");
    }

    private void setUpPublicAnimationActions()
    {
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
