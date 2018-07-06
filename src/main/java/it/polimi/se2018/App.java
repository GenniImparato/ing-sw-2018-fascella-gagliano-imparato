package it.polimi.se2018;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.gui.GUI;
import it.polimi.se2018.view.gui.dialogs.GUIDialog;
import it.polimi.se2018.view.gui.dialogs.GUIYesNoDialog;
import it.polimi.se2018.view.gui.views.GUIFinalScoreView;


public class App
{
    private static final String CLI_ICON_PATH =     "resources/images/menu/CLI.png";
    private static final String GUI_ICON_PATH =     "resources/images/menu/GUI.png";

    public static void main( String[] args )
    {
        GUIYesNoDialog viewDialog = new GUIYesNoDialog("SAGRADA", "Choose the interface to play with", GUI_ICON_PATH, CLI_ICON_PATH);
        if(viewDialog.getResponse() == GUIYesNoDialog.YES)
            new GUI();
        else
            new CLI();
    }
}