package it.polimi.se2018.view.gui.elements;

import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.GUIPopup;

import javax.swing.*;
import java.awt.*;

public class GUIRoundTrackPopup
{
    private Popup popup;
    private GUIElementRoundCell cell;
    private GUI gui;

    GUIRoundTrackPopup(GUIElementRoundCell cell, GUI gui)
    {
        this.cell = cell;
        this.gui = gui;
    }

    public void show()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        for(GUIElementDie guiDie : cell.getDice())
            mainPanel.add(new GUIElementDie(guiDie.getDie(), false, gui));


        int popupX = gui.getScreenRelativeX(cell) - 10;
        int popupY = gui.getScreenRelativeY(cell) - 90;

        popup = new GUIPopup(gui, mainPanel, popupX, popupY);
        popup.show();
    }

    public void hide()
    {
        popup.hide();
    }
}
