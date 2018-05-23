package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIConnectionView extends GUIView
{
    private boolean requestIP;

    public GUIConnectionView (GUI gui, boolean requestIP)
    {
        super(gui, 710,400);
        this.requestIP = requestIP;

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
        JTextField textNickname = new JTextField(30);

        firstRowContainer.add(textNickname);

        background.add(firstRowContainer);


        //creates the server ip row
        Container secondRowContainer = new Container();
        secondRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER));


        secondRowContainer.add(new JLabel("Server IP: "));

        if(requestIP)
        {
            JTextField textIP = new JTextField(30);
            secondRowContainer.add(textIP);
        }

        else
        {
            JLabel textIP = new JLabel("10.169.219.151");
            secondRowContainer.add(textIP);
        }

        background.add(secondRowContainer);

        //creates the type of connection
        Container thirdRowContainer = new Container();
        thirdRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        thirdRowContainer.add(new JLabel("Connection Type: "));
        JRadioButton rmiButton = new JRadioButton("RMI");
        JRadioButton socketButton = new JRadioButton("Socket");

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(rmiButton);
        radioGroup.add(socketButton);
        socketButton.setSelected(true);

        thirdRowContainer.add(rmiButton);
        thirdRowContainer.add(socketButton);

        background.add(thirdRowContainer);

        //creates two buttons
        Container fourthRowContainer = new Container();
        fourthRowContainer.setLayout(new BoxLayout(fourthRowContainer, BoxLayout.X_AXIS));

        JButton connectButton = new JButton();
        connectButton.setPreferredSize(new Dimension(355, 200));
        connectButton.setIcon(new ImageIcon("resources/images/menu/connect.png"));

        JButton backButton = new JButton();
        backButton.setPreferredSize(new Dimension(355, 200));
        backButton.setIcon(new ImageIcon("resources/images/menu/cancel.png"));
        backButton.addActionListener(new ActionListener() {
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
