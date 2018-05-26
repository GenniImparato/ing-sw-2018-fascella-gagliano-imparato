package it.polimi.se2018.view.gui;

import javax.swing.*;

public class GUINotification extends Popup
{
    private GUI gui;

    public GUINotification(GUI gui, String message, int position)
    {
        super(gui.getMainWindow(),
                new JLabel(message),
                gui.getMainWindow().getX() + 10,
                gui.getMainWindow().getY() + 20 * position + 10);

    }
}

