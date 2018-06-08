package it.polimi.se2018.view.gui.elements;


import it.polimi.se2018.model.Card;
import it.polimi.se2018.mvc_comunication.events.UseToolCardEvent;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.dialogs.GUIYesNoDialog;
import it.polimi.se2018.view.gui.elements.animations.GUIAnimationActions;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIElementToolCard extends GUIElementCard
{
    private boolean selectable;
    private int cardNumber;
    private Card card;

    public GUIElementToolCard(Card card, int cardNumber, GUI gui)
    {
        super(card, "tool_card", gui);
        selectable = false;
        this.card = card;
        this.cardNumber = cardNumber;

        turnAnimation.setActions(new GUIAnimationActions()
        {
            @Override
            public void started() {}

            @Override
            public void ended()
            {
                setSelectable(true);
                addMouseListener(new MouseListener()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {}

                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        if(selectable)
                        {
                            popup.hide();
                            GUIYesNoDialog dialog = new GUIYesNoDialog(gui, "Tool card", "Do you want to use " + card.getName() + "?");

                            if(dialog.getResponse() == GUIYesNoDialog.YES)
                                gui.notify(new UseToolCardEvent(gui, cardNumber));
                        }

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e)
                    {
                        if(selectable)
                            showSelected();
                        popup.show();
                    }

                    @Override
                    public void mouseExited(MouseEvent e)
                    {
                        if(selectable)
                            showNormal();
                        popup.hide();
                    }
                });
            }
        });
    }

    public void setSelectable(boolean selectable)
    {
        this.selectable = selectable;
    }

    public void showSelected()
    {
        setIcon(new ImageIcon("resources/images/elements/cards/" + card.getName() + "/selected.png"));
    }

    public void showNormal()
    {
        setIcon(new ImageIcon("resources/images/elements/cards/" + card.getName() + "/card.png"));
    }

    public Card getCard()
    {
        return card;
    }
}
