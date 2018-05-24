package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.mvc_comunication.events.AddPlayerEvent;
import it.polimi.se2018.network.exceptions.CannotConnectToServerException;
import it.polimi.se2018.network.rmi.client.RMINetworkHandler;
import it.polimi.se2018.network.socket.client.SocketNetworkHandler;
import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIConnectionView extends GUIView
{
    private boolean     requestIP;

    private JTextField  textNickname;
    private JTextField  textIP;
    private JRadioButton rmiButton;
    private JRadioButton socketButton;

    public GUIConnectionView (GUI gui, boolean requestIP)
    {
        super(gui, 710,400);
        this.requestIP = requestIP;
    }

    public void draw()
    {
        mainContainer = new Container();

        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("resources/images/menu/back1.png"));
        mainContainer.add(background);

        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        background.add(Box.createVerticalStrut(30));

        //creates the nickname row
        Container firstRowContainer = new Container();
        firstRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        firstRowContainer.add(new JLabel("Nickname: "));
        textNickname = new JTextField(30);

        firstRowContainer.add(textNickname);

        background.add(firstRowContainer);


        //creates the server ip row
        Container secondRowContainer = new Container();
        secondRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER));


        secondRowContainer.add(new JLabel("Server IP: "));

        if(requestIP)
        {
            textIP = new JTextField(30);
            secondRowContainer.add(textIP);
        }
        else
        {
            JLabel labelIP = new JLabel(gui.getServerIp());
            secondRowContainer.add(labelIP);
        }

        background.add(secondRowContainer);

        //creates the type of connection
        Container thirdRowContainer = new Container();
        thirdRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        thirdRowContainer.add(new JLabel("Connection Type: "));
        rmiButton = new JRadioButton("RMI");
        socketButton = new JRadioButton("Socket");

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(rmiButton);
        radioGroup.add(socketButton);
        socketButton.setSelected(true);

        thirdRowContainer.add(rmiButton);
        thirdRowContainer.add(socketButton);

        background.add(thirdRowContainer);

        //creates the connection button
        Container fourthRowContainer = new Container();
        fourthRowContainer.setLayout(new BoxLayout(fourthRowContainer, BoxLayout.X_AXIS));

        JButton connectButton = new JButton();
        connectButton.setPreferredSize(new Dimension(355, 200));
        connectButton.setIcon(new ImageIcon("resources/images/menu/connect.png"));
        connectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String correctIp;

                if(textNickname.getText().length() == 0)
                {
                    gui.showErrorMessage("Error", "Empty nickname field!");
                    return;
                }

                if(requestIP)
                {
                    if (textIP.getText().length() == 0)
                    {
                        gui.showErrorMessage("Error", "Empty IP field!");
                        return;
                    }
                    correctIp = textIP.getText();
                }
                else
                    correctIp = gui.getServerIp();

                try
                {
                    if (rmiButton.isSelected())
                        gui.setNetworkHandler(new RMINetworkHandler(correctIp, gui));
                    else if (socketButton.isSelected())
                        gui.setNetworkHandler(new SocketNetworkHandler(correctIp, gui));

                    gui.showMessage("Connected to server!");
                    gui.setServerIp(correctIp);

                    gui.setAssociatedPlayerNickname(textNickname.getText());
                    gui.notify(new AddPlayerEvent(gui, textNickname.getText()));
                }
                catch (CannotConnectToServerException e)
                {
                    gui.showErrorMessage("Cannot connect to Server", e.getMessage());
                }
            }
        });

        JButton backButton = new JButton();
        backButton.setPreferredSize(new Dimension(355, 200));
        backButton.setIcon(new ImageIcon("resources/images/menu/cancel.png"));

        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.showView(new GUIMenuView(gui));
            }
        });

        fourthRowContainer.add(connectButton);
        fourthRowContainer.add(backButton);

        mainContainer.add(fourthRowContainer);
    }

}
