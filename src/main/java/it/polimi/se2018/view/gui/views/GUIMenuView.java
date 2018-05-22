package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;

public class GUIMenuView extends GUIView
{
    public GUIMenuView(GUI gui)
    {
        super(gui, 710,600);

        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JLabel titleLabel = new JLabel();
        titleLabel.setIcon(new ImageIcon("resources/images/menu/sagrada.png"));
        mainContainer.add(titleLabel);

        JButton startButton = new JButton();
        startButton.setPreferredSize(new Dimension(355, 200));
        startButton.setIcon(new ImageIcon("resources/images/menu/startserver.png"));
        mainContainer.add(startButton);

        JButton connectButton= new JButton();
        connectButton.setPreferredSize(new Dimension(355, 200));
        connectButton.setIcon(new ImageIcon("resources/images/menu/connecttoserver.png"));
        mainContainer.add(connectButton);


    }


}
