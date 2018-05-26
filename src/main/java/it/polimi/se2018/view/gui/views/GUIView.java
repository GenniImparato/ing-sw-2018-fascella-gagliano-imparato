package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.view.gui.GUI;

import java.awt.*;

public abstract class GUIView
{
    protected GUI gui;
    protected Container mainContainer;
    protected int width;
    protected int height;
    private boolean resizable;

    public GUIView (GUI gui, int width, int height, boolean resizable)
    {
        this.gui = gui;
        this.width = width;
        this.height = height;
        this.resizable=resizable;
    }

    public void drawOnMainWindow()
    {
        gui.setContainer(mainContainer);
        gui.getMainWindow().setResizable(resizable);
        if(!resizable)
            gui.setDimensions(width,height);

    }

    public abstract void draw();
}
