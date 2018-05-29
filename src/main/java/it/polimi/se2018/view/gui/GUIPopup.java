package it.polimi.se2018.view.gui;

import javax.swing.*;

public class GUIPopup extends Popup
{
    public GUIPopup(GUI gui, JComponent shownComponent, int x, int y)
    {
        super(gui.getMainWindow(), shownComponent, x, y);
    }
}
