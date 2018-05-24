package it.polimi.se2018.view.gui.dialogs;

import it.polimi.se2018.view.cli.views.CLIView;
import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;

public abstract class GUIDialog extends JDialog
{
    protected GUI gui;

    public GUIDialog(GUI gui, String title)
    {
        super(gui.getMainWindow(), title, true);
        this.gui = gui;
    }
}
