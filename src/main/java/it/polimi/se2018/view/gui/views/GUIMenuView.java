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
        super(gui, 710,600, false);

    }

    public void draw()
    {
        mainContainer = new Container();

        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JLabel titleLabel = new JLabel();
        titleLabel.setIcon(new ImageIcon("resources/images/menu/sagrada.png"));
        mainContainer.add(titleLabel);


        //the push of the startButton creates the Server
        JButton startButton = new JButton();
        startButton.setPreferredSize(new Dimension(355, 200));
        startButton.setIcon(new ImageIcon("resources/images/menu/startserver.png"));
        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    Server server = new Server();
                    gui.setServerIp(server.getIP());
                    gui.showMessage("Server created at IP: " + server.getIP());
                    gui.showView(new GUIConnectionView(gui, false));
                }
                catch(CannotCreateServerException e)
                {
                    gui.showErrorMessage("Cannot Create Server!", e.getMessage());
                }

            }

        });
        mainContainer.add(startButton);

        //the push of the connectButton creates a Client connected to a Server already created
        JButton connectButton= new JButton();
        connectButton.setPreferredSize(new Dimension(355, 200));
        connectButton.setIcon(new ImageIcon("resources/images/menu/connecttoserver.png"));
        connectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.showView(new GUIConnectionView(gui, true));
            }

        });

        mainContainer.add(connectButton);
    }
}
