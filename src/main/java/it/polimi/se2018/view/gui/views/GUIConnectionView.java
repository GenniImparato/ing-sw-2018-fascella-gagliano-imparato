package it.polimi.se2018.view.gui.views;

import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;

public class GUIConnectionView extends GUIView
{
    public GUIConnectionView (GUI gui)
    {
        super(gui, 400,300);

        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));


        //creates the nickname row
        Container firstRowContainer = new Container();
        firstRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        firstRowContainer.add(new JLabel("Nickname: "));
        JTextField textNickname = new JTextField(30);

        firstRowContainer.add(textNickname);

        mainContainer.add(firstRowContainer);


        //creates the server ip row
        Container secondRowContainer = new Container();
        secondRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        secondRowContainer.add(new JLabel("Server IP: "));
        JTextField textIP = new JTextField(30);
        secondRowContainer.add(textIP);

        mainContainer.add(secondRowContainer);

        //creates the type of connection
        Container thirdRowContainer = new Container();
        thirdRowContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        thirdRowContainer.add(new JLabel("Connection Type: "));
        JRadioButton rmiButton = new JRadioButton("RMI");
        JRadioButton socketButton = new JRadioButton("Socket");

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(rmiButton);
        radioGroup.add(socketButton);

        thirdRowContainer.add(rmiButton);
        thirdRowContainer.add(socketButton);

        mainContainer.add(thirdRowContainer);


        //creates two buttons
        Container fourthRowContainer = new Container();
        fourthRowContainer.setLayout(new GridLayout());

        JButton connectButton = new JButton("Connect");
        JButton backButton = new JButton("Cancel");

        fourthRowContainer.add(connectButton);
        fourthRowContainer.add(backButton);

        mainContainer.add(fourthRowContainer);







    }


}
