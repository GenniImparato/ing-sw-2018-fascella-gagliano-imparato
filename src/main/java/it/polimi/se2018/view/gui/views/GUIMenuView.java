package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;

public class GUIMenuView extends GUIView
{
    public GUIMenuView(GUI gui)
    {
        super(gui, 400,300);

        mainContainer.setLayout(new GridLayout(2,1));

        JButton startButton = new JButton("Start a new Game (Server)");
        mainContainer.add(startButton);

        JButton connectButton= new JButton("Connect to a Game (Client)");
        mainContainer.add(connectButton);


    }


}
