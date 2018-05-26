package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.view.gui.GUI;

import java.awt.*;

public class GUIGameView extends GUIView
{
    public GUIGameView(GUI gui)
    {
        super(gui, 300,300);
    }

    public void draw()
    {
        mainContainer = new Container();
    }

}
