package it.polimi.se2018.view.gui.elements;


import it.polimi.se2018.model.toolcards.ToolCard;
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

    public GUIElementToolCard(ToolCard card, int cardNumber, GUI gui)
    {
        super(card, "tool_card", gui);
        selectable = false;
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
                        shoNormal();
                        popup.hide();
                    }
                });
            }
        });
    }

    public void setSelectable(boolean selectable)
    {
        if(!selectable)
            shoNormal();
        
        this.selectable = selectable;
    }

    private void showSelected()
    {
        setIcon(new ImageIcon("resources/images/elements/cards/" + card.getName() + "/selected.png"));
    }

    private void shoNormal()
    {
        setIcon(new ImageIcon("resources/images/elements/cards/" + card.getName() + "/card.png"));
    }
}
