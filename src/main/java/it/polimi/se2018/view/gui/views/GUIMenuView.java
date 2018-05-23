package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.network.exceptions.CannotCreateServerException;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    new Server();
                    gui.showView(new GUIConnectionView(gui, false));
                }
                catch(CannotCreateServerException exc)
                {
                    gui.showErrorMessage(exc.getMessage1(), exc.getMessage2());
                }

            }

        });
        mainContainer.add(startButton);

        JButton connectButton= new JButton();
        connectButton.setPreferredSize(new Dimension(355, 200));
        connectButton.setIcon(new ImageIcon("resources/images/menu/connecttoserver.png"));
        connectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.showView(new GUIConnectionView(gui, false));

            }

        });


        mainContainer.add(connectButton);


    }


}
