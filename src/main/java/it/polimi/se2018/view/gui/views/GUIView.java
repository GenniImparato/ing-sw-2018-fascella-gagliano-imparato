package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.view.gui.GUI;

import java.awt.*;

public abstract class GUIView
{
    protected GUI gui;
    protected Container mainContainer;
    protected int width;
    protected int height;

    public GUIView (GUI gui, int width, int height)
    {
        this.gui = gui;
        this.width = width;
        this.height = height;
        this.mainContainer = new Container();
    }

    public void draw()
    {
        gui.setContainer(mainContainer);
        gui.setDimensions(width,height);


    }
}
