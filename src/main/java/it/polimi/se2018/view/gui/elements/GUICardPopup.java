package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.GUIPopup;

import javax.swing.*;
import java.awt.*;

public class GUICardPopup
{
    private Popup popup;
    private GUIElementCard card;
    private GUI gui;

    GUICardPopup(GUIElementCard card, GUI gui)
    {
        this.card = card;
        this.gui = gui;
    }

    public void show()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        mainPanel.add(card.getZoomedImage());

        int popupX = gui.getScreenRelativeX(card) - 10 - 300;
        int popupY = gui.getScreenRelativeY(card) - 210;

        popup = new GUIPopup(gui, mainPanel, popupX, popupY);
        popup.show();
    }

    public void hide()
    {
        popup.hide();
    }
}
