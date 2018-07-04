package it.polimi.se2018.view.gui.dialogs;

import it.polimi.se2018.view.gui.GUI;

public class GUIIncrementDieDialog extends GUIYesNoDialog
{
    private static final String     PLUS_ICON_PATH = "./resources/images/increment_dialog/plus.png";
    private static final String     MINUS_ICON_PATH = "./resources/images/increment_dialog/minus.png";

    private static final String     TITLE = "Chose an action!";
    private static final String     MESSAGE = "Increment or decrement the value of the die!";

    public GUIIncrementDieDialog(GUI gui)
    {
        super(gui, TITLE, MESSAGE, PLUS_ICON_PATH, MINUS_ICON_PATH);
    }
}
